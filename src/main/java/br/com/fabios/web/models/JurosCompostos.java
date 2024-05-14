package br.com.fabios.web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "jurosCompostos")
public class JurosCompostos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "fee")
    private float fee;

    @Column(name = "txAdmConfig")
    private float txAdmConfig;

    @Column(name = "iofConfig")
    private float iofConfig;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "date")
    private Date date;

}
