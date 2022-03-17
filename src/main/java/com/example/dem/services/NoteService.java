package com.example.dem.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.dem.entity.Note;
import com.example.dem.entity.User;
import com.example.dem.models.AddNote;
import com.example.dem.models.NoteRequest;
import com.example.dem.repository.NoteRepository;

import com.example.dem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, UserRepository userRepository){
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public boolean create(Note note){
        Note note1 = note;
        note1.setDate((new Date()));
        noteRepository.save(note1);
        return true;
    }

    public boolean create(Note note, Long user_id){
        Note note1 = note;
        note1.setDate((new Date()));
        noteRepository.save(note1);
        List<Long> note_ids = new ArrayList<>();
        note_ids.add(user_id);
        Optional<User> userOptional = userRepository.findById(user_id);
        User user = userOptional.orElse(null);
        List<Note> notes = user.getNotes();
        notes.add(note);
        //note_ids.forEach(id -> noteRepository.findById(id).ifPresent(p -> notes.add(p)));
        user.setNotes(notes);
        userRepository.save(user);

        return true;
    }

    public List<Note> getNotes(){
        return (List<Note>) noteRepository.findAll();
    }

    public List<Note> getNotesUs(){
        return (List<Note>) noteRepository.findAll();
    }

    public Note findById(Long id){
        return noteRepository.findById(id).orElse(null);
    }

 /*   public Note create(NoteRequest request) {
        request.setDate(new Date());
        return noteRepository.save(new Note(request));
    } */

    public void updateNote(Note note){
        note.setDate(new Date());
       // note.setContent(note.getContent());
        noteRepository.save(note);
      //  return true;
    }

 /*   public Note update(Note request) {
        request.setDate(new Date());
        request.setContent(request.getContent());
        return noteRepository.save(request);
    } */

    public void deleteNote(String content, Long user_id){
        Optional<User> userOptional = userRepository.findById(user_id);
        User user = userOptional.orElse(null);
        Note note = noteRepository.findByContent(content);
        user.getNotes().remove(note);
        noteRepository.deleteByContent(content);
    }
}