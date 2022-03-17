package com.example.dem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.dem.models.NoteRequest;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long note_id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Date date;

    @OneToMany
    private List<Comm> comments;

    public Note(NoteRequest noteRequest) {
        this.content = noteRequest.getContent();
        this.date = noteRequest.getDate();
    }
}
