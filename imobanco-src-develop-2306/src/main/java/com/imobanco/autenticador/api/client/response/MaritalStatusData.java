package com.imobanco.autenticador.api.client.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Generated("jsonschema2pojo")
@Getter @Setter
public class MaritalStatusData {

    @SerializedName("MaritalStatus")
    @Expose
    public String maritalStatus;
    @SerializedName("MaritalStatusSource")
    @Expose
    public String maritalStatusSource;
    @SerializedName("MaritalStatusLastUpdateDate")
    @Expose
    public String maritalStatusLastUpdateDate;

}