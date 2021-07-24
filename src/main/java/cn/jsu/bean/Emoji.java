package cn.jsu.bean;

public class Emoji {
    private Integer id;
    private Integer userId;
    private String emojiName;
    private String path;

    public Emoji(){}

    public Emoji(Integer id, Integer userId, String emojiName, String path) {
        this.id = id;
        this.userId = userId;
        this.emojiName = emojiName;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmojiName() {
        return emojiName;
    }

    public void setEmojiName(String emojiName) {
        this.emojiName = emojiName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "id=" + id +
                ", userId=" + userId +
                ", emojiName='" + emojiName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
