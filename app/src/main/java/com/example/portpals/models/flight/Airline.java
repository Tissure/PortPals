
package com.example.portpals.models.flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Airline {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("icao")
    @Expose
    private String icao;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
