package com.imobanco.autenticador.api.client.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Generated("jsonschema2pojo")
@Getter @Setter
public class ExtendedDocumentInformation {

    @Expose
    public Map<String,ExtendedDocumentInformationFields> extendedDocumentfields;

}