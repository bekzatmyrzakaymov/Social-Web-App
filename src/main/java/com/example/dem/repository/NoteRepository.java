package com.example.dem.repository;

import com.example.dem.entity.Note;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface NoteRepository extends CrudRepository<Note, Long> {
    @Transactional
    Long deleteByContent(String content);
    @Transactional
    Note findByContent(String content);
}
