package cn.edu.swjtu.pojo;

import lombok.Data;
import lombok.ToString;

@Data
public class User {
    private String uid;
    private String username;
    private String password_hash;
    private String avatar;
    private String roles;
    private String email;
    private int group_id;


    public User(String username, String password_hash) {
        this.username = username;
        this.password_hash = password_hash;
    }

    public User(String username, String avatar, String email, String uid) {
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.uid = uid;
    }
    public User(){}
}
