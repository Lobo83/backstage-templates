package com.microservicios.ums.controller;


import com.microservicios.interfaces.ums.controller.UserController;
import com.microservicios.interfaces.ums.vo.UserVO;
import com.microservicios.ums.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 *
 * @author joseluis.anton
 */
@RestController
@RequestMapping("/users")
@CircuitBreaker(name = "standard")
public class UserControllerImpl implements UserController {

  @Autowired
  private UserService userService;

  @Override
  @GetMapping("/")
  public List<UserVO> getUsers() {
    return userService.getUsers();
  }

  @Override
  @GetMapping("/{id}")
  public UserVO getUserById(@PathVariable("id") String userId) {
    return userService.getUserById(userId);
  }

  @Override
  @PostMapping("/{id}/logout")
  public void doLogout(@PathVariable("id") String userId) {
    userService.doLogout(userId);
  }


}
