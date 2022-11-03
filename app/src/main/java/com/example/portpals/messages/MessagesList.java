package com.example.portpals.messages;

public class MessagesList {
    private String name, mobile, lastMessage;
    private int unseenMessage;

    public MessagesList(String name, String mobile, String lastMessage, int unseenMessage) {
        this.name = name;
        this.mobile = mobile;
        this.lastMessage = lastMessage;
        this.unseenMessage = unseenMessage;

    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getUnseenMessage() {
        return unseenMessage;
    }
}
