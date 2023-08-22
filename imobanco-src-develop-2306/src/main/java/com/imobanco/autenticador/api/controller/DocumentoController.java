package com.imobanco.autenticador.api.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.imobanco.autenticador.api.model.ArquivoModel;
import com.imobanco.autenticador.api.model.CadastroPfModel;
import com.imobanco.autenticador.api.model.domain.enums.TipoDocumentoEnum;
import com.imobanco.autenticador.api.model.input.DocumentoInput;
import com.imobanco.autenticador.service.CadastroPfService;
import com.imobanco.autenticador.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private CadastroPfService cadastroPfService;

    @GetMapping()
    public List<ArquivoModel> buscar(@RequestParam(name = "cadastroPfId") Long cadastroPfId,@RequestParam(name = "tipoDocumento") TipoDocumentoEnum tipoDocumento ) {

        return documentoService.buscarPorId(cadastroPfId,tipoDocumento);

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid DocumentoInput documentoInput) throws Exception {

           /* documentoInput.getImagemDocumento().forEach( imagemDocumentoItem -> {
            cadastroPfService.buscarPorId(documentoInput.getIdCadastro());
            documentoService.salvar(documentoInputItem); });*/
            cadastroPfService.buscarPorId(documentoInput.getIdCadastro());
            documentoService.verificarTipoDocumentoEnumValido(documentoInput);
            documentoService.salvar(documentoInput);

    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@RequestParam(name = "cadastroPfId") Long cadastroPfId,@RequestParam(name = "tipoDocumento") TipoDocumentoEnum tipoDocumento) {

        cadastroPfService.buscarPorId(cadastroPfId);
        documentoService.excluir(cadastroPfId,tipoDocumento);

    }

    @PutMapping("/{documentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@RequestBody @Valid DocumentoInput documentoInput) throws Exception {

        cadastroPfService.buscarPorId(documentoInput.getIdCadastro());
        documentoService.salvar(documentoInput);

    }

   // @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
   // @ExceptionHandler(InvalidFormatException.class)
    public String errorHandler(InvalidFormatException e) {
        if (e.getMessage().contains("Cannot deserialize value of type `com.imobanco.autenticador.api.model.domain.enums.TipoDocumentoEnum`"))
        return  "Valor enviado para tipoDocumento [".concat(e.getValue().toString())
                .concat("] Inválido.\nValores aceitos para TipoDocumento são:[")
               .concat(Arrays.stream(TipoDocumentoEnum.values())
                        .map( tipoDocumentoEnum ->  tipoDocumentoEnum.name())
                        .reduce(  (s, s2) -> s + "," + s2).get()).concat("]");

        return e.getMessage().toString();


    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String errorHandler(MissingServletRequestParameterException e) {
        if(e.getMessage().contains("cadastroPfId"))
            return "Parâmetro cadastroPfId é obrigatório!";
        if(e.getMessage().contains("tipoDocumento"))
            return "Parâmetro tipoDocumento é obrigatório!";

    return  e.getMessage().toString();

    }

}