package com.fintech.secfin.model;

import jakarta.persistence.*;

@Entity
public class MetaFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(nullable = false)
    private double valorObjetivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValorObjetivo() { return valorObjetivo; }

    public void setValorObjetivo(double valorObjetivo) { this.valorObjetivo = valorObjetivo; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
