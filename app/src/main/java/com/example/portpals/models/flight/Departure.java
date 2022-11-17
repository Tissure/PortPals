
package com.example.portpals.models.flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Departure {

    @SerializedName("airport")
    @Expose
    private String airport;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("icao")
    @Expose
    private String icao;
    @SerializedName("terminal")
    @Expose
    private Object terminal;
    @SerializedName("gate")
    @Expose
    private String gate;
    @SerializedName("delay")
    @Expose
    private Object delay;
    @SerializedName("scheduled")
    @Expose
    private String scheduled;
    @SerializedName("estimated")
    @Expose
    private String estimated;
    @SerializedName("actual")
    @Expose
    private Object actual;
    @SerializedName("estimated_runway")
    @Expose
    private Object estimatedRunway;
    @SerializedName("actual_runway")
    @Expose
    private Object actualRunway;

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
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

    public Object getTerminal() {
        return terminal;
    }

    public void setTerminal(Object terminal) {
        this.terminal = terminal;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Object getDelay() {
        return delay;
    }

    public void setDelay(Object delay) {
        this.delay = delay;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public String getEstimated() {
        return estimated;
    }

    public void setEstimated(String estimated) {
        this.estimated = estimated;
    }

    public Object getActual() {
        return actual;
    }

    public void setActual(Object actual) {
        this.actual = actual;
    }

    public Object getEstimatedRunway() {
        return estimatedRunway;
    }

    public void setEstimatedRunway(Object estimatedRunway) {
        this.estimatedRunway = estimatedRunway;
    }

    public Object getActualRunway() {
        return actualRunway;
    }

    public void setActualRunway(Object actualRunway) {
        this.actualRunway = actualRunway;
    }

}
