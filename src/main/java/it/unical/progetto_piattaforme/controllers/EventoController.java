package it.unical.progetto_piattaforme.controllers;

import it.unical.progetto_piattaforme.entities.Evento;
import it.unical.progetto_piattaforme.exceptions.EventoGiaEsistenteException;
import it.unical.progetto_piattaforme.services.EventoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoServices eventoServices;

    @PostMapping
    public ResponseEntity create(@RequestBody Evento evento) { //@Valid ?
        try {
            Evento added = eventoServices.registraEvento(evento);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (EventoGiaEsistenteException e) {
            return new ResponseEntity<>("ERROR_EVENT_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Evento> getAll() {
        return eventoServices.getAllEventi();
    }

}

