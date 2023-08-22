package com.imobanco.autenticador.api.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FilaArquivoInput {

    private Long idCadastro;
    private String nomeBucket;
    private String chaveObjetoBucket;
    private byte[] arquivo;
    private String extensao;
}
