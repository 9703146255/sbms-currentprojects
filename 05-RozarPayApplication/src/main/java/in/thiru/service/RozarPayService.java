package in.thiru.service;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class RozarPayService {
	
	 @Value("${razorpay.key.id}")
	    private String razorpayKeyId;

	    @Value("${razorpay.key.secret}")
	    private String razorpayKeySecret;

	    /**
	     * Creates an order with Razorpay (compatible with version 1.4.0).
	     * @param amountInPaise The amount in paise (100 paise = 1 INR)
	     * @return The created Razorpay order object
	     * @throws RazorpayException if there is an error in creating the order
	     */
	    public Order createOrder(int amountInPaise) throws RazorpayException {
	        // Create a Razorpay client with API key and secret
	        RazorpayClient client = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

	        // Create the order options (JSONObject for version 1.4.0)
	        JSONObject options = new JSONObject();
	        options.put("amount", amountInPaise);  // Amount in paise
	        options.put("currency", "INR");  // Currency in INR
	        options.put("receipt", "txn123456");  // Unique receipt ID (can be customized)

	        // Create an order with Razorpay
	        Order order = client.orders.create(options);

	        return order;  // Return the created order
	    }

	    /**
	     * Verifies the payment signature manually (for version 1.4.0).
	     * @param payload The Razorpay order ID and payment ID concatenated together
	     * @param razorpaySignature The signature sent by Razorpay to verify
	     * @return boolean Returns true if the signature is valid, false otherwise
	     */
	    public boolean verifyPaymentSignature(String payload, String razorpaySignature) {
	        try {
	            String computedSignature = computeSignature(payload, razorpayKeySecret);
	            return razorpaySignature.equals(computedSignature);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;  // Signature verification failed
	        }
	    }

	    /**
	     * Computes the HMAC SHA256 signature for the payload.
	     * @param payload The payload to verify
	     * @param secretKey The Razorpay secret key
	     * @return The computed signature
	     * @throws Exception If an error occurs while computing the signature
	     */
	    private String computeSignature(String payload, String secretKey) throws Exception {
	        String data = payload;  // Payload = "razorpay_order_id|razorpay_payment_id"
	        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
	        Mac mac = Mac.getInstance("HmacSHA256");
	        mac.init(secretKeySpec);
	        byte[] rawHmac = mac.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(rawHmac);
	    }

}
