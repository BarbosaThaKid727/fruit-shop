package bw.co.barbosa.fruitshop.api.v1.mapper;

import bw.co.barbosa.fruitshop.api.v1.dto.VendorDTO;
import bw.co.barbosa.fruitshop.model.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}
