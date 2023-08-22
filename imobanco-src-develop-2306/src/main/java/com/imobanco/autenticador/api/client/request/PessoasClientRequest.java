package com.imobanco.autenticador.api.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Datasets",
        "q"
})
@Getter
@Setter
@Builder
public class PessoasClientRequest {

    @JsonProperty("Datasets")
    public String datasets;
    @JsonProperty("q")
    public String q;

}
