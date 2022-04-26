package  ${{values.component_id}}.utilities.security.utils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * The type Eea feign security interceptor.
 *
 * @author Jose Luis Antón Bueso
 */
@Component
@Slf4j
public class FeignSecurityInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION_HEADER = "Authorization";


  @Override
  public void apply(RequestTemplate template) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();

    if (authentication instanceof UsernamePasswordAuthenticationToken) {
      log.info("Securing invocation to {} with token {} and user {}", template.url(),
          authentication.getCredentials().toString(), authentication.getName());
      template.header(AUTHORIZATION_HEADER, getValidAuthorizationHeaderValue(authentication));
    }
  }

  /**
   * Gets valid authorization header value. This methods is meant to be extended to ensure that the
   * value sent in Authorization header is valid since it could happen that in some call the token
   * gets expired
   *
   * @param authentication the authentication
   *
   * @return the valid authorization header value
   */
  protected String getValidAuthorizationHeaderValue(Authentication authentication) {
    return authentication.getCredentials().toString();
  }

}
