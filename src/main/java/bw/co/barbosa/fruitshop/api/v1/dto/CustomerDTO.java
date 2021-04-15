package bw.co.barbosa.fruitshop.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    Long id;
    String firstname;
    String lastname;

    @JsonProperty("customer_url")
    private String customerUrl;
}
