package com.microservicios.ums.service;

import com.microservicios.interfaces.ums.vo.UserVO;
import java.util.List;

/**
 * The interface User service.
 *
 * @author joseluis.anton
 */
public interface UserService {

  /**
   * Gets users.
   *
   * @return the users
   */
  List<UserVO> getUsers();

  /**
   * Gets user by id.
   *
   * @param userId the user id
   *
   * @return the user by id
   */
  UserVO getUserById(String userId);

  /**
   * Do logout.
   *
   * @param userId the user id
   */
  void doLogout(String userId);
}
