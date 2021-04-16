package bw.co.barbosa.fruitshop.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    Long id;
    private String name;

    @JsonProperty("vendor_id")
    private String vendorUrl;
}
