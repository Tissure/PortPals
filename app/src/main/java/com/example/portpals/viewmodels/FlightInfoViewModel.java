package com.example.portpals.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.Date;

public class FlightInfoViewModel extends ViewModel {
    String departureAirport, arrivalAirport;
    Date departureTime, arrivalTime;

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }
    // TODO: Implement the ViewModel
}