package hr.scorpiusmobile.springmvcrest.services;

import hr.scorpiusmobile.springmvcrest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    public List<CustomerDTO> listAllCustomers();
    public CustomerDTO getCustomerById(Long id);
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO);  //for post
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);  //for put
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);    //for patch
    void deleteCustomerById(Long id); //for delete
}
