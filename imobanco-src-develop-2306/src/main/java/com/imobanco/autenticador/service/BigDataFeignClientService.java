package com.imobanco.autenticador.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.imobanco.autenticador.api.client.BigDataCorpPessoasClient;
import com.imobanco.autenticador.api.client.request.PessoasClientRequest;
import com.imobanco.autenticador.api.client.response.AlternativeIdNumbers;
import com.imobanco.autenticador.api.client.response.ExtendedDocumentInformation;
import com.imobanco.autenticador.api.client.response.PeopleBasicDataResponse;
import com.imobanco.autenticador.api.client.response.deserializer.AlternativeIdNumbersDeserializer;
import com.imobanco.autenticador.api.client.response.deserializer.ExtendedDocumentInformationDeserializer;
import com.imobanco.autenticador.api.client.response.deserializer.LocalDateDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@PropertySource("classpath:application.properties")
public class BigDataFeignClientService {

    @Autowired
    BigDataCorpPessoasClient bigDataCorpPessoasClient;

    @Value("${bigdatacorp.api.token}")
    private String acessToken;

    public PeopleBasicDataResponse requestDadosPessoa(String cpf) {

        PessoasClientRequest request =PessoasClientRequest.builder()
                .datasets("basic_data")
                .q("doc{"+cpf+"}" ).build();

       String resposta =  bigDataCorpPessoasClient.requestPeopleBasicData(request,acessToken);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ExtendedDocumentInformation.class, new ExtendedDocumentInformationDeserializer());
        gsonBuilder.registerTypeAdapter(AlternativeIdNumbers.class, new AlternativeIdNumbersDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(resposta,PeopleBasicDataResponse.class);
    }

}
