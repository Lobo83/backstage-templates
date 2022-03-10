package  ${{values.component_id}}.exampleservice.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyFirstUserDetailService implements UserDetailsService {


  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    UserDetails userDetails=new MyFirstUserDetails() ;
      List<GrantedAuthority> authorityList = new ArrayList<>();
      GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
    authorityList.add(authority);
    if(userName.equals("alice")){
      ((MyFirstUserDetails) userDetails).setUsername("alice");
      ((MyFirstUserDetails) userDetails).setPassword("123");
      ((MyFirstUserDetails) userDetails).setAuthorities(authorityList);
    }
    if(userName.equals("bernard")){
      ((MyFirstUserDetails) userDetails).setUsername("bernard");
      ((MyFirstUserDetails) userDetails).setPassword("123");
      ((MyFirstUserDetails) userDetails).setAuthorities(authorityList);
    }
    return userDetails;
  }
}
