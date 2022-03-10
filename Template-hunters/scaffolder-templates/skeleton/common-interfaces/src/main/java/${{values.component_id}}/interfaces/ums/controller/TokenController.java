package com.microservicios.interfaces.ums.controller;

import com.microservicios.interfaces.ums.vo.TokenVO;
import javax.ws.rs.QueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Token controller.
 *
 * @author joseluis.anton
 */
public interface TokenController {

  /**
   * The interface Token controller feign.
   */
  @FeignClient(value = "user-management-service", contextId = "tokens", path = "/tokens")
  interface TokenControllerFeign extends TokenController {

  }

  /**
   * Create token token vo.
   *
   * @param username the username
   * @param password the password
   *
   * @return the token vo
   */
  @PostMapping("")
  TokenVO createToken(@RequestParam("username") String username,
      @RequestParam("password") String password);

  /**
   * Create token token vo.
   *
   * @param code the code
   *
   * @return the token vo
   */
  @PostMapping("/code/{code}")
  TokenVO createToken(@PathVariable("code") String code);
}
