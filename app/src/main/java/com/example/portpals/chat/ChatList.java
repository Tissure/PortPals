package com.example.portpals.chat;

public class ChatList {

    private String UID, displayName, message, date, time;

    public ChatList(String UID, String displayName, String message, String date, String time) {
        this.displayName = displayName;
        this.UID = UID;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUID() {
        return UID;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
