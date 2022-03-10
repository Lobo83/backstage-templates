package  ${{values.component_id}}.exampleservice.security;

import java.io.IOException;
import java.util.Base64;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MyFirstFilter extends OncePerRequestFilter {
  @Autowired
  @Lazy
  private AuthenticationManager authenticationManager;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws ServletException, IOException {

    String token=httpServletRequest.getHeader("Authorization");

    if(null!=token) {
      String decodedToken=new String(Base64.getDecoder().decode(token.replace("Basic ","")));
      String[] credentials = decodedToken.split(":");
      Authentication authentication = new UsernamePasswordAuthenticationToken(credentials[0],
          credentials[1]);
      authenticationManager.authenticate(authentication);
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
