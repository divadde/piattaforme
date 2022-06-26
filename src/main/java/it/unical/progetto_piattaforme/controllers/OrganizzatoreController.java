package it.unical.progetto_piattaforme.controllers;

import it.unical.progetto_piattaforme.entities.Organizzatore;
import it.unical.progetto_piattaforme.exceptions.OrganizzatoreGiaEsistenteException;
import it.unical.progetto_piattaforme.services.OrganizzatoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizzatori")
public class OrganizzatoreController {

    @Autowired
    private OrganizzatoreServices organizzatoreServices;

    @PostMapping
    public ResponseEntity create(@RequestBody Organizzatore organizzatore) { //@Valid ?
        try {
            Organizzatore added = organizzatoreServices.registraOrganizzatore(organizzatore);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (OrganizzatoreGiaEsistenteException e) {
            return new ResponseEntity<>("ERROR_ORGANIZER_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Organizzatore> getAll() {
        return organizzatoreServices.getAllOrganizzatori();
    }

}
