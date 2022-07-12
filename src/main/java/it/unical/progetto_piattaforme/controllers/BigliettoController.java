package it.unical.progetto_piattaforme.controllers;

import it.unical.progetto_piattaforme.entities.Biglietto;
import it.unical.progetto_piattaforme.entities.Utente;
import it.unical.progetto_piattaforme.exceptions.BigliettoGiaAcquistatoException;
import it.unical.progetto_piattaforme.exceptions.PostiEsauritiException;
import it.unical.progetto_piattaforme.exceptions.PostoOccupatoException;
import it.unical.progetto_piattaforme.services.BigliettoServices;
import it.unical.progetto_piattaforme.services.UtenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biglietti")
public class BigliettoController {

    @Autowired
    private BigliettoServices bigliettoServices;
    @Autowired
    private UtenteServices utenteServices;


    @PreAuthorize("hasAuthority('buyer')")
    @PostMapping
    public ResponseEntity create(@RequestBody Biglietto biglietto) { //@Valid ?
        try {
            System.out.println("proviamo a fare il biglietto");
            Biglietto added = bigliettoServices.createBiglietto(biglietto);
            System.out.println("biglietto creato");
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (PostiEsauritiException e1) {
            return new ResponseEntity<>("NO_SEATS_AVAILABLE", HttpStatus.BAD_REQUEST);
        } catch (PostoOccupatoException e2) {
            return new ResponseEntity<>("SEAT_ALREADY_OCCUPIED", HttpStatus.BAD_REQUEST);
        } catch (BigliettoGiaAcquistatoException e3) {
            return new ResponseEntity<>("TICKET_ALREADY_BOUGHT", HttpStatus.BAD_REQUEST);

        }
    }

    @PreAuthorize("hasAuthority('buyer')")
    @GetMapping
    public ResponseEntity getByEmail(@RequestParam( name="email", required = false ) String email){
        System.out.println("entra?");
        Utente u = utenteServices.getUserByEmail(email);
        List<Biglietto> listaBiglietti = bigliettoServices.getBigliettiByUser(u);
        System.out.println(listaBiglietti.size());
        return new ResponseEntity(listaBiglietti, HttpStatus.OK);
    }

}
