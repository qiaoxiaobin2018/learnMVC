package cn.itcast.user.domain;

/*
* 实体类
* */
public class User {
    private String username;
    private String password;
    private String age;
    private String gender;
    private String verifycode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    public User() {
    }

    public User(String username, String password, String age, String gender, String verifycode) {

        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.verifycode = verifycode;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", verifycode='" + verifycode + '\'' +
                '}';
    }
}
