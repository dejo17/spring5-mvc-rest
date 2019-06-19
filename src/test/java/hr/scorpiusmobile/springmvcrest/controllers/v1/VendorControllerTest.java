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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        mockMvc.perform(get("/api/v1/vendors/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void getVendorByName() throws Exception{

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Primorka");
        when(vendorService.getVendorByName(anyString())).thenReturn(vendorDTO);

        mockMvc.perform(get("/api/v1/vendors/Primorka")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalToIgnoringCase("Primorka")));
    }

    @Test
    void getVendorByNameNotFound() throws Exception {

        when(vendorService.getVendorByName(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/v1/vendors/Primorka")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}