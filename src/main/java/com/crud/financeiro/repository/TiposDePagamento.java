package com.crud.financeiro.repository;

import com.crud.financeiro.model.TipoDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposDePagamento extends JpaRepository<TipoDePagamento, Long>{

}
