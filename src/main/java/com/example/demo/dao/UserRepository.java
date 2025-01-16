package com.example.demo.dao;

import com.example.demo.bean.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount(String account);

    User findByEmail(String email);

    User findByPhone(String phone);
}
