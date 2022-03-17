package com.example.dem.repository;


import com.example.dem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<User, Long> {
}
