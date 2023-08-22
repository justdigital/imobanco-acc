package com.imobanco.autenticador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.imobanco.autenticador.api.client.response.AlternativeIdNumbers;
import com.imobanco.autenticador.api.client.response.ExtendedDocumentInformation;
import com.imobanco.autenticador.api.client.response.PeopleBasicDataResponse;
import com.imobanco.autenticador.api.client.response.deserializer.AlternativeIdNumbersDeserializer;
import com.imobanco.autenticador.api.client.response.deserializer.ExtendedDocumentInformationDeserializer;
import com.imobanco.autenticador.api.client.response.deserializer.LocalDateDeserializer;
import com.imobanco.autenticador.api.model.input.FilaArquivoInput;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class MircoservicoTreinoApplicationTests {

	@Test
	void contextLoads() {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ExtendedDocumentInformation.class, new ExtendedDocumentInformationDeserializer());
		gsonBuilder.registerTypeAdapter(AlternativeIdNumbers.class, new AlternativeIdNumbersDeserializer());
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
		Gson gson = gsonBuilder.create();
		PeopleBasicDataResponse peopleBasicDataResponse =  gson.fromJson("{\n" +
				"    \"Result\": [\n" +
				"        {\n" +
				"            \"MatchKeys\": \"doc{32178645810}\",\n" +
				"            \"BasicData\": {\n" +
				"                \"TaxIdNumber\": \"32178645810\",\n" +
				"                \"TaxIdCountry\": \"BRAZIL\",\n" +
				"                \"AlternativeIdNumbers\": {\n" +
				"                    \"RG - SP\": \"454299394\"\n" +
				"                },\n" +
				"                \"ExtendedDocumentInformation\": {\n" +
				"                    \"RG - SP\": {\n" +
				"                        \"DocumentNumber\": \"454299394\",\n" +
				"                        \"DocumentLast4Digits\": \"9394\",\n" +
				"                        \"DocumentIssuingAgency\": \"SSP\",\n" +
				"                        \"DocumentIssuingStateCode\": \"SP\",\n" +
				"                        \"CreationDate\": \"2019-12-25T13:03:03.815Z\",\n" +
				"                        \"LastUpdateDate\": \"2019-12-25T13:03:03.815Z\",\n" +
				"                        \"Sources\": [\n" +
				"                            \"MEI-RF\"\n" +
				"                        ]\n" +
				"                    }\n" +
				"                },\n" +
				"                \"Name\": \"GUILHERME CARDOSO FUENTES\",\n" +
				"                \"Aliases\": {\n" +
				"                    \"CommonName\": \"GUILHERME FUENTES\",\n" +
				"                    \"StandardizedName\": \"GUILHERME CARDOSO FUENTE\"\n" +
				"                },\n" +
				"                \"Gender\": \"M\",\n" +
				"                \"NameWordCount\": 3,\n" +
				"                \"NumberOfFullNameNamesakes\": 1,\n" +
				"                \"NameUniquenessScore\": 1,\n" +
				"                \"FirstNameUniquenessScore\": 0.001,\n" +
				"                \"FirstAndLastNameUniquenessScore\": 0.11111111,\n" +
				"                \"BirthDate\": \"1997-05-08T00:00:00Z\",\n" +
				"                \"Age\": 26,\n" +
				"                \"ZodiacSign\": \"TOURO\",\n" +
				"                \"ChineseSign\": \"Ox\",\n" +
				"                \"BirthCountry\": \"BRASILEIRO\",\n" +
				"                \"MotherName\": \"IDELMA MENEGUETTI CARDOSO\",\n" +
				"                \"FatherName\": \"JOSE SEBASTIAO FUENTES\",\n" +
				"                \"MaritalStatusData\": {\n" +
				"                    \"MaritalStatus\": \"CASADO(A)\",\n" +
				"                    \"MaritalStatusSource\": \"ProclamasOrg\",\n" +
				"                    \"MaritalStatusLastUpdateDate\": \"2022-01-24T00:00:00Z\"\n" +
				"                },\n" +
				"                \"TaxIdStatus\": \"REGULAR\",\n" +
				"                \"TaxIdOrigin\": \"RECEITA FEDERAL\",\n" +
				"                \"TaxIdFiscalRegion\": \"SP\",\n" +
				"                \"HasObitIndication\": false,\n" +
				"                \"TaxIdStatusDate\": \"2023-06-24T00:00:00\",\n" +
				"                \"CreationDate\": \"2016-08-23T00:00:00Z\",\n" +
				"                \"LastUpdateDate\": \"2023-06-10T00:00:00Z\"\n" +
				"            }\n" +
				"        }\n" +
				"    ],\n" +
				"    \"QueryId\": \"595b08ee-1f6b-469c-9f84-2c11df2bbb3b\",\n" +
				"    \"ElapsedMilliseconds\": 53,\n" +
				"    \"QueryDate\": \"2023-08-14T17:27:07.9328119Z\",\n" +
				"    \"Status\": {\n" +
				"        \"basic_data\": [\n" +
				"            {\n" +
				"                \"Code\": 0,\n" +
				"                \"Message\": \"OK\"\n" +
				"            }\n" +
				"        ]\n" +
				"    },\n" +
				"    \"Evidences\": {}\n" +
				"}\n", PeopleBasicDataResponse.class);

		String g = peopleBasicDataResponse.toString();
	}

}
