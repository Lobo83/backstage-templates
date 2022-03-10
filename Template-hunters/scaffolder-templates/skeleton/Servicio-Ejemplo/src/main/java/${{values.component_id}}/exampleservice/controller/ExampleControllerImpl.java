package  ${{values.component_id}}.exampleservice.controller;

import  ${{values.component_id}}.interfaces.example.ExampleController;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * The type Example controller.
 *
 * @author joseluis.anton
 */
@RestController
@RequestMapping("/entity")
@RefreshScope
public class ExampleControllerImpl implements ExampleController {

  @Value("${variable.example}")
  private String propiedad;

  @Override
  @GetMapping("/")
  @CircuitBreaker(name = "standard")
  @SecurityRequirement(name = "javainuseapi")
  @PreAuthorize("isAuthenticated()")
  public String getSaludo() {

    return "hola " + propiedad;
  }

  @GetMapping("/reactive")
  public Flux<String> getDataReactiveFashion() {
    Random random = new Random();

    List<String> result = new ArrayList<>();
    for (int j = 0; j < 10; j++) {
      result.add(random.ints(48, 122)
          .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
          .mapToObj(i -> (char) i)
          .limit(20)
          .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
          .toString());
    }

    // invocar en navegador a http://localhost:8081/entity/reactive
    // se ve como van llegando los elementos cada segundo, para simular llegada de datos de manera retardada
    // Ojo, swagger es bloqueante, por tanto, esperará a tener todos los datos para mostrar el resultado
    return Flux.fromStream(result.stream()).delayElements(Duration.ofSeconds(1));
  }
}
