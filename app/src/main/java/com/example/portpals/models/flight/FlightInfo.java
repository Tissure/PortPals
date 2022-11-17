
package com.example.portpals.models.flight;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.portpals.util.FlightInfoManager;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightInfo extends ViewModel {

    private static FlightInfo instance = null;
    
    public static synchronized FlightInfo getInstance() {
        if (null == instance)
            instance = new FlightInfo();
        return instance;
    }

    @SerializedName("flight_date")
    @Expose
    private String flightDate;
    @SerializedName("flight_status")
    @Expose
    private String flightStatus;
    @SerializedName("departure")
    @Expose
    private Departure departure;
    @SerializedName("arrival")
    @Expose
    private Arrival arrival;
    @SerializedName("airline")
    @Expose
    private Airline airline;
    @SerializedName("flight")
    @Expose
    private Flight flight;
    @SerializedName("aircraft")
    @Expose
    private Object aircraft;
    @SerializedName("live")
    @Expose
    private Object live;

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Object getAircraft() {
        return aircraft;
    }

    public void setAircraft(Object aircraft) {
        this.aircraft = aircraft;
    }

    public Object getLive() {
        return live;
    }

    public void setLive(Object live) {
        this.live = live;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FlightInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("flightDate");
        sb.append('=');
        sb.append(((this.flightDate == null)?"<null>":this.flightDate));
        sb.append(',');
        sb.append("flightStatus");
        sb.append('=');
        sb.append(((this.flightStatus == null)?"<null>":this.flightStatus));
        sb.append(',');
        sb.append("departure");
        sb.append('=');
        sb.append(((this.departure == null)?"<null>":this.departure));
        sb.append(',');
        sb.append("arrival");
        sb.append('=');
        sb.append(((this.arrival == null)?"<null>":this.arrival));
        sb.append(',');
        sb.append("airline");
        sb.append('=');
        sb.append(((this.airline == null)?"<null>":this.airline));
        sb.append(',');
        sb.append("flight");
        sb.append('=');
        sb.append(((this.flight == null)?"<null>":this.flight));
        sb.append(',');
        sb.append("aircraft");
        sb.append('=');
        sb.append(((this.aircraft == null)?"<null>":this.aircraft));
        sb.append(',');
        sb.append("live");
        sb.append('=');
        sb.append(((this.live == null)?"<null>":this.live));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
