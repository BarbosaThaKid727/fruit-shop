package bw.co.barbosa.fruitshop.api.v1.dto;

import bw.co.barbosa.fruitshop.model.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorListDTO {

    List<Vendor> vendors;
}
