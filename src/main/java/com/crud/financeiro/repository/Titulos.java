package com.crud.financeiro.repository;

import com.crud.financeiro.model.Entidade;
import com.crud.financeiro.model.Titulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Titulos extends JpaRepository<Titulo, Long>{

    @Query(value = "select t from Titulo t join fetch t.tipoDePagamento " +
            "where t.descricao like %?1% and (t.entidade = ?2 or ?2 is null)",

            countQuery = "select count (t1) from Titulo as t1 " +
                    "where t1.descricao like %?1% and (t1.entidade = ?2 or ?2 is null)"
    )
    Page<Titulo> filtrados(String descricao, Entidade entidade, Pageable pageable);

    @Query("select distinct t.entidade from Titulo as t")
    List<Entidade> entidadesDosTitulos();
}
