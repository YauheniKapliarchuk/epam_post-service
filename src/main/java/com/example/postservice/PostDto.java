package com.example.postservice;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PostDto {

    private Long authorId;
    private String text;
    private String topic;
}
