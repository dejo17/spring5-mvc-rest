package hr.scorpiusmobile.springmvcrest.services;


import hr.scorpiusmobile.springmvcrest.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();
    VendorDTO getVendorByName(String name);

}
