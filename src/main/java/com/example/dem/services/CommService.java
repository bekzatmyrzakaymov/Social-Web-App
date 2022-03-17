package com.example.dem.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.dem.entity.Comm;
import com.example.dem.entity.Note;
import com.example.dem.entity.User;
import com.example.dem.models.AddNote;
import com.example.dem.models.NoteRequest;
import com.example.dem.repository.CommRepository;
import com.example.dem.repository.NoteRepository;

import com.example.dem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class    CommService {

    private final CommRepository commRepository;
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public CommService(CommRepository commRepository, NoteRepository noteRepository, UserRepository userRepository){
        this.commRepository = commRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public boolean create(Comm comm){
        Comm comm1 = comm;
        comm1.setDate((new Date()));
        commRepository.save(comm1);
        return true;
    }

    public boolean create(Comm comm, Long note_id){
        Comm comm1 = comm;
        comm1.setDate((new Date()));
        commRepository.save(comm1);
        List<Long> comm_ids = new ArrayList<>();
        comm_ids.add(note_id);
        Optional<Note> noteOptional = noteRepository.findById(note_id);
        Note note = noteOptional.orElse(null);
        List<Comm> comms = note.getComments();
        comms.add(comm);
        //note_ids.forEach(id -> noteRepository.findById(id).ifPresent(p -> notes.add(p)));
        note.setComments(comms);
        noteRepository.save(note);

        return true;
    }

    public List<Comm> getComms(){
        return (List<Comm>) commRepository.findAll();
    }

    public List<Comm> getCommsUs(){
        return (List<Comm>) commRepository.findAll();
    }

    public Comm findById(Long id){
        return commRepository.findById(id).orElse(null);
    }

 /*   public Note create(NoteRequest request) {
        request.setDate(new Date());
        return noteRepository.save(new Note(request));
    } */

    public void updateComm(Comm comm){
        comm.setDate(new Date());
        // note.setContent(note.getContent());
        commRepository.save(comm);
        //  return true;
    }

 /*   public Note update(Note request) {
        request.setDate(new Date());
        request.setContent(request.getContent());
        return noteRepository.save(request);
    } */

//    public void deleteComm(String content, Long note_id){
//        Optional<Note> noteOptional = userRepository.findById(note_id);
//        Note note = noteOptional.orElse(null);
//        Comm comm = commRepository.findByContent(content);
//        note.getComments().remove(comm);
//        commRepository.deleteByContent(content);
//    }
}
