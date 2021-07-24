package cn.jsu.bean;

import java.util.Date;

public class RoomUser {
    private Integer contrastId;
    private Integer roomId;
    private Integer userId;
    private Date speakTime;

    public RoomUser(){}

    public RoomUser(Integer contrastId, Integer roomId, Integer userId, Date speakTime) {
        this.contrastId = contrastId;
        this.roomId = roomId;
        this.userId = userId;
        this.speakTime = speakTime;
    }

    public Integer getContrastId() {
        return contrastId;
    }

    public void setContrastId(Integer contrastId) {
        this.contrastId = contrastId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSpeakTime() {
        return speakTime;
    }

    public void setSpeakTime(Date speakTime) {
        this.speakTime = speakTime;
    }

    @Override
    public String toString() {
        return "RoomUser{" +
                "contrastId=" + contrastId +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", speakTime=" + speakTime +
                '}';
    }
}
