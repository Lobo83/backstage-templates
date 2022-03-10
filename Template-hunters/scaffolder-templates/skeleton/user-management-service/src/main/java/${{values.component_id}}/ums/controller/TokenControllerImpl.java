package  ${{values.component_id}}.ums.controller;

import  ${{values.component_id}}.interfaces.ums.controller.TokenController;
import  ${{values.component_id}}.interfaces.ums.vo.TokenVO;
import  ${{values.component_id}}.ums.service.TokenService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Token controller.
 *
 * @author joseluis.anton
 */
@RestController
@RequestMapping("/tokens")
@CircuitBreaker(name = "standard")
public class TokenControllerImpl implements TokenController {

  @Autowired
  private TokenService tokenService;

  @Override
  @PostMapping("")
  public TokenVO createToken(@RequestParam("username") String username,
      @RequestParam("password") String password) {
    return tokenService.createToken(username, password);
  }

  @Override
  @PostMapping("/code/{code}")
  public TokenVO createToken(@QueryParam("code") String code) {
    return tokenService.createToken(code);
  }
}
