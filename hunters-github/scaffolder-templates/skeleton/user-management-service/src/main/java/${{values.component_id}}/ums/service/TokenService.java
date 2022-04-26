package ${{values.component_id}}.ums.service;

import ${{values.component_id}}.interfaces.ums.vo.TokenVO;

/**
 * The interface Token service.
 *
 * @author joseluis.anton
 */
public interface TokenService {

  /**
   * Create token token vo.
   *
   * @param username the user name
   * @param password the password
   *
   * @return the token vo
   */
  TokenVO createToken(String username, String password);

  /**
   * Create token token vo.
   *
   * @param code the code
   *
   * @return the token vo
   */
  TokenVO createToken(String code);
}

