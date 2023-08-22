package com.imobanco.autenticador.api.client.response.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.imobanco.autenticador.api.client.response.AlternativeIdNumbers;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        return LocalDate.parse(json.getAsString().substring(0,10) );

    }
}
