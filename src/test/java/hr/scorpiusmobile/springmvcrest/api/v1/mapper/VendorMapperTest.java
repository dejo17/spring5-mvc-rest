package hr.scorpiusmobile.springmvcrest.api.v1.mapper;

import hr.scorpiusmobile.springmvcrest.api.v1.model.VendorDTO;
import hr.scorpiusmobile.springmvcrest.domain.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void vendorToVendorDTO() {

        Vendor vendor = new Vendor();
        vendor.setName("Primorka");
        vendor.setId(1L);

        VendorDTO vendorDTO= vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());
        assertEquals(vendor.getId(), vendorDTO.getId());
    }

}