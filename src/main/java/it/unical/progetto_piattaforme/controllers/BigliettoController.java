package it.unical.progetto_piattaforme.controllers;

import it.unical.progetto_piattaforme.entities.Biglietto;
import it.unical.progetto_piattaforme.entities.Utente;
import it.unical.progetto_piattaforme.exceptions.PostiEsauritiException;
import it.unical.progetto_piattaforme.exceptions.PostoOccupatoException;
import it.unical.progetto_piattaforme.services.BigliettoServices;
import it.unical.progetto_piattaforme.services.UtenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biglietti")
public class BigliettoController {

    @Autowired
    private BigliettoServices bigliettoServices;
    @Autowired
    private UtenteServices utenteServices;

    @PostMapping
    public ResponseEntity create(@RequestBody Biglietto biglietto) { //@Valid ?
        try {
            Biglietto added = bigliettoServices.createBiglietto(biglietto);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (PostiEsauritiException e1) {
            return new ResponseEntity<>("NO_SEATS_AVAILABLE", HttpStatus.BAD_REQUEST);
        } catch (PostoOccupatoException e2) {
            return new ResponseEntity<>("SEAT_ALREADY_OCCUPIED", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity getByEmail(@RequestParam( name="email", required = false ) String email){
        Utente u = utenteServices.getUserByEmail(email);
        return new ResponseEntity(u, HttpStatus.OK);
    }

}
