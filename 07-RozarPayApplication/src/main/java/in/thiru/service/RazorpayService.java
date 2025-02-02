package in.thiru.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class RazorpayService {

	 @Autowired
	    private RazorpayClient razorpayClient;  // RazorpayClient is injected from the configuration class

	    // Method to create an order
	    public Order createOrder(int amount) throws Exception {
	        // Create a JSON object with order details
	        JSONObject orderRequest = new JSONObject();
	        orderRequest.put("amount", amount * 100); // Amount in paise (100 paise = 1 INR)
	        orderRequest.put("currency", "INR");
	        orderRequest.put("payment_capture", 1);

	        // Create the order
	        return razorpayClient.orders.create(orderRequest);
	    }
	
	
	

}
