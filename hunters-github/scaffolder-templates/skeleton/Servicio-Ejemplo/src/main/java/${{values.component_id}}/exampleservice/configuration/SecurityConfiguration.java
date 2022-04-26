package  ${{values.component_id}}.exampleservice.configuration;

import  ${{values.component_id}}.exampleservice.security.MyFirstAuthenticationEntryPoint;
import  ${{values.component_id}}.exampleservice.security.MyFirstAuthenticationProvider;
import  ${{values.component_id}}.exampleservice.security.MyFirstFilter;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private MyFirstAuthenticationProvider myFirstAuthenticationProvider;

  @Autowired
  private MyFirstAuthenticationEntryPoint myFirstAuthenticationEntryPoint;
  @Autowired
  private MyFirstFilter myFirstFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable().headers().frameOptions().sameOrigin().and().exceptionHandling().authenticationEntryPoint(myFirstAuthenticationEntryPoint).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/h2-console","/entity/**").permitAll()
        .antMatchers("/ejemplo/**").hasAnyRole("ADMIN","USER").and().logout().logoutUrl("/logout");;

    http.addFilterBefore(myFirstFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    AuthenticationManager authenticationManager = new ProviderManager(Arrays.asList((AuthenticationProvider) myFirstAuthenticationProvider));
    ((ProviderManager) authenticationManager).setEraseCredentialsAfterAuthentication(false);//sin esto, las credenciales se borran del objeto authentication y no se pueden obtener para transmitir en llamadas feign
    return authenticationManager;
  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.authenticationProvider((AuthenticationProvider) myFirstAuthenticationProvider);
//  }

}
