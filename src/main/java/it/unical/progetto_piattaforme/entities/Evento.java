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
@Table(name = "evento", schema = "ticketstore")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Version
    @Column(name = "version", nullable = false)
    @JsonIgnore
    private long version;

    @Basic
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name = "citta", nullable = true, length = 20)
    private String citta;

    @Basic
    @Column(name = "posti_occupati", nullable = false)
    private int posti_occupati;

    @Basic
    @Column(name = "massimo_posti", nullable = false)
    private int massimo_posti;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Biglietto> bigliettiInVendita;

    @ManyToOne
    @JoinColumn(name = "organizzatore")
    private Organizzatore organizzatore;
}
