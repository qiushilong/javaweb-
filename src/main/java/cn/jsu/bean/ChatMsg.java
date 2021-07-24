package cn.jsu.bean;

import java.util.Date;

public class ChatMsg {
    private Integer roomMsgId;
    private Integer roomId;
    private Integer userId;
    private Date date;
    private String content;

    public ChatMsg(){}

    public ChatMsg(Integer roomMsgId, Integer roomId, Integer userId, Date date, String content) {
        this.roomMsgId = roomMsgId;
        this.roomId = roomId;
        this.userId = userId;
        this.date = date;
        this.content = content;
    }

    public Integer getRoomMsgId() {
        return roomMsgId;
    }

    public void setRoomMsgId(Integer roomMsgId) {
        this.roomMsgId = roomMsgId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CharMsg{" +
                "roomMsgId=" + roomMsgId +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }
}
