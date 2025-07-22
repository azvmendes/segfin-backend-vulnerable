package com.fintech.secfin.dto;

public class MetaSimuladaDTO {
    private String descricao;
    private double valorObjetivo;
    private int meses;

    public MetaSimuladaDTO() {}

    public MetaSimuladaDTO(String descricao, double valorObjetivo, int meses) {
        this.descricao = descricao;
        this.valorObjetivo = valorObjetivo;
        this.meses = meses;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(double valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }
}
