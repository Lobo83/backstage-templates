package com.microservicios.interfaces.ums.vo;

import lombok.Data;

/**
 * The type Token vo.
 */
@Data
public class TokenVO {

  private String token;
  private long expiresIn;
  private long refreshExpiresIn;
  private String refreshToken;

}
