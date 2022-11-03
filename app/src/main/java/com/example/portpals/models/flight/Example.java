
package com.example;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Example {

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

}
