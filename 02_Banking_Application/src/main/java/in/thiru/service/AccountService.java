package in.thiru.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.thiru.entity.Account;
import in.thiru.repo.IAccountRepository;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository repository;

	@Override
	public Account createAccount(Account account) {
		Account accountSaved = repository.save(account);
		return accountSaved;
	}

	@Override
	public Optional<Account> getAccount(Integer id) {

		return repository.findById(id);

	}

	@Override
	public Account deposite(Integer id, Map<String, Double> request) {

		Account account = new Account();

		Double amount = request.get("amount");

		Optional<Account> accountObject = repository.findById(id);

		double balance = accountObject.get().getBalance();

		if (accountObject.isPresent()) {
			Account account2 = accountObject.get();
			account.setBalance(amount + balance);
			account.setId(account2.getId());
			account.setAccountHolderName(account2.getAccountHolderName());
		}

		repository.save(account);

		return null;
	}

	@Override
	public Account withdraw(Integer id, Map<String, Double> request) {

		Account account = new Account();

		Double amount = request.get("amount");

		Optional<Account> dbAccountObject = repository.findById(id);

		double dbBalance = dbAccountObject.get().getBalance();

		if (dbBalance > amount) {

			if (dbAccountObject.isPresent()) {
				Account account2 = dbAccountObject.get();
				account.setBalance(dbBalance-amount);
				account.setId(account2.getId());
				account.setAccountHolderName(account2.getAccountHolderName());
			}

		}

		Account save = repository.save(account);

		return save;
	}

	@Override
	public Account setAccount(Double amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
