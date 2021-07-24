package cn.jsu.bean;

public class Room {
    private Integer id;
    private Integer managerId;
    private String roomAccountId;
    private String roomName;
    private String msg;
    private boolean isPrivate;
    private String roomDeclaration;

    public Room(){}

    public Room(Integer id, Integer managerId, String roomAccountId, String roomName, String msg, boolean isPrivate, String roomDeclaration) {
        this.id = id;
        this.managerId = managerId;
        this.roomAccountId = roomAccountId;
        this.roomName = roomName;
        this.msg = msg;
        this.isPrivate = isPrivate;
        this.roomDeclaration = roomDeclaration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getRoomAccountId() {
        return roomAccountId;
    }

    public void setRoomAccountId(String roomAccountId) {
        this.roomAccountId = roomAccountId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getRoomDeclaration() {
        return roomDeclaration;
    }

    public void setRoomDeclaration(String roomDeclaration) {
        this.roomDeclaration = roomDeclaration;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", managerId=" + managerId +
                ", roomAccountId='" + roomAccountId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", msg='" + msg + '\'' +
                ", isPrivate=" + isPrivate +
                ", roomDeclaration='" + roomDeclaration + '\'' +
                '}';
    }
}
