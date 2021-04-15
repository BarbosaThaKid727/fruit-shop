package bw.co.barbosa.fruitshop.repository;

import bw.co.barbosa.fruitshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
