package br.com.fabios.web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "calculoJurosCompostos")
public class CalculoJurosCompostos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "administradores_id")
    private Administrador cliente;

    @Column(name = "clientePesquisado")
    private String clientePesquisado;

    @Column(name = "mesesInvestimento")
    private int mesesInvestimento;

    @Column(name = "valorInvestido")
    private String valorInvestido;

    @Column(name = "vencimento")
    private Date vencimento;

    @Column(name = "IR")
    private float ir;

    @Column(name = "saldoInicial")
    private float saldoInicial;

    @Column(name = "jurosBruto")
    private float jurosBruto;

    @Column(name = "rentabilidadeFixada")
    private float rentabilidadeFixada;

    @Column(name = "recebiveisAcumulado")
    private float recebiveisAcumulado;

    @ManyToOne
    @JoinColumn(name = "juros_compostos_id")
    private JurosCompostos jurosCompostos;

    @Column(name = "mensal")
    private boolean mensal;

    @Column(name = "anual")
    private boolean anual;

    @Column(name = "date")
    private Date date;

}
