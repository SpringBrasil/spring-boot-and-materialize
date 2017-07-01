package com.crud.financeiro.model;

public enum Tipo {

    ENTRADA("Entrada"),
    SAIDA("Saída");

    private String descricao;

    Tipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
