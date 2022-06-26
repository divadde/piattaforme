package it.unical.progetto_piattaforme.services;

import it.unical.progetto_piattaforme.entities.Utente;
import it.unical.progetto_piattaforme.exceptions.AccountGiaEsistenteException;
import it.unical.progetto_piattaforme.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtenteServices {

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente registraUtente(Utente utente) throws AccountGiaEsistenteException {
        if ( utenteRepository.existsByEmail(utente.getEmail()) ) {
            throw new AccountGiaEsistenteException();
        }
        return utenteRepository.save(utente);
    }

    @Transactional(readOnly = true)
    public List<Utente> getAllUsers() {
        return utenteRepository.findAll();
    }

}
