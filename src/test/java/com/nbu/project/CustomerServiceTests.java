package com.nbu.project;

import com.nbu.project.entities.Customer;
import com.nbu.project.repos.CustomerRepository;
import com.nbu.project.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RequiredArgsConstructor
public class CustomerServiceTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    void testCreateCustomer() {
        final CustomerRepository customerRepository = new CustomerRepository(jdbcTemplate);
        final CustomerService customerService = new CustomerService(customerRepository);
        final Customer customer = new Customer("johndoe@mail.com");

        final Customer result = customerService.createCustomer(customer);
        final String sql = "INSERT INTO customer (email) VALUES (?)";
        final String email = customer.email();
        verify(jdbcTemplate, times(1)).update(sql, email);
        Assertions.assertEquals(customer, result);
    }

    @Test
    void testUpdate() {
        final Customer customer = new Customer("johndoe@mail.com");
        final CustomerRepository customerRepository = new CustomerRepository(jdbcTemplate);
        final CustomerService customerService = new CustomerService(customerRepository);

        customerService.update(customer);
        final String expectedQuery = "UPDATE customer SET email WHERE email = ?";

        verify(jdbcTemplate, times(1)).update(expectedQuery, customer.email());
    }
}
