package com.example.demo.bean.dto.request;

import com.example.demo.bean.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String account;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Long roleId;
    public User toEntity() {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRoleId(roleId);
        return user;
    }
}
