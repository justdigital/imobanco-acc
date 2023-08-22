package com.imobanco.autenticador.api.model.domain.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TipoDocumentoEnum {

    COMPROVANTE_ENDERECO("doc-comprovante-endereco"),
    CNH("doc-cnh"),
    RG_FRENTE("doc-rg-frente"),
    RG_VERSO("doc-rg-verso");
    private String chave;
    private TipoDocumentoEnum(String chave) {
        this.chave = chave;
    }

}
