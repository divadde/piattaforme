package it.unical.progetto_piattaforme.controllers;

import it.unical.progetto_piattaforme.entities.Utente;
import it.unical.progetto_piattaforme.exceptions.AccountGiaEsistenteException;
import it.unical.progetto_piattaforme.services.UtenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteServices utenteServices;

    @PostMapping
    public ResponseEntity create(@RequestBody Utente utente) { //@Valid ?
        try {
            Utente added = utenteServices.registraUtente(utente);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (AccountGiaEsistenteException e) {
            return new ResponseEntity<>("ERROR_MAIL_USER_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Utente> getAll() {
        return utenteServices.getAllUsers();
    }


}
