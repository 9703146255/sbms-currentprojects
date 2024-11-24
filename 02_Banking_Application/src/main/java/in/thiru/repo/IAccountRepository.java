package in.thiru.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.thiru.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {

}
