package  ${{values.component_id}}.exampleservice.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FeignSecurityInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION_HEADER = "Authorization";


  @Override
  public void apply(RequestTemplate template) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();

    if (authentication instanceof UsernamePasswordAuthenticationToken) {
      StringBuilder stringBuilder = new StringBuilder(authentication.getName()).append(":").append(authentication.getCredentials());

      String encodedToken=new String(Base64.getEncoder().encode(stringBuilder.toString().getBytes()));

      log.info("Securing invocation to {} with token {} and user {}", template.url(),
          authentication.getCredentials().toString(), authentication.getName());
      template.header(AUTHORIZATION_HEADER, "Basic "+encodedToken);
    }
  }

}
