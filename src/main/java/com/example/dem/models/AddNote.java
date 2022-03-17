package com.example.dem.models;

import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class AddNote {
    private Long id;
    private List<Long> note_ids;
}