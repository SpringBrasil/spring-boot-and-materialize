package com.crud.financeiro.service;

import com.crud.financeiro.exception.NegocioException;
import com.crud.financeiro.model.Entidade;
import com.crud.financeiro.repository.Entidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EntidadesService {

    @Autowired
    private Entidades entidades;

    public void salvar(Entidade entidade) {
        entidades.save(entidade);
    }

    public void remover(Long codigo) {
        try {
            entidades.delete(codigo);
        }catch (DataIntegrityViolationException e){
            throw new NegocioException("Erro ao tentar excluir a entidade. " +
                    "Apague os títulos da entidade e tente novamente");
        }
    }
}
