package it.unical.progetto_piattaforme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "utente", schema = "ticketstore")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "cf", nullable = false, length = 70)
    private String cf;

    @Basic
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name = "cognome", nullable = false, length = 50)
    private String cognome;

    @Basic
    @Column(name = "eta", nullable = false)
    private int eta;

    @Basic
    @Column(name = "citta", nullable = false, length = 20)
    private String citta;

    @Basic
    @Column(name = "email", nullable = false, length = 90)
    private String email;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Biglietto> bigliettiAcquistati;
}
