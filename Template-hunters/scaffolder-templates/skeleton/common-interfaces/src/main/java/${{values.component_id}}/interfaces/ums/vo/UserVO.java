package ${{values.component_id}}.interfaces.ums.vo;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class UserVO {

  private String id;
  private String username;
  private Boolean enabled;
  private Boolean emailVerified;
  private String firstName;
  private String lastName;
  private String email;
  private List<String> realmRoles;
  private Map<String, List<String>> clientRoles;
}
