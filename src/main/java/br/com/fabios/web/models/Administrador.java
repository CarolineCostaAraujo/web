package br.com.fabios.web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@Table(name = "administradores")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "email", length = 180, nullable = false)
    private String email;

    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "nivelAcesso")
    private String nivelAcesso;

    @Column(name = "imgPerfil")
    private String imgPerfil;

    @Column(name = "cliente")
    private String cliente;
}
