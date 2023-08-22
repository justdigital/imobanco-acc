package com.imobanco.autenticador.api.model;


import com.imobanco.autenticador.api.model.input.ArquivoInput;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CadastroPfModel {

    private Long idCadastro;
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
    private LocalDateTime tsAtualizacao;
    private LocalDateTime tsInativacao;
    private ArquivoModel imagemComprovanteEndereco;

}
