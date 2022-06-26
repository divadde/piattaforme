package it.unical.progetto_piattaforme.services;

import it.unical.progetto_piattaforme.entities.Organizzatore;
import it.unical.progetto_piattaforme.entities.Utente;
import it.unical.progetto_piattaforme.exceptions.AccountGiaEsistenteException;
import it.unical.progetto_piattaforme.exceptions.OrganizzatoreGiaEsistenteException;
import it.unical.progetto_piattaforme.repositories.OrganizzatoreRepository;
import it.unical.progetto_piattaforme.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizzatoreServices {

    @Autowired
    private OrganizzatoreRepository organizzatoreRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Organizzatore registraOrganizzatore(Organizzatore organizzatore) throws OrganizzatoreGiaEsistenteException {
        if ( organizzatoreRepository.existsByNome(organizzatore.getNome()) ) {
            throw new OrganizzatoreGiaEsistenteException();
        }
        return organizzatoreRepository.save(organizzatore);
    }

    @Transactional(readOnly = true)
    public List<Organizzatore> getAllOrganizzatori() {
        return organizzatoreRepository.findAll();
    }
}
