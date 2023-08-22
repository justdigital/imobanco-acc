package com.imobanco.autenticador.api.client.response;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class AlternativeIdNumbers {

    @Expose
    public Map<String,String> alternativeNumbersList;
}
