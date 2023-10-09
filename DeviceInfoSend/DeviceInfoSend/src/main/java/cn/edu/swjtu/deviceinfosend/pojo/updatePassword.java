package cn.edu.swjtu.deviceinfosend.pojo;

import lombok.Data;

@Data
public class updatePassword {
    private String username;
    private String oldPassword;
    private String newPassword;

}
