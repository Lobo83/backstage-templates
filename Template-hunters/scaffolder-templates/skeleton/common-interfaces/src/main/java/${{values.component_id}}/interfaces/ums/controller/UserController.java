package com.microservicios.interfaces.ums.controller;

import com.microservicios.interfaces.example.ExampleController;
import com.microservicios.interfaces.ums.vo.UserVO;
import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The interface User controller.
 *
 * @author joseluis.anton
 */
public interface UserController {

  /**
   * The interface User controller feign.
   */
  @FeignClient(value = "user-management-service", contextId = "users", path = "/users")
  interface UserControllerFeign extends ExampleController {

  }

  /**
   * Gets users.
   *
   * @return the users
   */
  @GetMapping("/")
  List<UserVO> getUsers();

  /**
   * Gets user by id.
   *
   * @param userId the user id
   *
   * @return the user by id
   */
  @GetMapping("/{id}")
  UserVO getUserById(@PathVariable("id") String userId);

  /**
   * Do logout for the given user.
   *
   * @param userId the user id
   */
  @PostMapping("/{id}/logout")
  void doLogout(@PathVariable("id") String userId);
}
