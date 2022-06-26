package it.unical.progetto_piattaforme.controllers;

import it.unical.progetto_piattaforme.entities.Biglietto;
import it.unical.progetto_piattaforme.exceptions.PostiEsauritiException;
import it.unical.progetto_piattaforme.services.BigliettoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biglietti")
public class BigliettoController {

    @Autowired
    private BigliettoServices bigliettoServices;

    @PostMapping
    public ResponseEntity create(@RequestBody Biglietto biglietto) { //@Valid ?
        try {
            Biglietto added = bigliettoServices.createBiglietto(biglietto);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (PostiEsauritiException e) {
            return new ResponseEntity<>("NO_SEATS_AVAILABLE", HttpStatus.BAD_REQUEST);
        }
    }

}
