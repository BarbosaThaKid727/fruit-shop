package bw.co.barbosa.fruitshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String CUSTOMER_TAG = "Customer Controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .apiInfo(tags());
    }

    private ApiInfo metaData() {

        Contact contact = new Contact("Bosa Matlhare", "https://fruitshop.barbosa.co.bw", "barbosa@fruitshop.co.bw");

        return new ApiInfo(
                "Barbosa Inc.",
                "Fruit-Shop",
                "1.0",
                "Terms of Service: blah",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }

    private ApiInfo tags() {
        return new ApiInfoBuilder().title("My Customer Controller").version("1.0.0").build();
    }
}
