package it.unical.progetto_piattaforme.repositories;

import it.unical.progetto_piattaforme.entities.Organizzatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizzatoreRepository extends JpaRepository<Organizzatore,Integer> {

    List<Organizzatore> findByNome(String nome);
    boolean existsByNome(String nome);
}
