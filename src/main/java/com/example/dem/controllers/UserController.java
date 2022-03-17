package com.example.dem.controllers;

import com.example.dem.DemApplication;
import com.example.dem.entity.Note;
import com.example.dem.entity.User;
import com.example.dem.helpers.ValidateHelper;
import com.example.dem.models.AddNote;
import com.example.dem.models.AddNoteRequest;
import com.example.dem.services.CommService;
import com.example.dem.services.NoteService;
import com.example.dem.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = Logger.getLogger(DemApplication.class.getName());
    private UserService userService;
    private NoteService noteService;
    private CommService commService;

    @Autowired
    public UserController(CommService commService, UserService userService, NoteService noteService) {
        this.commService = commService;
        this.userService = userService;
        this.noteService = noteService;
    }


    @GetMapping("/getAllUsers")
    public String getUsers(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        logger.info("list of users are returned");
        return "user-list";
    }

    @GetMapping("/internet")
    public String internet(Model model){
        List<User> users = userService.getUsers();
        List<Note> notes = noteService.getNotesUs();
        model.addAttribute("users", users);
        model.addAttribute("notes", notes);
        logger.info("list of users, posts and comments are returned");
        return "internet";
    }


    @GetMapping("/friendsall")
    public String friend(Model model ){
        List<User> product = userService.get();
        model.addAttribute("userRequest", product);
        logger.info("list of friends are returned");
        return "listUsers";
    }


    @GetMapping("/addFriend/{id}")
    public String addFriends(@PathVariable("id") List<Integer> id, Principal principal) {
        User user = userService.getbyEm(principal.getName());
        long idf = user.getId();
        System.out.println(idf);
        boolean res = userService.addProductsToUser((int)idf, id);
        if(res){
            logger.info("after friendship request friend is added");
            return "redirect:/user/friendsall";
        }
        logger.info("user is not logged in");
        return "forbidden";
    }

 /*   @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    } */

    @GetMapping("/create-get")
    public String createUserForm (User user){
        logger.info("create user form page returned");
        return "user-create";
    }

    @PostMapping("/create-post")
    public String create(User user){
        boolean result = userService.saveUser(user);
        Long idd = user.getId();
        if (result) return "redirect:/login";
        else {
            logger.info("ERROR: post cannot be created");
            return "notFound";
        }
    }

    @GetMapping("/login-form")
    public String userForm(User user){
        return "login-page";
    }

    @PostMapping("/login-post")
    public String login(User user, Model model){
        User user1 = userService.existUser(user);
        List<Note> notes = noteService.getNotesUs();
        model.addAttribute("notes", notes);
        model.addAttribute("user", user1);
        return "profile";
    }

  /*  @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserRequest userRequest) {

        boolean result = userService.saveUser(userRequest);
        if (result) {
            return new ResponseEntity("user created", HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("bad request");
    } */

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update-post")
    public String updateUser(User user){
        boolean updated = userService.updateUser(user);
        Long idd = user.getId();
        if(updated){
            logger.info("Updated successfully");
        }
        else{
            logger.info("FAILED");
        }
        return "redirect:/user/getUser/" + idd;
    }

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        List<Note> notes = noteService.getNotesUs();
        model.addAttribute("user", user);
        model.addAttribute("notes", notes);
        if(userService.findById(id) == null){
            logger.info("ERROR: user not found");
            return "notFound";
        }
        logger.info("user found: return user page");
        return "profile";
    }

  /*  @GetMapping("/get")
    public ResponseEntity getUserById(@RequestParam Integer id) {
        User userRequest = userService.getUser(id);
        if (userRequest == null) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userRequest);
    } */

    @GetMapping("/email")
    public ResponseEntity checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(ValidateHelper.validate(email));
    }

    @GetMapping("/addNotes-get")
    public String addNotesForm(AddNote addNote){
        return "user-note-create";
    }

    @PostMapping("/addNotes-post")
    public String addNotesPost(AddNote addNote){
        userService.addNotes(addNote.getId(), addNote.getNote_ids());
        logger.info("successfully created");
        logger.info("all users list");
        return "redirect:/user/getAllUsers";
    }

 /*   @PostMapping("/addNotes")
    public ResponseEntity addNotes(@RequestBody AddNoteRequest request) {
        boolean result = userService.addNotesToUser(request.getUserId(), request.getNotesIds());
        if (!result){
            return new ResponseEntity("User not found",HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body("Note(s) added");
    } */

    @GetMapping("delete-get/{email}")
    public String deleteUserByEmail(@PathVariable("email") String email){
        userService.deleteUser(email);
        logger.info("delete user");
        return "redirect:/user/create-get";
    }

  /*  @PostMapping("/delete-user")
    public ResponseEntity deleteUserByEmail(@RequestParam String email){
        boolean result = userService.deleteUser(email);
        if(result) {
            return new ResponseEntity("User deleted", HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("bad");
    } */
}
