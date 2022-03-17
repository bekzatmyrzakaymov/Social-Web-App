package com.example.dem.models;

import lombok.Data;

import java.util.List;

@Data
public class AddNoteRequest {
    private Integer userId;
    private List<Integer> notesIds;
}