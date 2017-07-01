package com.crud.financeiro.dto;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RelatorioContasAReceber {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data_inicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data_fim;

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }
}
