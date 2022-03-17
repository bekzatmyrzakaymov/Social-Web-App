package com.example.dem.entity;

import com.example.dem.models.CommRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.dem.models.NoteRequest;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
public class Comm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comm_id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Date date;

    public Comm(CommRequest commRequest) {
        this.content = commRequest.getContent();
        this.date = commRequest.getDate();
    }
}