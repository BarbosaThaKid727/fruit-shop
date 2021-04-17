package bw.co.barbosa.fruitshop.service;

import bw.co.barbosa.fruitshop.api.v1.dto.VendorDTO;
import bw.co.barbosa.fruitshop.api.v1.dto.VendorListDTO;

import java.util.List;

public interface VendorService {

    VendorListDTO getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
