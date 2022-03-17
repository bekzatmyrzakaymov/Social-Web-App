package com.example.dem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.dem.entity.Note;
import com.example.dem.entity.User;
import com.example.dem.helpers.ValidateHelper;
import com.example.dem.repository.FriendRepository;
import com.example.dem.repository.NoteRepository;
import com.example.dem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final FriendRepository friendRepository;

    List<User> friends = new ArrayList<>();

    @Autowired
    public UserService(UserRepository userRepository, NoteRepository noteRepository, FriendRepository friendRepository){
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.friendRepository = friendRepository;
    }


    public List<User> get(){
        return (List) userRepository.findAll();

    }

    public boolean addProductsToUser(Integer userId, List<Integer> productIds) {
        Optional<User> userOptional = userRepository.findById(userId.longValue());
        User user = userOptional.orElse(null);
        if(user == null) {
            return false;
        }


        System.out.println(friends);
        productIds.forEach(id -> friendRepository.findById(id.longValue()).ifPresent(p -> friends.add(p)));
        System.out.println(friends);
        user.setFriends(friends);
        userRepository.save(user);
        return true;

    }

    public User getbyEm(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean saveUser(User user){

        if(!ValidateHelper.validate(user.getEmail())) {
            return false;
        }
        User byEmail = userRepository.findByEmail(user.getEmail());
        if(byEmail != null) {
            return false;
        }
        user.setRoles("ROLE_USER");
        encodePassword(user);
        userRepository.save(user);
        return true;
    }

    public boolean updateUser(User user){
        userRepository.save(user);
        return true;
    }


    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
  /*  public boolean saveUser(User userRequest) {
       if(!ValidateHelper.validate(userRequest.getEmail())) {
            return false;
        }
        User byEmail = userRepository.findByEmail(userRequest.getEmail());
        if(byEmail != null) {
            return false;
        }
        User user = new User(userRequest.getFirstName(), userRequest.getSecondName(),
                userRequest.getEmail(), userRequest.getPassword());

        userRepository.save(userRequest);
        return true;
    }
  */

    public User existUser(User user){
        User user1 = userRepository.findByEmail(user.getEmail());
        if (user1 == null){
            return null;
        }
        else if (user1.getPassword().equals(user.getPassword())){
           return user1;
        }
        return null;
    }

    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }

    public void addNotes(Long user_id, List<Long> note_ids){
        Optional<User> userOptional = userRepository.findById(user_id);
        User user = userOptional.orElse(null);
        List<Note> notes = user.getNotes();
        note_ids.forEach(id -> noteRepository.findById(id).ifPresent(p -> notes.add(p)));
        user.setNotes(notes);
        userRepository.save(user);
    }

  /*  public boolean addNotesToUser(Integer userId, List<Integer> notesIds) {
        Optional<User> userOptional = userRepository.findById(userId.longValue());
        User user = userOptional.orElse(null);
        if(user == null) {
            return false;
        }
        List<Note> notes = new ArrayList<>();
        notesIds.forEach(id -> noteRepository.findById(id.longValue()).ifPresent(p -> notes.add(p)));
        user.setNotes(notes);
        userRepository.save(user);
        return true;
    } */

    public User getUser(Integer id) {
        return userRepository.findById(id.longValue()).orElse(null);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean deleteUser(String email){
        userRepository.deleteByEmail(email);
        return true;
    }
}
