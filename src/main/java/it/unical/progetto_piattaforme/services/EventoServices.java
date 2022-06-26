package it.unical.progetto_piattaforme.services;

import it.unical.progetto_piattaforme.entities.Evento;
import it.unical.progetto_piattaforme.entities.Organizzatore;
import it.unical.progetto_piattaforme.exceptions.EventoGiaEsistenteException;
import it.unical.progetto_piattaforme.exceptions.OrganizzatoreGiaEsistenteException;
import it.unical.progetto_piattaforme.repositories.EventoRepository;
import it.unical.progetto_piattaforme.repositories.OrganizzatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoServices {

    @Autowired
    private EventoRepository eventoRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Evento registraEvento(Evento evento) throws EventoGiaEsistenteException {
        if ( eventoRepository.existsByNome(evento.getNome()) ) {
            throw new EventoGiaEsistenteException();
        }
        return eventoRepository.save(evento);
    }

    @Transactional(readOnly = true)
    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }
}
