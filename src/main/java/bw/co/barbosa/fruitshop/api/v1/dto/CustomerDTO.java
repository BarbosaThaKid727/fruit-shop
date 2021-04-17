package bw.co.barbosa.fruitshop.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @ApiModelProperty(value = "This is the first name", required = true)
    String firstname;

    @ApiModelProperty(value = "This is the last name", required = true)
    String lastname;

    @JsonProperty("customer_url")
    private String customerUrl;
}
