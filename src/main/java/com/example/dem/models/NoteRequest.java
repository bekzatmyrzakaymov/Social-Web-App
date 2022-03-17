package com.example.dem.models;

import lombok.Data;

import java.util.Date;

@Data
public class NoteRequest {
    private String content;
    private Date date;
}
