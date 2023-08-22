package com.imobanco.autenticador.infrastructure.repository;

import com.imobanco.autenticador.api.model.domain.model.CadastroPf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadastroPfRepository extends JpaRepository<CadastroPf, Long> {

    List<CadastroPf> findByTsInativacaoIsNull();


}
