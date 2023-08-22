package com.imobanco.autenticador.api.client.response.deserializer;

import com.google.gson.*;
import com.imobanco.autenticador.api.client.response.ExtendedDocumentInformation;
import com.imobanco.autenticador.api.client.response.ExtendedDocumentInformationFields;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ExtendedDocumentInformationDeserializer implements JsonDeserializer<ExtendedDocumentInformation> {


    @Override
    public ExtendedDocumentInformation deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ExtendedDocumentInformation extendedDocumentInformation = new ExtendedDocumentInformation();
        extendedDocumentInformation.setExtendedDocumentfields(new HashMap<>());

        ExtendedDocumentInformationFields fields = new ExtendedDocumentInformationFields();
        JsonObject jsonObject = element.getAsJsonObject();
        String chaveObjeto = jsonObject.entrySet().stream().findFirst().get().getKey().toString();
        JsonObject jsonObject1 = jsonObject.entrySet().stream().findFirst().get().getValue().getAsJsonObject();

        for (Entry<String, JsonElement> entry : jsonObject1.entrySet())
        {
            switch (entry.getKey()) {
                case "DocumentNumber": fields.setDocumentNumber(entry.getValue().getAsString());
                    break;
                case "DocumentLast4Digits": fields.setDocumentLast4Digits(entry.getValue().getAsString());
                    break;
                case "DocumentIssuingAgency": fields.setDocumentIssuingAgency(entry.getValue().getAsString());
                    break;
                case "DocumentIssuingStateCode": fields.setDocumentIssuingStateCode(entry.getValue().getAsString());
                    break;
                case "CreationDate": fields.setCreationDate(entry.getValue().getAsString());
                    break;
                case "LastUpdateDate": fields.setLastUpdateDate(entry.getValue().getAsString());
                    break;
                case "Sources": JsonArray jsonArray = entry.getValue().getAsJsonArray();
                        List<String> listaSources = new ArrayList<>();
                    for (JsonElement jsonElement : jsonArray){
                        listaSources.add(jsonElement.getAsString());
                    }
                     fields.setSources(listaSources);
                    break;
            }

        }

        extendedDocumentInformation.extendedDocumentfields.put(chaveObjeto,fields);
        return extendedDocumentInformation;
    }
}
