package in.thiru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.thiru.model.Customer;



@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer>{

}
