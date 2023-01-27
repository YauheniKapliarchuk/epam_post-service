package com.example.postservice;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    private Long id;
    private String username;
    private int amountOfPosts;
}
