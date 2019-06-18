package hr.scorpiusmobile.springmvcrest.api.v1.mapper;

import hr.scorpiusmobile.springmvcrest.api.v1.model.CustomerDTO;
import hr.scorpiusmobile.springmvcrest.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDTO() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Djuro");
        customer.setLastName("Peric");

        CustomerDTO convertedCustomer = customerMapper.customerToCustomerDTO(customer);

        assertEquals(convertedCustomer.getId(), customer.getId());
        assertEquals(convertedCustomer.getFirstName(),customer.getFirstName());
        assertEquals(convertedCustomer.getLastName(),customer.getLastName());

    }
}