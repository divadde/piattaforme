package it.unical.progetto_piattaforme.services;

import it.unical.progetto_piattaforme.entities.Biglietto;
import it.unical.progetto_piattaforme.entities.Evento;
import it.unical.progetto_piattaforme.exceptions.PostiEsauritiException;
import it.unical.progetto_piattaforme.repositories.BigliettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BigliettoServices {
    @Autowired
    private BigliettoRepository bigliettoRepository;

    @Transactional(readOnly = false)
    public Biglietto createBiglietto(Biglietto biglietto) throws PostiEsauritiException {
        Biglietto bigliettoDaAcq = bigliettoRepository.save(biglietto);
        Evento evento = bigliettoDaAcq.getEvento();
        int postiDisp = evento.getMassimo_posti() - evento.getPosti_occupati();
        if(postiDisp <= 0){
            throw new PostiEsauritiException();
        }
        evento.setPosti_occupati(evento.getPosti_occupati()+1);
        //entityManager.refresh(evento);
        //entityManager.refresh(bigliettoDaAcq);
        return bigliettoDaAcq;
    }

}
