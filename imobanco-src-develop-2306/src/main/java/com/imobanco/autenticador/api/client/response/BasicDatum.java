package com.imobanco.autenticador.api.client.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Generated("jsonschema2pojo")
@Getter @Setter
public class BasicDatum {

    @SerializedName("Code")
    @Expose
    public Integer code;
    @SerializedName("Message")
    @Expose
    public String message;

}