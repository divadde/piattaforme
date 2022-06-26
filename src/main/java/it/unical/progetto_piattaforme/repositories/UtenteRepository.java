package it.unical.progetto_piattaforme.repositories;

import it.unical.progetto_piattaforme.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Integer> {

    List<Utente> findByCf(String cf);
    List<Utente> findByNome(String nome);
    List<Utente> findByCognome(String cognome);
    List<Utente> findByNomeAndCognome(String nome, String cognome);
    List<Utente> findByEta(int eta);
    List<Utente> findByCitta(String citta);
    List<Utente> findByEmail(String email);
    boolean existsByEmail(String email);

}
