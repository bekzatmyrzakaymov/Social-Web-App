package com.example.dem.models;

import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class AddComm {
    private Long note_id;
    private List<Long> comm_ids;
}