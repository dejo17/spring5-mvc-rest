package hr.scorpiusmobile.springmvcrest.controllers.v1;


import hr.scorpiusmobile.springmvcrest.api.v1.model.VendorDTO;
import hr.scorpiusmobile.springmvcrest.controllers.RestResponseEntityExceptionHandler;
import hr.scorpiusmobile.springmvcrest.exceptions.ResourceNotFoundException;
import hr.scorpiusmobile.springmvcrest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static hr.scorpiusmobile.springmvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class VendorControllerTest {

    @Mock
    VendorService vendorService;
    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    void getAllVendors() throws Exception{
        when(vendorService.getAllVendors()).thenReturn(Arrays.asList(new VendorDTO(), new VendorDTO()));

        mockMvc.perform(get("/api/v1/vendors")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void getVendorById() throws Exception{

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Primorka");
        vendorDTO.setId(1L);
        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get("/api/v1/vendors/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalToIgnoringCase("Primorka")))
        .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void getVendorByIdNotFound() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/v1/vendors/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateNewVendor() throws Exception {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Dean");

        when(vendorService.createNewVendor(any())).thenReturn(vendorDTO);


        mockMvc.perform(post("/api/v1/vendors")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Dean")));

    }

    @Test
    void testUpdateVendor() throws Exception {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setName("Dean");

        when(vendorService.updateVendor(anyLong(), any())).thenReturn(vendorDTO);


        mockMvc.perform(put("/api/v1/vendors/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Dean")))
                .andExpect(jsonPath("$.id", equalTo(1)));

    }

    @Test
    void testPatchVendor() throws Exception {

        VendorDTO vendor = new VendorDTO();
        vendor.setName("Mirko");

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());

        when(vendorService.patchVendor(anyLong(), any())).thenReturn(vendorDTO);


        mockMvc.perform(patch("/api/v1/vendors/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Mirko")));

    }

    @Test
    public void testDeleteVendor() throws Exception {

        mockMvc.perform(delete("/api/v1/vendors/1"))
                .andExpect(status().isOk());
        verify(vendorService, times(1)).deleteVendorById(anyLong());
    }

}