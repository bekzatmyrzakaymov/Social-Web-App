package com.example.dem.controllers;

import com.example.dem.DemApplication;
import com.example.dem.entity.Note;
import com.example.dem.entity.User;
import com.example.dem.models.NoteRequest;
import com.example.dem.services.NoteService;

import com.example.dem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    Logger logger = Logger.getLogger(DemApplication.class.getName());
    private NoteService noteService;
    private UserService userService;

    @Autowired
    public NoteController(NoteService noteService, UserService userService){
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/getAllNotes")
    public String getNotes(Model model){
        List<Note> notes = noteService.getNotes();
        model.addAttribute("notes", notes);
        logger.info("list of all notes returned");
        return "note-list";
    }

    @GetMapping("/createNote-get")
    public String createNoteForm (Note note){
        logger.info("note creation page");
        return "note-create";
    }

    @PostMapping("/createNote-post")
    public String create(Note note){
        boolean result = noteService.create(note);
        if (result) {
            logger.info("creating note");
            return "redirect:/note/getAllNotes";
        }
        logger.info("error occured");
         return "notFound";
    }

    @GetMapping("/createNote-get/{id}")
    public String createNoteForm (@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("note",new Note());
        logger.info("user note connection");
        return "user-note-connect";
    }

    @PostMapping("/createNote-post/{id}")
    public String create(@PathVariable("id") Long id, Note note){
 boolean result = noteService.create(note, id);
       if (result) {
           logger.info("post, note created");
           return "redirect:/user/getUser/" + id;
       }
        logger.info("error occured post wasnt created");
       return "notFound";
    }

 //   @PostMapping("/createNote-post/{id}")
 //   public String create(@PathVariable("id") Long id, Note note, Model model){
 //       boolean result = noteService.create(note, id);
 //       User user = userService.findById(id);
 //       model.addAttribute("user", user);
 //       if (result) return "redirect:/user/getUser";
 //       else return "bad";
 //   }

 /*   @PostMapping("/create")
    public Note create(@RequestBody NoteRequest note){
        return noteService.create(note);
    } */

    @GetMapping("/note-update/{note_id}")
    public String updateNoteForm(@PathVariable("note_id") Long note_id, Model model){
        Note note = noteService.findById(note_id);
        model.addAttribute("note", note);
        logger.info("updating note " + note);
        return "note-update";
    }

    @PostMapping("/note-update-post")
    public String updateNote(Note note){
        noteService.updateNote(note);
        logger.info("updating post " + note);
        return "redirect:/user/create-get";
    }

 /*   @PostMapping("/update-note")
    public ResponseEntity updateNote(@RequestBody Note request){
        noteService.update(request);
        return new ResponseEntity("updated", HttpStatus.ACCEPTED);
    } */

    @GetMapping("delete-get/{content}/{id}")
    public String deleteNote(@PathVariable("content") String content, @PathVariable("id") Long user_id){
        noteService.deleteNote(content, user_id);
        logger.info("deleting note with content" + content);
        return "redirect:/user/getUser/" + user_id;
    }

  /*  @PostMapping("/delete-note")
    public ResponseEntity deleteNoteById(@RequestBody Note request){
        boolean result = noteService.deleteNote(request);
        if (result){
            return new ResponseEntity("Note deleted",HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("bad");
    } */
}