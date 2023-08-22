package com.imobanco.autenticador.api.client.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ExtendedAddresDataResponse {

    @SerializedName("Result")
    @Expose
    List<Result> results;
    @SerializedName("QueryId")
    @Expose
    public String queryId;
    @SerializedName("ElapsedMilliseconds")
    @Expose
    public Integer elapsedMilliseconds;
    @SerializedName("QueryDate")
    @Expose
    public String queryDate;
    @SerializedName("Status")
    @Expose
    public Status status;
    @SerializedName("Evidences")
    @Expose
    public Evidences evidences;


}
