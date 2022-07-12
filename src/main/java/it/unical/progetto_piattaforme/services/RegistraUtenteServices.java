package it.unical.progetto_piattaforme.services;

import it.unical.progetto_piattaforme.entities.Utente;
import it.unical.progetto_piattaforme.repositories.UtenteRepository;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;

@Service
public class RegistraUtenteServices {

    @Autowired
    private UtenteRepository utenteRepository;

    //metodo per la registrazione dell'utente
    public void registra(String pass, String username, Utente utente){
        String usernameAdmin = "david";
        String passwordAdmin = "sayan";
        String clientName = "ticketstore-flutter";
        String role = "buyer";
        String email = utente.getEmail();
        String lastName = username;
        String password = pass;
        String serverUrl = "http://localhost:8080/";
        String realm = "ticketstore-flutter";
        String clientId = clientName;
        String clientSecret = "ld5PGgxa4X4KJok1dJEC8OtP8Jjm4Rif";


        Keycloak keycloak = KeycloakBuilder.builder() 
                .serverUrl(serverUrl) 
                .realm(realm) 
                .grantType(OAuth2Constants.PASSWORD) 
                .clientId(clientId) 
                .clientSecret(clientSecret) 
                .username(usernameAdmin) 
                .password(passwordAdmin) 
                .build();

        System.out.println("Keycloak builder");
        // Define user
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(username);
        user.setEmail(email);

        user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

        System.out.println("Define user");

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();


        System.out.println("Get realm");


        // Create user (requires manage-users role)
        Response response = usersRessource.create(user);

        System.out.println("Response?");

        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);
        System.out.printf("User created with userId: %s%n", userId);


        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);

        UserResource userResource = usersRessource.get(userId);


        // Set password credential
        userResource.resetPassword(passwordCred);

        // Get client
        ClientRepresentation app1Client = realmResource.clients().findByClientId(clientName).get(0);

        // Get client level role (requires view-clients role)
        RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId()).roles().get(role).toRepresentation();

        // Assign client level role to user
        userResource.roles().clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));

        // Send password reset E-Mail
        // VERIFY_EMAIL, UPDATE_PROFILE, CONFIGURE_TOTP, UPDATE_PASSWORD, TERMS_AND_CONDITIONS
        //usersRessource.get(userId).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));

        utenteRepository.save(utente);

        //System.out.println("Registrazione completata");

    }
    
    
}