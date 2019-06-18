package hr.scorpiusmobile.springmvcrest.repositories;

import hr.scorpiusmobile.springmvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
