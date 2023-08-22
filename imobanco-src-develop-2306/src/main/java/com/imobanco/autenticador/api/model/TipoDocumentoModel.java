package com.imobanco.autenticador.api.model;

import com.imobanco.autenticador.api.model.domain.enums.TipoDocumentoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDocumentoModel {

    TipoDocumentoEnum[] tipoDocumento = TipoDocumentoEnum.values();

}
