package com.imobanco.autenticador.service;


import com.imobanco.autenticador.api.assembler.CadastroPfAssembler;
import com.imobanco.autenticador.api.assembler.CadastroPfDisassembler;
import com.imobanco.autenticador.api.model.CadastroPfModel;
import com.imobanco.autenticador.api.model.domain.exception.DefaultException;
import com.imobanco.autenticador.api.model.domain.model.AtividadeCadastro;
import com.imobanco.autenticador.api.model.domain.model.CadastroPf;
import com.imobanco.autenticador.api.model.domain.model.StatusOnboarding;
import com.imobanco.autenticador.api.model.input.CadastroPfInput;
import com.imobanco.autenticador.infrastructure.Aws;
import com.imobanco.autenticador.infrastructure.repository.AtividadeCadastroRepository;
import com.imobanco.autenticador.infrastructure.repository.CadastroPfRepository;
import com.imobanco.autenticador.infrastructure.repository.StatusOnboardingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
//@AllArgsConstructor
@PropertySource("classpath:application.properties")
public class AtividadeCadastroService {


    @Autowired
    private AtividadeCadastroRepository atividadeCadastroRepository;

    @Autowired
    private StatusOnboardingRepository statusOnboardingRepository;

    @Autowired
    private CadastroPfService cadastroPfService;

    @Transactional
    public AtividadeCadastro salvar(Long idCadastro, String status) {

        CadastroPf cadastroPf = cadastroPfService.buscarPorIdOuFalhar(idCadastro);
        StatusOnboarding statusOnboarding = statusOnboardingRepository.findByDescricaoStatus(status);
        AtividadeCadastro atividadeCadastro = AtividadeCadastro.builder().statusOnboarding(statusOnboarding).build();
        cadastroPf.addAtividadeCadastro(atividadeCadastro);

        return atividadeCadastroRepository.save(atividadeCadastro);

    }

    public void finalizarAtividadeAtual(Long idCadastro){

        atividadeCadastroRepository.finalizarAtividadeAtual(idCadastro);

    }






}
