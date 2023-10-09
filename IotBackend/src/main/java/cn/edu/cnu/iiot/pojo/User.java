package cn.edu.cnu.iiot.pojo;

import lombok.Data;

@Data
public class User {
    private String username;

    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
