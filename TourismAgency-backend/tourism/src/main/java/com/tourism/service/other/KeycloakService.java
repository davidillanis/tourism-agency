package com.tourism.service.other;

import com.tourism.util.dto.UserRoleKeycloakDTO;
import org.json.JSONException;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.Set;

public interface KeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUsername(String username);
    String createUser(UserRoleKeycloakDTO userDTO);
    void deleteUser(String userId);
    void updateUser(String userId, UserRoleKeycloakDTO userDTO);
    boolean isValidToken(String token);
    Set<String> findRoleByToken(String token) throws JSONException;
}
