package com.example.portpals.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event implements Parcelable {

    @SerializedName("host")
    @Expose
    private String host;

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

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("user")
    @Expose
    private User user;

    protected Event(Parcel in) {
        name = in.readString();
        id = in.readString();
        upTime = in.readString();
        capacity = in.readInt();
        tags = in.createStringArrayList();
        longitude = in.readString();
        latitude = in.readString();
        description = in.readString();
        user = in.readParcelable(ClassLoader.getSystemClassLoader());
    }

    public Event() {}

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(longitude);
        parcel.writeString(latitude);
        parcel.writeString(id);
        parcel.writeString(upTime);
        parcel.writeInt(capacity);
        parcel.writeList(tags);
        parcel.writeString(description);
        parcel.writeParcelable(user, i);
    }

}