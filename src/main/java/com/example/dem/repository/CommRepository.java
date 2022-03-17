package com.example.dem.repository;

import com.example.dem.entity.Comm;
import com.example.dem.entity.Note;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface CommRepository extends CrudRepository<Comm, Long> {
    @Transactional
    Long deleteByContent(String content);
    @Transactional
    Note findByContent(String content);
}