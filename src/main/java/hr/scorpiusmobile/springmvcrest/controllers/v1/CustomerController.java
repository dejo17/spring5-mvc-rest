package hr.scorpiusmobile.springmvcrest.controllers.v1;

import hr.scorpiusmobile.springmvcrest.api.v1.model.CustomerDTO;
import hr.scorpiusmobile.springmvcrest.api.v1.model.CustomerListDTO;
import hr.scorpiusmobile.springmvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){
        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.listAllCustomers()), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id){
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(Long.valueOf(id)), HttpStatus.OK);
    }

}
