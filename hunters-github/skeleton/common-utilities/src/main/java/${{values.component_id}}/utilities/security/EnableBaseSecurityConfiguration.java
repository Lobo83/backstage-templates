package ${{values.component_id}}.utilities.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {EnableBaseSecurityConfiguration.class})
public class EnableBaseSecurityConfiguration {

}
