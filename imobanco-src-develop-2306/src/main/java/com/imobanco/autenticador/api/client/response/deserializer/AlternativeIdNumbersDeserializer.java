package com.imobanco.autenticador.api.client.response.deserializer;

import com.google.gson.*;
import com.imobanco.autenticador.api.client.response.AlternativeIdNumbers;
import com.imobanco.autenticador.api.client.response.ExtendedDocumentInformation;
import com.imobanco.autenticador.api.client.response.ExtendedDocumentInformationFields;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class AlternativeIdNumbersDeserializer implements JsonDeserializer<AlternativeIdNumbers> {


    @Override
    public AlternativeIdNumbers deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        AlternativeIdNumbers alternativeIdNumbers = new AlternativeIdNumbers();
        alternativeIdNumbers.setAlternativeNumbersList(new HashMap<>());

        JsonObject jsonObject = element.getAsJsonObject();

        for (Entry<String, JsonElement> entry : jsonObject.entrySet())
        {
            alternativeIdNumbers.getAlternativeNumbersList().put(entry.getKey(),entry.getValue().getAsString());

        }


        return alternativeIdNumbers;
    }
}
