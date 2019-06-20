package hr.scorpiusmobile.springmvcrest.controllers.v1;

import hr.scorpiusmobile.springmvcrest.api.v1.model.CustomerDTO;
import hr.scorpiusmobile.springmvcrest.api.v1.model.CustomerListDTO;
import hr.scorpiusmobile.springmvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers(){
        return new CustomerListDTO(customerService.listAllCustomers());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable String id){
        return customerService.getCustomerById(Long.valueOf(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(id, customerDTO);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.patchCustomer(id, customerDTO);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        return;
    }
}
