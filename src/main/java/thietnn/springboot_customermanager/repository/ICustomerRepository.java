package thietnn.springboot_customermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thietnn.springboot_customermanager.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
