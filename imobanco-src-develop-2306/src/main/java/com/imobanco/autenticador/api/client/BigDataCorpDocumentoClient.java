package com.imobanco.autenticador.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "https://plugin.bigdatacorp.com.br",name = "qualidadeDocumento")
@PropertySource("classpath:application.properties")
public interface BigDataCorpDocumentoClient {


    @RequestMapping(method = RequestMethod.POST, value = "/qualidadedeimagem/documento",consumes = "application/json")
    String getDocumentoQuality(@RequestBody String Datasets,String q);
}
