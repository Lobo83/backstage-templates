package ${{values.component_id}}.exampleservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The type Example service application.
 *
 * @author joseluis.anton
 */
@SpringBootApplication
@EnableDiscoveryClient
@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
//activa seguridad en swagger
//@SecurityRequirement(name = "javainuseapi") --> deberá ser usado en los controllers que requieran seguirdad
@OpenAPIDefinition(
    servers = {
        @Server(url = "http://localhost:8081", description = "Default Server URL")
    }
)
@EnableCaching
public class ExampleServiceApplication {

  /**
   * Main.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    SpringApplication.run(ExampleServiceApplication.class, args);
  }


}
