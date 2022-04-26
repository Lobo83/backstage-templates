package  ${{values.component_id}}.ums.configuration;

import  ${{values.component_id}}.utilities.security.utils.BaseSecurityConfiguration;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

/**
 * The type Ums security configuration.
 *
 * @author joseluis.anton
 */
//@Configuration
public class UMSSecurityConfiguration extends BaseSecurityConfiguration {

  @Override
  protected String[] getAuthenticatedRequest() {
    return new String[]{"/users/**"};
  }

  @Override
  protected String[] getPermittedRequest() {
    return new String[]{"/tokens/**"};
  }

  @Override
  protected List<Pair<String[], String>> getRoleProtectedRequest() {
    return null;
  }
}
