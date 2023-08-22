package com.imobanco.autenticador.api.client.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
@Getter
@Setter
public class ExtendedDocumentInformationFields {

    @SerializedName("DocumentNumber")
    @Expose
    public String documentNumber;
    @SerializedName("DocumentLast4Digits")
    @Expose
    public String documentLast4Digits;
    @SerializedName("DocumentIssuingAgency")
    @Expose
    public String documentIssuingAgency;
    @SerializedName("DocumentIssuingStateCode")
    @Expose
    public String documentIssuingStateCode;
    @SerializedName("CreationDate")
    @Expose
    public String creationDate;
    @SerializedName("LastUpdateDate")
    @Expose
    public String lastUpdateDate;
    @SerializedName("Sources")
    @Expose
    public List<String> sources;

}