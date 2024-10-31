package ru.netology.websecurityconfigureradapter.config;

import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import ru.netology.websecurityconfigureradapter.dto.UserDto;

import java.util.Collections;

//@Configuration
@Service //in javabegin
public class KeycloakConfig {
    @Value("${keycloak.server-url}")
    private String serverUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credential.secret}")
    private String clientSecret;

    private static Keycloak keycloak;

    public Keycloak getInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .realm(realm)
                    .serverUrl(serverUrl)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();
        }
        return keycloak;
    }

    public Response createKeycloakUser(UserDto user) {
        RealmResource resource = getInstance().realm(realm);

        UsersResource usersResource = resource.users();

        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUserName());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

        Response response = usersResource.create(kcUser);

        return response;
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }


}
