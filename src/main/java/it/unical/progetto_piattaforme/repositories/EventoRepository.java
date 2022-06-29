package it.unical.progetto_piattaforme.repositories;

import it.unical.progetto_piattaforme.entities.Evento;
import it.unical.progetto_piattaforme.entities.Organizzatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Integer> {

    List<Evento> findByNome(String nome);
    List<Evento> findByNomeContaining(String nome);
    List<Evento> findByCitta(String nome);
    List<Evento> findByOrganizzatore(Organizzatore organizzatore);
    boolean existsByNome(String nome);
}
