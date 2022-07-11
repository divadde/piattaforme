package it.unical.progetto_piattaforme.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "biglietto", schema = "ticketstore")
public class Biglietto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "settore", length = 20)
    private String settore;

    @Basic
    @Column(name = "posto", length = 20)
    private String posto;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "evento")
    private Evento evento;
}
