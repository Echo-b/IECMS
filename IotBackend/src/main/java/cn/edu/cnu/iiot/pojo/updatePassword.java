package cn.edu.cnu.iiot.pojo;

import lombok.Data;

@Data
public class updatePassword {
    private String username;
    private String oldPassword;
    private String newPassword;

}
