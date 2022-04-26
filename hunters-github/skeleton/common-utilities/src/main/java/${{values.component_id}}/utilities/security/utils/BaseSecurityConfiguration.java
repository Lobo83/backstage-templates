package  ${{values.component_id}}.utilities.security.utils;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Security configuration.
 */
@EnableWebSecurity
public abstract class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter {


  /**
   * The unauthorized handler.
   */
  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  /**
   * Jwt authentication filter jwt authentication filter.
   *
   * @return the jwt authentication filter
   */
  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  /**
   * The Constant LOG_ERROR.
   */
  private static final Logger LOG_ERROR = LoggerFactory.getLogger("error_logger");


  /**
   * Configure security.
   *
   * @param http the http
   *
   * @throws Exception the exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers("/actuator/**", "/swagger-ui.html", "/webjars/swagger-ui/**",
            "/v3/api-docs").permitAll()

    ;
    String[] authenticatedRequest = getAuthenticatedRequest();
    if (null != authenticatedRequest && authenticatedRequest.length > 0) {
      http.authorizeRequests().antMatchers(authenticatedRequest).authenticated();
    }
    String[] permitedRequest = getPermittedRequest();
    if (null != permitedRequest && permitedRequest.length > 0) {
      http.authorizeRequests().antMatchers(permitedRequest).permitAll();
    }

    List<Pair<String[], String>> roleProtectedRequest = getRoleProtectedRequest();
    if (null != roleProtectedRequest && !roleProtectedRequest.isEmpty()) {
      roleProtectedRequest.stream().forEach(pair -> {
        try {
          http.authorizeRequests().antMatchers(pair.getLeft()).hasRole(pair.getRight());
        } catch (Exception e) {
          LOG_ERROR.error("Exception in security configuration. Message: {}", e.getMessage(), e);
        }
      });

    }
    // Add our custom JWT security filter
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    performAdditionalConfiguration(http);

  }

  /**
   * Perform additional configuration.
   *
   * @param http the http
   */
  protected void performAdditionalConfiguration(HttpSecurity http) {
  }

  /**
   * Get authenticated request string [ ].
   *
   * @return the string [ ]
   */
  protected abstract String[] getAuthenticatedRequest();

  /**
   * Get permited request string [ ].
   *
   * @return the string [ ]
   */
  protected abstract String[] getPermittedRequest();

  /**
   * Gets role protected request. The result is a List of Pairs where left part contains the
   * endpoints to be secured and the right part the role that is authorized to invoke the endpoint
   *
   * @return the role protected request
   */
  protected abstract List<Pair<String[], String>> getRoleProtectedRequest();

}