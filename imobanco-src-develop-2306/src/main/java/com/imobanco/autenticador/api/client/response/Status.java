package com.imobanco.autenticador.api.client.response;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Generated("jsonschema2pojo")
@Getter @Setter
public class Status {

    @SerializedName("basic_data")
    @Expose
    public List<BasicDatum> basicData;

}