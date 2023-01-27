package com.example.postservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping()
    public Post createPost(@RequestBody PostDto userDto) {
        return postService.createPost(userDto);
    }

    @GetMapping(value = "/{id}")
    public Optional<Post> getUserById(@PathVariable("id") long id) {
        return postService.getPostById(id);
    }

    @PutMapping(value = "/{id}")
    public Post updatePostById(@PathVariable("id") long id, @RequestBody PostDto userDto) {
        return postService.updatePostById(id, userDto);
    }

    @DeleteMapping(value = "/{id}")
    public String deletePostById(@PathVariable("id") long id) {
        postService.deletePostById(id);
        return String.format("User with id = %s was deleted", id);
    }
}
