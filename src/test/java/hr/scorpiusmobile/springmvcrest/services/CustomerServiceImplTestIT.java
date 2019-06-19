package hr.scorpiusmobile.springmvcrest.services;

import hr.scorpiusmobile.springmvcrest.api.v1.mapper.CustomerMapper;
import hr.scorpiusmobile.springmvcrest.api.v1.model.CustomerDTO;
import hr.scorpiusmobile.springmvcrest.dataloader.DataLoader;
import hr.scorpiusmobile.springmvcrest.domain.Customer;
import hr.scorpiusmobile.springmvcrest.repositories.CategoryRepository;
import hr.scorpiusmobile.springmvcrest.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.CustomMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
class CustomerServiceImplTestIT {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() throws Exception {

        log.debug("Loading customer data in integration test");

        DataLoader dataLoader = new DataLoader(categoryRepository, customerRepository);
        dataLoader.run();
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void testPatchCustomerFirstName() throws Exception {

        String updatedName = "Some New Name";
        long id = getCustomerIdValue();  //we are getting ID which is for sure there with this helper method

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.getOne(id);

        assertNotNull(updatedCustomer);

        assertEquals(updatedName, updatedCustomer.getLastName());
        assertEquals(originalFirstName, updatedCustomer.getFirstName());

        //another way of doing assertions:
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));


    }


    /**
     * this method returns first customer ID from repository. In this way we are mimicking search for specific ID,
     * which is not easy to do because database is freshly populated before each test. ID values are increasing after every
     * setUp() method. So, we don't have any value to search for because they are every time different, so we just take the
     * first one for testing purposes.
     *
     * @return first customer ID from repository
     */
    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers Found: " + customers.size());

        //return first id
        return customers.get(0).getId();
    }

}