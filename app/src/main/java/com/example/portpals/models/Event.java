package com.example.portpals.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("upTime")
    @Expose
    private String upTime;

    @SerializedName("capacity")
    @Expose
    private int capacity;

    @SerializedName("tags")
    @Expose
    private List<String> tags;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getUpTime() {
        return upTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
