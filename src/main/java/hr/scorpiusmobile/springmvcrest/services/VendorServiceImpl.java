package hr.scorpiusmobile.springmvcrest.services;

import hr.scorpiusmobile.springmvcrest.api.v1.mapper.VendorMapper;
import hr.scorpiusmobile.springmvcrest.api.v1.model.VendorDTO;
import hr.scorpiusmobile.springmvcrest.domain.Vendor;
import hr.scorpiusmobile.springmvcrest.exceptions.ResourceNotFoundException;
import hr.scorpiusmobile.springmvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements  VendorService{

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return  vendorRepository.findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorMapper.vendorToVendorDTO(vendorRepository.getOne(id));
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO savedVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        return savedVendorDTO;
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(id);
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO savedVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        return savedVendorDTO;
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }
            return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
