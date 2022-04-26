package  ${{values.component_id}}.utilities.security.utils;

import static org.keycloak.TokenVerifier.IS_ACTIVE;
import static org.keycloak.TokenVerifier.SUBJECT_EXISTS_CHECK;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The type Jwt token provider.
 *
 * @author joseluis.anton
 */
@Component

/** The Constant log. */
@Slf4j
public class JwtTokenProvider {

  /**
   * The public key value.
   */

  @Value("${keycloak.publicKey}")
  private String publicKeyValue;

  /**
   * The public key.
   */
  private PublicKey publicKey;


  /**
   * Parse a JWT Token extracting the more relevant data from the JWT into a TokenDataVO.
   *
   * @param jwt the jwt token
   *
   * @return the token data vo
   *
   * @throws VerificationException the verification exception
   * @see AccessToken
   */
  public AccessToken parseToken(String jwt) throws VerificationException {

    TokenVerifier tokenVerifier = TokenVerifier.create(jwt, AccessToken.class).publicKey(publicKey)
        .withChecks(SUBJECT_EXISTS_CHECK, IS_ACTIVE).verify();

    return (AccessToken) tokenVerifier.getToken();
  }


  /**
   * Create public key.
   *
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws InvalidKeySpecException the invalid key spec exception
   * @throws CertificateException the certificate exception
   */
  @PostConstruct
  private void createPublicKey()
      throws NoSuchAlgorithmException, InvalidKeySpecException, CertificateException {
    // Configure publicKey for Keycloak as it is the main Token Generator
    this.publicKey = retrievePubliKeyFromString(publicKeyValue);

  }

  /**
   * Retrieve publi key from string.
   *
   * @param publicKeyValue the public key value
   *
   * @return the public key
   *
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws InvalidKeySpecException the invalid key spec exception
   */
  private PublicKey retrievePubliKeyFromString(String publicKeyValue)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    X509EncodedKeySpec X509publicKey =
        new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyValue.getBytes()));
    KeyFactory kf = null;
    kf = KeyFactory.getInstance("RSA");
    return kf.generatePublic(X509publicKey);
  }
}
