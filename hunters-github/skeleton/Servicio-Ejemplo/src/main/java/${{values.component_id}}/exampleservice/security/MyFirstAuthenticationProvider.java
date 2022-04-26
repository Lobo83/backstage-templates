package  ${{values.component_id}}.exampleservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyFirstAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private UserDetailsService myFirstUserDetailService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String name = authentication.getName();
    String password = authentication.getCredentials().toString();
    UserDetails userDetails = myFirstUserDetailService.loadUserByUsername(name);
    UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(auth);

    return auth;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
