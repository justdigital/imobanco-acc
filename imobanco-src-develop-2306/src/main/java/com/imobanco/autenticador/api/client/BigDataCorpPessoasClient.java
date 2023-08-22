package com.imobanco.autenticador.api.client;

import com.imobanco.autenticador.api.client.request.PessoasClientRequest;
import feign.Headers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "https://plataforma.bigdatacorp.com.br",name = "pessoas")
@PropertySource("classpath:application.properties")
public interface BigDataCorpPessoasClient {


    @RequestMapping(method = RequestMethod.POST, value = "/pessoas",consumes = "application/json",produces = "application/json")
    String requestPeopleBasicData( @RequestBody PessoasClientRequest request,
                                   @RequestHeader("AccessToken") String acessToken);
}
