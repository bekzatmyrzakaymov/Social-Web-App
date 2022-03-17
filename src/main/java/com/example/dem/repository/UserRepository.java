package com.example.dem.repository;

import com.example.dem.entity.User;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    @Transactional
    Long deleteByEmail(String email);
    User findByPassword(String password);
}
