package cn.jsu.bean;

public class User {
    private Integer id;
    private String name;
    private String gender;
    private String accountId;
    private String password;
    private String qq;
    private String attitude;
    private String headShot;

    public User() {
    }

    public User(Integer id, String name, String gender, String accountId, String password, String qq, String attitude, String headShot) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.accountId = accountId;
        this.password = password;
        this.qq = qq;
        this.attitude = attitude;
        this.headShot = headShot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getHeadShot() {
        return headShot;
    }

    public void setHeadShot(String headShot) {
        this.headShot = headShot;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", accountId='" + accountId + '\'' +
                ", password='" + password + '\'' +
                ", qq='" + qq + '\'' +
                ", attitude='" + attitude + '\'' +
                ", headShot='" + headShot + '\'' +
                '}';
    }
}
