package bw.co.barbosa.fruitshop.service.impl;

import bw.co.barbosa.fruitshop.api.v1.dto.VendorDTO;
import bw.co.barbosa.fruitshop.api.v1.mapper.VendorMapper;
import bw.co.barbosa.fruitshop.exception.ResourceNotFoundException;
import bw.co.barbosa.fruitshop.model.Vendor;
import bw.co.barbosa.fruitshop.repository.VendorRepository;
import bw.co.barbosa.fruitshop.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VendorServiceImpl implements VendorService {

    private static final String VENDOR_URL = "/api/v1/vendors";
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {

        log.info("Fetching all vendors");
        return vendorRepository
                .findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {

        log.info("Fetching vendor with id: " + id);
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {

        log.info("Creating a new Vendor");
        return convert(vendorMapper.vendorDtoToVendor(vendorDTO));
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {

        log.info("Updating vendor with ID: " + id);
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);
        return convert(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {

        log.info("Updating vendor with id: " + id);
        return vendorRepository.findById(id)
                .map( vendor -> {
                    if (vendorDTO.getName() != null) {
                        vendor.setName(vendorDTO.getName());
                    }
                    return convert(vendor);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {

        log.info("Deleting Vendor with ID: " + id);
        vendorRepository.deleteById(id);
    }

    private VendorDTO convert(Vendor vendor) {

        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnedDTO = vendorMapper.vendorToVendorDTO(vendor);
        returnedDTO.setVendorUrl(VENDOR_URL + "/" + savedVendor.getId());
        return returnedDTO;
    }
}
