package in.thiru.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.thiru.entity.Account;
import in.thiru.service.IAccountService;

@RestController
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@PostMapping("/account")
	public Account createAccount(@RequestBody Account account) {
		Account createAccount = accountService.createAccount(account);

		return createAccount;
	}

	@GetMapping("/account/{id}")
	public Account getAccountDetails(@PathVariable Integer id) {
		return accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account Not found"));
	}

	@PostMapping("/account/{id}/deposite")
	public String deposite(@PathVariable("id") Integer id,@RequestBody Map<String, Double> request) {
		
		Account deposite = accountService.deposite(id, request);
		return "YOUR ACCOUNT DEPOSITED SUCCESSFULLY :: "+deposite;
	}
	
	@PostMapping("/account/{id}/withdraw")
	public String withdraw(@PathVariable("id") Integer id,@RequestBody Map<String, Double> request) {
		
		Account withdraw = accountService.withdraw(id, request);
		System.out.println("withdraw amount :: "+withdraw);
		return "YOUR ACCOUNT DEPOSITED SUCCESSFULLY:- YOUR TOTAL BALANCE IS :: "+withdraw.getBalance();
	}

}
