package in.thiru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.razorpay.Order;

import in.thiru.model.Customer;
import in.thiru.repository.CustomerRepository;
import in.thiru.service.RozarPayService;

@Controller
public class PaymentController {

		@Autowired
	    private RozarPayService razorpayService;

	    @Autowired
	    private CustomerRepository customerRepository;

	    /**
	     * Endpoint to initiate the Razorpay payment process (e.g., generate order)
	     * @param amount The amount to be paid (in INR)
	     * @param model The model to pass data to the view
	     * @return The view name (Thymeleaf page)
	     */
	    @GetMapping("/create-order")
	    public String createOrder(@RequestParam("amount") int amount, Model model) {
	        try {
	            int amountInPaise = amount * 100;  // Convert to paise
	            // Create Razorpay order
	            Order order = razorpayService.createOrder(amountInPaise);

	            // Add the Razorpay order to the model
	            model.addAttribute("orderId", order.get("id"));
	            model.addAttribute("amount", amount);
	            model.addAttribute("keyId", "your_razorpay_key_id");  // Replace with your Razorpay key

	            return "payment-page";  // Return the Thymeleaf page for payment
	        } catch (Exception e) {
	            e.printStackTrace();
	            model.addAttribute("error", "Failed to create Razorpay order.");
	            return "error-page";  // Return error page if order creation fails
	        }
	    }

	    /**
	     * Endpoint to handle the payment success and signature verification
	     * @param razorpay_payment_id The payment ID returned by Razorpay
	     * @param razorpay_order_id The order ID returned by Razorpay
	     * @param razorpay_signature The signature sent by Razorpay
	     * @param model The model to pass data to the view
	     * @return The success or failure page
	     */
	    @PostMapping("/payment-success")
	    public String paymentSuccess(@RequestParam("razorpay_payment_id") String razorpay_payment_id,
	                                 @RequestParam("razorpay_order_id") String razorpay_order_id,
	                                 @RequestParam("razorpay_signature") String razorpay_signature,
	                                 Model model) {

	        String payload = razorpay_order_id + "|" + razorpay_payment_id;

	        // Verify the payment signature
	        boolean isValid = razorpayService.verifyPaymentSignature(payload, razorpay_signature);

	        if (isValid) {
	            // If the payment is verified successfully, save the customer info
	            Customer customer = new Customer();
	            customer.setPaymentId(razorpay_payment_id);
	            customer.setOrderId(razorpay_order_id);
	            customer.setPaymentStatus(true);  // Payment was successful

	            customerRepository.save(customer);  // Save customer to the DB

	            model.addAttribute("message", "Payment successful!");
	            return "payment-success";
	        } else {
	            model.addAttribute("message", "Payment verification failed!");
	            return "payment-failure";
	        }
	    }
}
