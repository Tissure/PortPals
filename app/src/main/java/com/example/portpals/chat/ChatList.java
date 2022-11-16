package com.example.portpals.chat;

public class ChatList {

    private String UID, message, date, time;

    public ChatList(String UID, String message, String date, String time) {
        this.UID = UID;
        this.message = message;
        this.date = date;
        this.time = time;
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
