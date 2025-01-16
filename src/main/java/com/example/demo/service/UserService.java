package com.example.demo.service;

import com.example.demo.bean.Const;
import com.example.demo.bean.dto.request.UserRequest;
import com.example.demo.bean.entity.User;
import com.example.demo.dao.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String createAdmin(String account,
                              String password,
                              String name) {
        if (userRepository.findByAccount(account) != null) {
            return "account already exists";
        }
        userRepository.save(
                UserRequest.builder().account(account).password(password).name(name).roleId(Const.ADMIN_ID).build().toEntity()
        );
        return "success";
    }

    public String createUser(String account,
                             String password,
                             String name,
                             String email,
                             String phone) {
        if (userRepository.findByAccount(account) != null) {
            return "account already exists";
        }
        if (userRepository.findByEmail(email) != null) {
            return "email already exists";
        }
        if (userRepository.findByPhone(phone) != null) {
            return "phone already exists";
        }
        userRepository.save(
                UserRequest.builder().account(account).password(password).name(name).email(email).phone(phone).roleId(Const.USER_ID).build().toEntity()
        );
        return "success";
    }

    public String updateUser(Long userId, String name, String email, String phone) {
        userRepository.findById(userId).ifPresent(user -> {
           if (name != null && !name.trim().isEmpty()) {
               user.setName(name);
           }
           if (email != null && !email.trim().isEmpty() && userRepository.findByEmail(email) == null) {
               user.setEmail(email);
           }
           if (phone != null && !phone.trim().isEmpty() && userRepository.findByPhone(phone) == null) {
               user.setPhone(phone);
           }
           userRepository.save(user);
        });
        return "success";
    }

    public User login(String account, String password) {
        User user = userRepository.findByAccount(account);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    public User findByAccount(String account) {
        return userRepository.findByAccount(account);
    }
}
