package com.imobanco.autenticador.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ArquivoModel {

    private byte[] arquivo;
    private String arquivoId;
    private String extensao;

}
