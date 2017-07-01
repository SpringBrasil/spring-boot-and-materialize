package com.crud.financeiro.model;


public enum Situacao {

    COMPENSADO("Compensado"),
    CANCELADO("Cancelado"),
    PAGAMENTO_NAO_REALIZADO("Pagamento não realizado");

    private String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
