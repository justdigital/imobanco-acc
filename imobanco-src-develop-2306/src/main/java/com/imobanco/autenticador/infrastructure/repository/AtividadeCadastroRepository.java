package com.imobanco.autenticador.infrastructure.repository;

import com.imobanco.autenticador.api.model.domain.model.AtividadeCadastro;
import com.imobanco.autenticador.api.model.domain.model.CadastroPf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeCadastroRepository extends JpaRepository<AtividadeCadastro, Long> {

    @Modifying
    @Query("update AtividadeCadastro a set a.tsDataFim = current_timestamp where a.cadastroPf.idCadastro = :idCadastro and a.tsDataFim = NULL ")
    void finalizarAtividadeAtual(@Param("idCadastro") Long idCadastro);


}
