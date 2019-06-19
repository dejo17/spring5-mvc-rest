package hr.scorpiusmobile.springmvcrest.services;

import hr.scorpiusmobile.springmvcrest.api.v1.mapper.CustomerMapper;
import hr.scorpiusmobile.springmvcrest.api.v1.model.CustomerDTO;
import hr.scorpiusmobile.springmvcrest.domain.Customer;
import hr.scorpiusmobile.springmvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository,CustomerMapper.INSTANCE);
    }

    @Test
    void listAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer()));

        List<CustomerDTO> customers = customerService.listAllCustomers();

        assertEquals(customers.size(), 2L);
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setFirstName("Djuro");
        customer.setLastName("Peric");
        customer.setId(1L);
        when(customerRepository.getOne(anyLong())).thenReturn(customer);

        CustomerDTO returnedCustomer = customerService.getCustomerById(1L);

        assertNotNull(returnedCustomer);
        assertEquals(returnedCustomer.getId(), 1L);
        assertEquals(returnedCustomer.getFirstName(), "Djuro");
    }

    @Test
    void createNewCustomer() throws Exception{

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("Dean");

        when(customerRepository.save(any())).thenReturn(customerMapper.customerDTOtoCustomer(customerDTO));

        CustomerDTO returnedCustomer = customerService.createNewCustomer(customerDTO);

        assertNotNull(returnedCustomer);
        assertEquals(customerDTO.getId(), returnedCustomer.getId());
        verify(customerRepository, times(1)).save(any());

    }

    @Test
    void testDeleteCustomer() throws Exception {

        customerService.deleteCustomerById(1L);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }

}