package com.imobanco.autenticador.api.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.imobanco.autenticador.api.assembler.CadastroPfAssembler;
import com.imobanco.autenticador.api.model.CadastroPfModel;
import com.imobanco.autenticador.api.model.domain.model.CadastroPf;
import com.imobanco.autenticador.api.model.input.CadastroPfInput;
import com.imobanco.autenticador.service.CadastroPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cadastropf")
public class CadastroPfController {

    @Autowired
    private CadastroPfService cadastroPfService;

    @Autowired
    private CadastroPfAssembler cadastroPfAssembler;

    @GetMapping
    public List<CadastroPfModel> listar() {

        return  cadastroPfService.listar();
    }

    @GetMapping("/{cadastroPfId}")
    public CadastroPfModel buscar(@PathVariable Long cadastroPfId) {
        return      cadastroPfService.buscarPorId(cadastroPfId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroPfModel adicionar(@RequestBody @Valid CadastroPfInput cadastroPfInput) throws Exception {

        return cadastroPfAssembler.toModel(cadastroPfService.salvar(cadastroPfInput));
    }

    @DeleteMapping("/{cadastroPfId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cadastroPfId) {

        cadastroPfService.excluir(cadastroPfId);
    }

    @PutMapping("/{cadastroPfId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CadastroPf atualizar(@PathVariable Long cadastroPfId,@RequestBody @Valid CadastroPfInput cadastroPfInput) throws Exception {
        return cadastroPfService.atualizar(cadastroPfId, cadastroPfInput);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidFormatException.class)
    public String errorHandler(InvalidFormatException e) {
        if (e.getMessage().contains("Cannot deserialize value of type `com.imobanco.autenticador.api.model.domain.enums.TipoComprovanteEnderecoEnum`"))
            return  "Valor enviado para tipoComprovanteEndereco [".concat(e.getValue().toString()).concat("] Inválido.\nValores aceitos para tipoComprovanteEndereco são: [AGUA, ENERGIA, TELEFONE, GAS]");

        return e.getMessage().toString();


    }

}