package com.example.portpals.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatRoomInfo implements Parcelable {

    private String title;
    private String creatorUserName;
    private String participants;
    private int roomTime;
    private int terminalNum;
    private int imageId;

    public ChatRoomInfo(String title, String username, String participants, int roomTime, int terminalNum, int profile_picture) {
        this.title = title;
        this.creatorUserName = username;
        this.imageId = profile_picture;
        this.roomTime = roomTime;
        this.terminalNum = terminalNum;
        this.participants = participants;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public void setRoomTime(int roomTime) {
        this.roomTime = roomTime;
    }

    public void setTerminalNum(int terminalNum) {
        this.terminalNum = terminalNum;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public String getParticipants() {
        return participants;
    }

    public int getRoomTime() {
        return roomTime;
    }

    public int getTerminalNum() {
        return terminalNum;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return creatorUserName;
    }

    public int getProfile_picture() {
        return imageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUsername(String username) {
        this.creatorUserName = username;
    }

    public void setProfile_picture(int profile_picture) {
        this.imageId = profile_picture;
    }

    protected ChatRoomInfo(Parcel in) {
        title = in.readString();
        creatorUserName = in.readString();
        imageId = in.readInt();
    }

    public static final Creator<ChatRoomInfo> CREATOR = new Creator<ChatRoomInfo>() {
        @Override
        public ChatRoomInfo createFromParcel(Parcel in) {
            return new ChatRoomInfo(in);
        }

        @Override
        public ChatRoomInfo[] newArray(int size) {
            return new ChatRoomInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(creatorUserName);
        parcel.writeInt(imageId);
    }

    @Override
    public String toString() {
        return "ChatRoomInfo{" +
                "title='" + title + '\'' +
                ", creatorUserName='" + creatorUserName + '\'' +
                ", participants='" + participants + '\'' +
                ", roomTime=" + roomTime +
                ", terminalNum=" + terminalNum +
                ", imageId=" + imageId +
                '}';
    }
}
