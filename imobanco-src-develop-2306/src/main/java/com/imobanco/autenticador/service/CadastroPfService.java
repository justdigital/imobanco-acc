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
import java.time.LocalDateTime;

import com.imobanco.autenticador.infrastructure.repository.StatusOnboardingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
//@AllArgsConstructor
@PropertySource("classpath:application.properties")
public class CadastroPfService {

    @Autowired
    private CadastroPfRepository cadastroPfRepository;

    @Autowired
    private StatusOnboardingRepository statusOnboardingRepository;

    @Autowired
    private AtividadeCadastroRepository atividadeCadastroRepository;

    @Autowired
    private Aws aws;
    @Autowired
    private  CadastroPfDisassembler cadastroPfDisassembler;

    @Autowired
    private CadastroPfAssembler cadastroPfAssembler;

    @Transactional
    public CadastroPf salvar(CadastroPfInput cadastroPfInput) {

        CadastroPf cadastroPf = cadastroPfDisassembler.toDomainObject(cadastroPfInput);
        cadastroPf.setTsCriacao( LocalDateTime.now());
        cadastroPf.setIdCadastro(null);
        StatusOnboarding statusOnboarding = statusOnboardingRepository.findByDescricaoStatus("CADASTRO");
        cadastroPf.addAtividadeCadastro(AtividadeCadastro.builder().statusOnboarding(statusOnboarding).build());
        CadastroPf cadastroPfEntity = cadastroPfRepository.save(cadastroPf);
        return cadastroPfEntity;

    }

    public void finalizarAtividadeAtual(Long idCadastro){

        atividadeCadastroRepository.finalizarAtividadeAtual(idCadastro);

    }

    @Transactional
    public CadastroPf atualizar (Long cadastroPfId, CadastroPfInput cadastroPfInput) throws Exception{

        buscarPorIdOuFalhar(cadastroPfId);
        CadastroPf cadastroPf = cadastroPfDisassembler.toDomainObject(cadastroPfInput);
        cadastroPf.setTsAtualizacao(LocalDateTime.now());
        cadastroPf.setIdCadastro(cadastroPfId);
        CadastroPf cadastroPfEntity = cadastroPfRepository.save(cadastroPf);
        return cadastroPfEntity;

    }

    public List<CadastroPfModel> listar() {

        List<CadastroPfModel> cadastroPfModelList = cadastroPfAssembler.toCollectionModel(cadastroPfRepository.findByTsInativacaoIsNull()) ;
        return cadastroPfModelList;

    }

    public void excluir (Long cadastroPfId){

        CadastroPf cadastroPf = buscarPorIdOuFalhar(cadastroPfId);
        cadastroPf.setTsInativacao(LocalDateTime.now());
        cadastroPfRepository.save(cadastroPf);

    }

    public CadastroPfModel buscarPorId(Long cadastroPfId) {

        CadastroPfModel cadastroPfModel = cadastroPfAssembler.toModel(buscarPorIdOuFalhar(cadastroPfId)) ;
        return cadastroPfModel;

    }

    public CadastroPf buscarPorIdOuFalhar(Long cadastroPfId){

        return cadastroPfRepository.findById(cadastroPfId)
                .orElseThrow(() -> new DefaultException(HttpStatus.NOT_FOUND, "CadastroPf Não encontrado" ));
    }

}
