package com.example.dem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "usr")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String firstName;
    @Column(name = "surname")
    private String secondName;
    private String email;
    private String password;
    private String roles;
    @OneToMany (cascade=CascadeType.ALL)
    private List<Note> notes;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> friends;

    public User(final String firstName, final String secondName, final String email, final String password, final String roles) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}