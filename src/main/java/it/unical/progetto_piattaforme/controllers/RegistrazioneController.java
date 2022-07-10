package it.unical.progetto_piattaforme.controllers;

import it.unical.progetto_piattaforme.entities.Utente;
import it.unical.progetto_piattaforme.services.RegistraUtenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrazioneController {

    @Autowired
    RegistraUtenteServices registraUtenteServices;

    //todo verifica
    @PostMapping("/registrazione")
    public @ResponseBody ResponseEntity registrazione(@RequestBody User u){
        System.out.println("Qualcuno si vuole registrare");
        String pass = u.password;
        String user = u.username;
        try {
            registraUtenteServices.registra(pass, user, u.utente);
            System.out.println("registrazione avvenuta");
        }catch(Exception e){
            return new ResponseEntity("errore di registrazione", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("registrazione completata", HttpStatus.OK);
    }


    private static class User{
        Utente utente;
        String username;
        String password;

        public void setUtente(Utente utente){
            this.utente = utente;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

}
