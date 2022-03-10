package  ${{values.component_id}}.utilities.security.utils;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The type Jwt authentication filter.
 *
 * @author joseluis.anton
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String BEARER_TOKEN = "Bearer ";

  /**
   * The token provider.
   */
  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private AuthenticationUtils authenticationUtils;

  /**
   * The Constant LOG_ERROR.
   */
  private static final Logger LOG_ERROR = LoggerFactory.getLogger("error_logger");

  /**
   * Do filter internal.
   *
   * @param request the request
   * @param response the response
   * @param filterChain the filter chain
   *
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String jwt = getJwtFromRequest(request);

    try {
      AccessToken token = null;
      if (StringUtils.hasText(jwt) && (token = tokenProvider.parseToken(jwt)) != null) {
        authenticationUtils.performAuthentication(token, BEARER_TOKEN + jwt);
      }
    } catch (VerificationException e) {
      LOG_ERROR.error(
          "Could not set authentication security context: uri={}, token={}",
          request.getRequestURI(), jwt, e);
    }

    filterChain.doFilter(request, response);
  }


  /**
   * Gets the jwt from request.
   *
   * @param request the request
   *
   * @return the jwt from request
   */
  private String getJwtFromRequest(HttpServletRequest request) {

    String bearerToken = request.getHeader("Authorization");
    String jwt = null;
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TOKEN)) {
      jwt = bearerToken.substring(7, bearerToken.length());
    }

    return jwt;
  }
}

