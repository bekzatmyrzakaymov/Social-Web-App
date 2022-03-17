package com.example.dem.controllers;

import com.example.dem.DemApplication;
import com.example.dem.entity.Comm;
import com.example.dem.entity.Note;
import com.example.dem.entity.User;
import com.example.dem.models.NoteRequest;
import com.example.dem.services.CommService;
import com.example.dem.services.NoteService;

import com.example.dem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@Controller
@RequestMapping("/comm")
public class CommController {

    Logger logger = Logger.getLogger(DemApplication.class.getName());
    private NoteService noteService;
    private UserService userService;
    private CommService commService;

    @Autowired
    public CommController(CommService commService, NoteService noteService, UserService userService){
        this.commService = commService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/getAllComms")
    public String getComments(Model model){
        List<Comm> comms = commService.getComms();
        model.addAttribute("comments", comms);
        logger.info("list of comments returned");
        return "comm-list";
    }

    @GetMapping("/createComm-get")
    public String createCommForm (Comm comm){
        logger.info("comment creation");
        return "comm-create";
    }

    @PostMapping("/createComm-post")
    public String create(Comm comm){
        boolean result = commService.create(comm);
        if (result) {
            logger.info("comm created");
            return "redirect:/note/getAllComms";
        }
        logger.info("cannot be created comm");
        return "bad";
    }


    @GetMapping("/createComm-get/{user_id}/{id}")
    public String createCommForm (@PathVariable("id") Long id, Model model, @PathVariable("user_id") Long user_id){
        Note note = noteService.findById(id);
        User user = userService.findById(user_id);
        model.addAttribute("user", user);
        model.addAttribute("note", note);
        model.addAttribute("comm",new Comm());
        logger.info("post comment connection");
        return "note-comment-connect";
    }

    @PostMapping("/createComm-post/{user_id}/{id}")
    public String create(@PathVariable("id") Long id, Comm comm, @PathVariable("user_id") Long user_id){
        boolean result = commService.create(comm, id);
        if (result) {
            logger.info("comm created on user " + user_id + " note " + id);
            return "redirect:/user/getUser/" + user_id;
        }
        logger.info("bad request");
        return "bad";
    }

    @GetMapping("/createComm-get1/{id}")
    public String createCommForm1 (@PathVariable("id") Long id, Model model){
        Note note = noteService.findById(id);
        model.addAttribute("note", note);
        model.addAttribute("comm",new Comm());
        logger.info("post comment connection");
        return "note-comment-connect1";
    }

    @PostMapping("/createComm-post1/{id}")
    public String create1(@PathVariable("id") Long id, Comm comm){
        boolean result = commService.create(comm, id);
        if (result) {
            logger.info("creating comment");
            return "redirect:/note/getAllNotes";
        }
        logger.info("bad request");
        return "bad";
    }
}
