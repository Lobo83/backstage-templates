package ${{values.component_id}}.ums;

import ${{values.component_id}}.utilities.security.EnableSecurity;
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
 * The type User management service application.
 *
 * @author joseluis.anton
 */
@SpringBootApplication
@EnableDiscoveryClient
@SecurityScheme(name = "jwt", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
//activa seguridad en swagger
@OpenAPIDefinition(
    servers = {
        @Server(url = "${openapi-url:http://localhost:8082}", description = "Default Server URL")
    }
)
@EnableCaching
@EnableSecurity
public class UserManagementServiceApplication {

  /**
   * Main.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    SpringApplication.run(UserManagementServiceApplication.class, args);
  }
}
