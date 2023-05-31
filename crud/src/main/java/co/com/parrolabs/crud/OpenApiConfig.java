package co.com.parrolabs.crud;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Crud Parrolabs")
                        .version("1.0.0")
                        .description("CRUD for Order, Clinet and product")
                        .contact(new Contact()
                                .name("Luis Felipe Marin Cano")
                                .email("luisfelipemarincano@gmail.com")));
    }
}
