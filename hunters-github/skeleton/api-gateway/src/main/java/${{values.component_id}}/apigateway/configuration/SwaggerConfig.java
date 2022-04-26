package ${{values.component_id}}.apigateway.configuration;

import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Swagger config.
 * @author joseluis.anton
 */
@Configuration
public class SwaggerConfig {



  /**
   * Open api groups command line runner.
   *
   * @param locator the locator
   * @param swaggerUiParameters the swagger ui parameters
   *
   * @return the command line runner
   */
  @Bean
  public CommandLineRunner openApiGroups(
      RouteDefinitionLocator locator,
      SwaggerUiConfigParameters swaggerUiParameters) {
    return args -> locator
        .getRouteDefinitions().collectList().block()
        .stream()
        .map(RouteDefinition::getId)
        .filter(id -> !id.startsWith("ReactiveCompositeDiscoveryClient") && id.matches(".*-service"))
        .forEach(swaggerUiParameters::addGroup);
  }

}
