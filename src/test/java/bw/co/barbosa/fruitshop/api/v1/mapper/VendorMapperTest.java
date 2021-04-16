package bw.co.barbosa.fruitshop.api.v1.mapper;

import bw.co.barbosa.fruitshop.api.v1.dto.VendorDTO;
import bw.co.barbosa.fruitshop.model.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void vendorToVendorDTO() {

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Foo Bar");

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(1L, vendorDTO.getId());
        assertEquals("Foo Bar", vendorDTO.getName());
    }
}