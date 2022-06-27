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
@Table(name = "organizzatore", schema = "ticketstore")
public class Organizzatore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @OneToMany(mappedBy = "organizzatore", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Evento> eventi;

}
