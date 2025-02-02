package in.thiru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;

import in.thiru.service.RazorpayService;

@RestController
@RequestMapping("/razorpay")
public class RazorpayController {
	
	
		@Autowired
	    private RazorpayService razorpayService;

	    @PostMapping("/createOrder")
	    public Order createOrder(@RequestParam int amount) {
	        try {
	            return razorpayService.createOrder(amount);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null; // Handle the exception properly in your production code
	        }
	    }
	    
	    
	    @GetMapping("/payment")
	    public String showPaymentPage(Model model) {
	        try {
	            // Example customer data
	            int amount = 1000;  // Amount in paise (1000 paise = 10 INR)

	            // Create an order via the Razorpay service
	            Order order = razorpayService.createOrder(amount);

	            // Add variables to the model that will be injected into the frontend (Thymeleaf)
	            model.addAttribute("razorpayKeyId", "rzp_test_c9eACGghaxhe1W");  // Replace with actual Razorpay Key ID
	            model.addAttribute("orderId", order.get("id"));  // Order ID returned from the backend
	            model.addAttribute("amountInPaise", amount);  // Amount in paise (1000 paise = 10 INR)
	            model.addAttribute("customerName", "John Doe");
	            model.addAttribute("customerEmail", "john.doe@example.com");
	            model.addAttribute("customerContact", "1234567890");

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "error"; // Return an error page if the order creation fails
	        }

	        return "razorpay-payment";  // Thymeleaf template name
	    }

}
