
package com.example.portpals.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Airport {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("dst")
    @Expose
    private String dst;
    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("icao")
    @Expose
    private String icao;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("lon")
    @Expose
    private Object lon;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLon() {
        return lon;
    }

    public void setLon(Object lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

}
