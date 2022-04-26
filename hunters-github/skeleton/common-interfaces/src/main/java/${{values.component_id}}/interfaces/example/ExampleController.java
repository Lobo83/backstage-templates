package ${{values.component_id}}.interfaces.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The interface Example cotroller.
 * @author Hunters
 */
public interface ExampleController {

  /**
   * The interface Example cotroller feign.
   */
  @FeignClient(value = "ejemplo-servicio", contextId = "entity", path = "/entity")
  interface ExampleControllerFeign extends ExampleController {

  }

  /**
   * Gets saludo.
   */
  @GetMapping("/")
  String getSaludo();

}
