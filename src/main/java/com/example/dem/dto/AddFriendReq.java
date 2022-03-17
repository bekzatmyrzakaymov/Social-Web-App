package com.example.dem.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddFriendReq {
    private Integer userId;
    private List<Integer> productIds;
}


