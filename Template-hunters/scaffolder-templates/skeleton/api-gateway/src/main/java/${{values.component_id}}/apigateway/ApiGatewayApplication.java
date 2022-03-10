package ${{values.component_id}}.apigateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;


/**
 * The type Api gateway application.
 *
 * @author joseluis.anton
 */
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class ApiGatewayApplication {

  /**
   * Main.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayApplication.class, args);
  }


}
