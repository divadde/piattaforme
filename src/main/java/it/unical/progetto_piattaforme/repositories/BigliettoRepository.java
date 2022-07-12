package it.unical.progetto_piattaforme.repositories;

import it.unical.progetto_piattaforme.entities.Biglietto;
import it.unical.progetto_piattaforme.entities.Evento;
import it.unical.progetto_piattaforme.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BigliettoRepository extends JpaRepository<Biglietto,Integer> {

    List<Biglietto> findBySettore(String settore);
    List<Biglietto> findByUtente(Utente utente);
    List<Biglietto> findByEvento(Evento evento);
    boolean existsByUtenteAndEvento(Utente utente, Evento evento);
    boolean existsBySettoreAndPostoAndEvento(String settore, String posto, Evento evento);

}
