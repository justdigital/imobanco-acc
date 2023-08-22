package com.imobanco.autenticador.api.client.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Generated("jsonschema2pojo")
@Getter @Setter
public class BasicData {

    @SerializedName("TaxIdNumber")
    @Expose
    public String taxIdNumber;
    @SerializedName("TaxIdCountry")
    @Expose
    public String taxIdCountry;
    @SerializedName("AlternativeIdNumbers")
    @Expose
    public AlternativeIdNumbers alternativeIdNumbers;
    @SerializedName("ExtendedDocumentInformation")
    @Expose
    public ExtendedDocumentInformation extendedDocumentInformation;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Aliases")
    @Expose
    public Aliases aliases;
    @SerializedName("Gender")
    @Expose
    public String gender;
    @SerializedName("NameWordCount")
    @Expose
    public Integer nameWordCount;
    @SerializedName("NumberOfFullNameNamesakes")
    @Expose
    public Integer numberOfFullNameNamesakes;
    @SerializedName("NameUniquenessScore")
    @Expose
    public String nameUniquenessScore;
    @SerializedName("FirstNameUniquenessScore")
    @Expose
    public Float firstNameUniquenessScore;
    @SerializedName("FirstAndLastNameUniquenessScore")
    @Expose
    public Float firstAndLastNameUniquenessScore;
    @SerializedName("BirthDate")
    @Expose
    public LocalDate birthDate;
    @SerializedName("Age")
    @Expose
    public Integer age;
    @SerializedName("ZodiacSign")
    @Expose
    public String zodiacSign;
    @SerializedName("ChineseSign")
    @Expose
    public String chineseSign;
    @SerializedName("BirthCountry")
    @Expose
    public String birthCountry;
    @SerializedName("MotherName")
    @Expose
    public String motherName;
    @SerializedName("FatherName")
    @Expose
    public String fatherName;
    @SerializedName("MaritalStatusData")
    @Expose
    public MaritalStatusData maritalStatusData;
    @SerializedName("TaxIdStatus")
    @Expose
    public String taxIdStatus;
    @SerializedName("TaxIdOrigin")
    @Expose
    public String taxIdOrigin;
    @SerializedName("TaxIdFiscalRegion")
    @Expose
    public String taxIdFiscalRegion;
    @SerializedName("HasObitIndication")
    @Expose
    public Boolean hasObitIndication;
    @SerializedName("TaxIdStatusDate")
    @Expose
    public String taxIdStatusDate;
    @SerializedName("CreationDate")
    @Expose
    public String creationDate;
    @SerializedName("LastUpdateDate")
    @Expose
    public String lastUpdateDate;

}