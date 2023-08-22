package com.imobanco.autenticador.api.model.input;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class CadastroPfInput {


    //private Long idCadastro;
    private String idUsuKeycloak;
    private LocalDateTime tsCriacao;
    private String nomeUsu;
    private String numeroCpfUsu;
    private String nomeMae;
    private LocalDate dataNasc;
    private String emailUsu;
    private String numeroFoneUsu;
    private String numeroDddFoneUsu;
    private Integer codigoGeneroUsu;
    private String nomeLogradouro;
    private String numeroEndereço;
    private String numeroCep;
    private String descricaoCompEnd;
    private boolean codigoPep;
    private boolean codigoAceiteTermo;
    // private LocalDateTime ts_atualizacao;
    // private LocalDateTime ts_inativacao;











}
