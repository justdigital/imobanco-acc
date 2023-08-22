package com.imobanco.autenticador.api.client.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Generated("jsonschema2pojo")
@Getter @Setter
public class Result {

    @SerializedName("MatchKeys")
    @Expose
    public String matchKeys;
    @SerializedName("BasicData")
    @Expose
    public BasicData basicData;

}