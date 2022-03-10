package com.microservicios.ums.service;

import java.util.List;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

/**
 * The interface Keycloak interface.
 *
 * @author joseluis.anton
 */
public interface KeycloakInterface {

  /**
   * Gets the users.
   *
   * @return the users
   */
  List<UserRepresentation> getUsers();

  /**
   * Gets the users by its email.
   *
   * @param email the email
   *
   * @return the users by email
   */
  UserRepresentation getUsersByUsername(String email);

  /**
   * Gets the users in a group.
   *
   * @param groupId the group id
   *
   * @return the user by group id
   */
  List<UserRepresentation> getUsersByGroupId(String groupId);

  /**
   * Gets the user by its User Id.
   *
   * @param userId the user id
   *
   * @return the user
   */
  UserRepresentation getUser(String userId);


  /**
   * Do logout.
   *
   * @param userId the user id
   */
  void doLogout(String userId);

  /**
   * Gets the roles.
   *
   * @return the role
   */
  List<RoleRepresentation> getRoles();


  /**
   * Gets group by id.
   *
   * @param groupId the group id
   *
   * @return the group by id
   */
  GroupRepresentation getGroupById(String groupId);

  /**
   * Perform login action for a given user identified by username and password
   *
   * @param username the username
   * @param password the password
   *
   * @return the access token response
   */
  AccessTokenResponse doLogin(String username, String password);

  /**
   * Perform login action for a given user identified by a code
   *
   * @param code the code
   *
   * @return the access token response
   */
  AccessTokenResponse doLogin(String code);
}
