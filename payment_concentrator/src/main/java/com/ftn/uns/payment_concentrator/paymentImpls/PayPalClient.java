package com.ftn.uns.payment_concentrator.paymentImpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ftn.uns.payment_concentrator.paymentInterface.PaymentInterface;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payee;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PayPalClient implements PaymentInterface{
	public PayPalClient(){}
	
	private String clientId = "AYiZ1NVxzcFb0aHZnHSCaTqpeJpZf6ZSwJxoeFTKLAnvOktF8nOF0P3zcoL2FW3yRcSgyrxyHakUyoRl";
	private String clientSecret = "ELTcYHBS5VuNby6wuz1Rn9wzHGbiWVGvnJaZuQ2Qn4QlrhoubqIJxl0HiencysO3KrkU8q4_6aWMkKJB";
	private String adress = "http://localhost:4200";
	
	@Override
	public Map<String, Object> create(String sum) {
		System.out.println("USAO U PAYPAL");
		Map<String, Object> response = new HashMap<String, Object>();
	    Amount amount = new Amount();
	    amount.setCurrency("USD");
	    amount.setTotal(sum);

	    Transaction transaction = new Transaction();
	    Payee payee = new Payee();
	    payee.setEmail("vladimirjovicic95@yahoo.com");	// seller
	    transaction.setAmount(amount);
	    transaction.setPayee(payee);
	    List<Transaction> transactions = new ArrayList<Transaction>();
	    transactions.add(transaction);
	    Payer payer = new Payer();
	    payer.setPaymentMethod("paypal");

	    Payment payment = new Payment();
	    payment.setIntent("sale");
	    payment.setPayer(payer);
	    payment.setTransactions(transactions);

	    RedirectUrls redirectUrls = new RedirectUrls();
	    redirectUrls.setCancelUrl(adress+"/cancelPaypal");
	    redirectUrls.setReturnUrl(adress+"/paypalsucces");
	    payment.setRedirectUrls(redirectUrls);
	    Payment createdPayment;
	    try {
	        String redirectUrl = "";
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        createdPayment = payment.create(context);
	        if(createdPayment!=null){
	            List<Links> links = createdPayment.getLinks();
	            for (Links link:links) {
	                if(link.getRel().equals("approval_url")){
	                    redirectUrl = link.getHref();
	                    break;
	                }
	            }
	            response.put("status", "success");
	            response.put("redirect_url", redirectUrl);
	        }
	    } catch (PayPalRESTException e) {
	        System.out.println("Error happened during payment creation!");
	    }
	    return response;
	}

	@Override
	public Map<String, Object> complete(HttpServletRequest req) {
		Map<String, Object> response = new HashMap<String, Object>();
	    Payment payment = new Payment();
	    payment.setId(req.getParameter("paymentId"));
	    /*System.out.println("Payment iD: "  + req.getParameter("paymentId"));
	    System.out.println("Payer ID: " + req.getParameter("payerId"));*/
	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(req.getParameter("payerId"));
	    try {
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        Payment createdPayment = payment.execute(context, paymentExecution);
	        if(createdPayment!=null){
	            response.put("status", "success");
	            response.put("payment", createdPayment);
	        }
	    } catch (PayPalRESTException e) {
	        //System.err.println(e.getDetails());
	    }
	    return response;
	}
}
