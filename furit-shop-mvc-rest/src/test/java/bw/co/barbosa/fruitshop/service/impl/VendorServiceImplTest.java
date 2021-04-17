package bw.co.barbosa.fruitshop.service.impl;

import bw.co.barbosa.fruitshop.api.v1.dto.VendorDTO;
import bw.co.barbosa.fruitshop.api.v1.dto.VendorListDTO;
import bw.co.barbosa.fruitshop.api.v1.mapper.VendorMapper;
import bw.co.barbosa.fruitshop.exception.ResourceNotFoundException;
import bw.co.barbosa.fruitshop.model.Vendor;
import bw.co.barbosa.fruitshop.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class VendorServiceImplTest {

    private final String BASE_VENDOR_URL = "/api/v1/vendors/1";

    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    VendorServiceImpl vendorService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
    }

    @Test
    void getAllVendorsTest() {

        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());

        given(vendorRepository.findAll()).willReturn(vendors);
        VendorListDTO vendorDTOS = vendorService.getAllVendors();

        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorDTOS.getVendors().size(), is(equalTo(2)));
    }

    @Test
    void getVendorByIdTest() {

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Foo Bar");

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(vendor));
        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        assertEquals("Foo Bar", vendorDTO.getName());
    }

    @Test
    void createVendorTest() {

        VendorDTO vendorDTO= new VendorDTO();
        vendorDTO.setName("Foo Bar");

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);
        VendorDTO savedDTO = vendorService.createVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(BASE_VENDOR_URL, savedDTO.getVendorUrl());
    }

    @Test
    void updateVendorTest() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Foo Bar");

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);
        VendorDTO savedDTO = vendorService.updateVendor(1L, vendorDTO);

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(BASE_VENDOR_URL, savedDTO.getVendorUrl());
    }

    @Test
    void patchVendorTest() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Foo Bar");

        Vendor vendor = new Vendor();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO saverdDTO = vendorService.patchVendor(1L, vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(saverdDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    void deleteVendorByIdTest() {

        Long id = 1L;
        vendorRepository.deleteById(id);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Foo Bar");
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setId(2L);
        vendor.setName("Fee Bar");
        return vendor;
    }
}