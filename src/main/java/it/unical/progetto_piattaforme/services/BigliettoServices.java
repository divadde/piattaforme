package it.unical.progetto_piattaforme.services;

import it.unical.progetto_piattaforme.entities.Biglietto;
import it.unical.progetto_piattaforme.entities.Evento;
import it.unical.progetto_piattaforme.exceptions.PostiEsauritiException;
import it.unical.progetto_piattaforme.exceptions.PostoOccupatoException;
import it.unical.progetto_piattaforme.repositories.BigliettoRepository;
import it.unical.progetto_piattaforme.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BigliettoServices {
    @Autowired
    private BigliettoRepository bigliettoRepository;
    @Autowired
    private EventoRepository eventoRepository;

    @Transactional(readOnly = false)
    public Biglietto createBiglietto(Biglietto biglietto) throws PostiEsauritiException, PostoOccupatoException {
        if (bigliettoRepository.existsBySettoreAndPostoAndEvento(biglietto.getSettore(),
                biglietto.getPosto(),biglietto.getEvento()))
            throw new PostoOccupatoException();
        Evento evento = biglietto.getEvento();
        Evento realEv = eventoRepository.getReferenceById(evento.getId());
        int postiDisp = realEv.getMassimo_posti() - realEv.getPosti_occupati();
        if(postiDisp <= 0){
            throw new PostiEsauritiException();
        }
        Biglietto bigliettoDaAcq = bigliettoRepository.save(biglietto);
        realEv.setPosti_occupati(realEv.getPosti_occupati()+1);
        return bigliettoDaAcq;
    }

}