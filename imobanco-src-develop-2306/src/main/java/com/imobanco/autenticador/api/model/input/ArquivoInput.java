package com.imobanco.autenticador.api.model.input;

import com.imobanco.autenticador.api.model.domain.enums.TipoDocumentoEnum;
import com.imobanco.autenticador.api.model.domain.exception.ValidEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ArquivoInput {

  //  @ValidEnum(enumClass = TipoDocumentoEnum.class)
    private String tipoDocumento;
    @Schema(type = "string", format = "byte")
    private byte[] arquivo;
    private String extensao;
}
