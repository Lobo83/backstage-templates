package  ${{values.component_id}}.utilities.security.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

/**
 * The type base user details.
 *
 * @author joseluis.anton
 */
@Setter
@ToString
public class BaseUserDetails implements UserDetails {

  private static final long serialVersionUID = 5374139621703446667L;

  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;


  /**
   * Create base user details.
   *
   * @param username the username
   * @param roles the roles
   *
   * @return the eea user details
   */
  public static BaseUserDetails create(String username, Set<String> roles) {
    BaseUserDetails principal = new BaseUserDetails();
    principal.setUsername(username);
    List<GrantedAuthority> authorities = new ArrayList<>();
    if (!CollectionUtils.isEmpty(roles)) {
      roles.stream().forEach(role -> {
        if (!role.startsWith("ROLE_")) {
          authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        } else {
          authorities.add(new SimpleGrantedAuthority(role));
        }
      });
    }
    principal.setAuthorities(authorities);

    return principal;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseUserDetails principal = (BaseUserDetails) o;
    return username.equals(principal.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username);
  }
}
