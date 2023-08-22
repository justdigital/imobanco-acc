package com.imobanco.autenticador.api.client;

import com.imobanco.autenticador.api.client.request.PessoasClientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "https://app.bigdatacorp.com.br",name = "documentocopy")
@PropertySource("classpath:application.properties")
public interface BigDataCorpDocumentosCopyClient {

    @RequestMapping(method = RequestMethod.POST, value = "/bigid/documentoscopia/checar",consumes = "application/json")
    String getDocumentosCopy(@RequestBody PessoasClientRequest request);
}
