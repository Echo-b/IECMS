package cn.edu.swjtu.pojo;

import lombok.Data;
import lombok.ToString;

@Data
public class User {
    private String username;
    private String password_hash;
    private String avatar;
    private String roles;


    public User(String username, String password_hash) {
        this.username = username;
        this.password_hash = password_hash;
    }

    public User(){}
}
