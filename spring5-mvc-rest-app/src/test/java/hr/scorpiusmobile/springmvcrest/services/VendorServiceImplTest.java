package hr.scorpiusmobile.springmvcrest.services;

import hr.scorpiusmobile.springmvcrest.api.v1.mapper.VendorMapper;
import hr.scorpiusmobile.springmvcrest.api.v1.model.CustomerDTO;
import hr.scorpiusmobile.springmvcrest.api.v1.model.VendorDTO;
import hr.scorpiusmobile.springmvcrest.domain.Vendor;
import hr.scorpiusmobile.springmvcrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendorServiceImplTest {

    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @InjectMocks
    VendorServiceImpl vendorService;

    public static final String NAME_1 = "Primorka";
    public static final String NAME_2 = "Vera";
    public static final long ID_1 = 1L;
    public static final long ID_2 = 2L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vendorService  = new VendorServiceImpl(vendorRepository, vendorMapper);
    }

    @Test
    void getAllVendors() {

        Vendor vendor1 = new Vendor();

        Vendor vendor2 = new Vendor();
        vendor1.setId(ID_1);
        vendor1.setName(NAME_1);
        vendor2.setId(ID_2);
        vendor2.setName(NAME_2);
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(vendor1);
        vendors.add(vendor2);

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOSDTOs = new ArrayList<>();

        vendorDTOSDTOs = vendorService.getAllVendors();

        assertEquals(vendors.size(), vendorDTOSDTOs.size());
    }

    @Test
    void getVendorById() {

        Vendor vendor = new  Vendor();
        vendor.setId(ID_1);
        vendor.setName(NAME_1);

        when(vendorRepository.getOne(anyLong())).thenReturn(vendor);

        VendorDTO returnedVendor = vendorService.getVendorById(ID_1);

        assertEquals(ID_1, returnedVendor.getId());
        assertEquals(NAME_1, returnedVendor.getName());

    }

    @Test
    void createNewCustomer() throws Exception{

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setName("Dean");

        when(vendorRepository.save(any())).thenReturn(vendorMapper.vendorDTOtoVendor(vendorDTO));

        VendorDTO returnedVendor = vendorService.createNewVendor(vendorDTO);

        assertNotNull(returnedVendor);
        assertEquals(vendorDTO.getId(), returnedVendor.getId());
        verify(vendorRepository, times(1)).save(any());

    }

    @Test
    void testDeleteCustomer() throws Exception {

        vendorService.deleteVendorById(1L);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}