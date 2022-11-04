
package com.example.portpals.models.flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flight {

    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("icao")
    @Expose
    private String icao;
    @SerializedName("codeshared")
    @Expose
    private Object codeshared;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public Object getCodeshared() {
        return codeshared;
    }

    public void setCodeshared(Object codeshared) {
        this.codeshared = codeshared;
    }

}
