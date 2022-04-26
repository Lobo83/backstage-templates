package  ${{values.component_id}}.utilities.security.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.keycloak.representations.AccessToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * The type Authentication utils.
 *
 * @author joseluis.anton
 */
@Component
public class AuthenticationUtils {

  /**
   * Perform authentication. It sets the
   *
   * @param token the token
   * @param credentials the credentials
   */
  public void performAuthentication(AccessToken token, String credentials) {
    String username = token.getPreferredUsername();
    Set<String> authorities = token.getRealmAccess().getRoles();

    //get additional authorities if any
    Optional.ofNullable(getAdditionalAuthorities(token)).ifPresent(authorities::addAll);
    UserDetails userDetails = BaseUserDetails.create(username, authorities);
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(userDetails, credentials,
            userDetails.getAuthorities());

    Map<String, String> details = new HashMap<>();
    details.put(AuthenticationDetails.USER_ID, token.getSubject());

    //get additional details if any
    Optional.ofNullable(getDetails(token)).ifPresent(details::putAll);
    authentication.setDetails(details);

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }


  /**
   * Gets additional authorities to be added to the authentication token object.
   *
   * @param token the token
   *
   * @return the adicional authorities
   */
  protected Set<String> getAdditionalAuthorities(AccessToken token) {
    return null;
  }

  /**
   * Gets details to be added to the authentication token object.
   *
   * @param token the token
   *
   * @return the details
   */
  protected Map<String, String> getDetails(AccessToken token) {
    return null;
  }
}
