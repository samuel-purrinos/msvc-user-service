package com.uichesoh.user.repository;

import com.uichesoh.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, String> {
}
