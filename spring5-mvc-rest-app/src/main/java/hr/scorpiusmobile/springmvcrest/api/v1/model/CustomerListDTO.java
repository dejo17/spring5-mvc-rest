package hr.scorpiusmobile.springmvcrest.api.v1.model;

import hr.scorpiusmobile.springmvcrest.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerListDTO {
    private List<CustomerDTO> customers;
}
