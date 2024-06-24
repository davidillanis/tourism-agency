package com.tourism.configuration.other;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtUtils {

    @Autowired
    private JwtDecoder jwtDecoder;

    public boolean isTokenValid(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Set<String> getUserRole(String token) throws JSONException {
        Jwt jwt = jwtDecoder.decode(token);

        String resource=jwt.getClaims().get("resource_access").toString();
        resource=resource.replace("=",":");
        JSONArray rolesArray = new JSONObject(resource)
                .getJSONObject("spring-client-api-rest")
                .getJSONArray("roles");

        Set<String> roles=new HashSet<>();
        for(int i=0; i<rolesArray.length(); i++){
            roles.add(rolesArray.getString(i));
        }
        return roles;
    }
}
