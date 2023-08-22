package com.imobanco.autenticador.api.model.input;

import com.imobanco.autenticador.api.model.domain.enums.TipoDocumentoEnum;
import com.imobanco.autenticador.api.model.domain.exception.ValidEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DocumentoInput {

    private Long idCadastro;
  //  @ValidEnum(enumClass = TipoDocumentoEnum.class)
    private List<ArquivoInput> imagemDocumento;
}
