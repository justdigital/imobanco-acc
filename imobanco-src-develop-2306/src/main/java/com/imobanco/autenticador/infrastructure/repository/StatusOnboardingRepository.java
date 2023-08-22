package com.imobanco.autenticador.infrastructure.repository;

import com.imobanco.autenticador.api.model.domain.model.AtividadeCadastro;
import com.imobanco.autenticador.api.model.domain.model.CadastroPf;
import com.imobanco.autenticador.api.model.domain.model.StatusOnboarding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusOnboardingRepository extends JpaRepository<StatusOnboarding, Long> {

    StatusOnboarding findByDescricaoStatus(String descricaoStatus );


}
