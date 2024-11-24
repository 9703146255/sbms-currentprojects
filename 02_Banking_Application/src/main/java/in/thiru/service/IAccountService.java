package in.thiru.service;

import java.util.Map;
import java.util.Optional;

import in.thiru.entity.Account;

public  interface IAccountService{
	
	public Account createAccount(Account account);
	public Optional<Account> getAccount(Integer id);
	public Account deposite(Integer id, Map<String, Double> request);
	public Account withdraw(Integer id, Map<String, Double> request);
	public Account setAccount(Double amount);

}
