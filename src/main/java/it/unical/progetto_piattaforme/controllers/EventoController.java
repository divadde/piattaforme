package it.unical.progetto_piattaforme.controllers;

import it.unical.progetto_piattaforme.entities.Evento;
import it.unical.progetto_piattaforme.exceptions.EventoGiaEsistenteException;
import it.unical.progetto_piattaforme.services.EventoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoServices eventoServices;

    @PostMapping
    public ResponseEntity create(@RequestBody Evento evento) {
        try {
            Evento added = eventoServices.registraEvento(evento);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (EventoGiaEsistenteException e) {
            return new ResponseEntity<>("ERROR_EVENT_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public List<Evento> getAll() {
        return eventoServices.getAllEventi();
    }


    //@PreAuthorize("hasAuthority('buyer')")
    @GetMapping("/byName")
    public ResponseEntity getByName(@RequestParam( name="nome", required = false ) String nome) {
        //System.out.println("Qualcuno cerca eventi all'istante " + Calendar.getInstance().getTime().getSeconds() +", col nome: "+nome);
        List<Evento> result = eventoServices.showEventsByName(nome);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>("No results!", HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

