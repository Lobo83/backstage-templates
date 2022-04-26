package ${{values.component_id}}.interfaces.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * The Class InterfacesConfig.
 *
 * @author joseluis.anton
 */
@Configuration
@EnableFeignClients("${{values.component_id}}.interfaces")
public class InterfacesConfig {

}
