package com.imobanco.autenticador.api.controller;


import com.imobanco.autenticador.api.model.TipoDocumentoModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tipodocumento")
public class TipoDocumentoController {

    @GetMapping
    public TipoDocumentoModel listar() {

        return  new TipoDocumentoModel();
    }
}
