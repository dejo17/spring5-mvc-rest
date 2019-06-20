package hr.scorpiusmobile.springmvcrest.services;


import hr.scorpiusmobile.springmvcrest.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    public List<VendorDTO> getAllVendors();
    public VendorDTO getVendorById(Long id);
    public VendorDTO createNewVendor(VendorDTO vendorDTO);  //for post
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO);  //for put
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO);    //for patch
    void deleteVendorById(Long id); //for delete
}
