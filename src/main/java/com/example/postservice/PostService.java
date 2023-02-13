package com.example.postservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Value("${user.service.host.url}")
    private String USER_SERVICE_HOST_URL;

    public Post createPost(PostDto postDto) {
        Post post = new Post();
        post.setAuthorId(postDto.getAuthorId());
        post.setText(postDto.getText());
        post.setPostedAt(LocalDateTime.now());
        post.setTopic(postDto.getTopic());

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> entity = new HttpEntity<>(new User());

        System.out.println("TTT:: " + USER_SERVICE_HOST_URL);

        ResponseEntity<User> currentUser = restTemplate.exchange(USER_SERVICE_HOST_URL + "users/" + postDto.getAuthorId(), HttpMethod.GET, entity, User.class);

        if (currentUser.getBody() != null) {
            int amount = currentUser.getBody().getAmountOfPosts() + 1;
            currentUser.getBody().setAmountOfPosts(amount);
        } else {
            throw new PostNotFoundException(String.format("User with userId = %s NOT FOUND...", postDto.getAuthorId()));
        }

        restTemplate.exchange(USER_SERVICE_HOST_URL + "users/count/" + postDto.getAuthorId(), HttpMethod.PUT, currentUser, User.class);

        return postRepository.save(post);
    }

    public Optional<Post> getPostById(long id) {
        Optional<Post> currentPost = postRepository.findById(id);
        if (currentPost.isEmpty()) {
            throw new PostNotFoundException("Post doesn’t exist with given id = " + id);
        }
        return currentPost;
    }

    public Post updatePostById(long id, PostDto postDto) {
        Optional<Post> currentPost = postRepository.findById(id);
        if (currentPost.isEmpty()) {
            throw new PostNotFoundException("User doesn’t exist with given id = " + id);
        }
        currentPost.ifPresent(post -> {
            post.setText(postDto.getText());
            post.setPostedAt(LocalDateTime.now());
            post.setTopic(post.getTopic());
        });
        return postRepository.save(currentPost.get());
    }

    public void deletePostById(long id) {
        Optional<Post> currentPost = postRepository.findById(id);
        if (currentPost.isEmpty()) {
            throw new PostNotFoundException("Post doesn’t exist with given id = " + id);
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> entity = new HttpEntity<>(new User());

        ResponseEntity<User> currentUser = restTemplate.exchange(USER_SERVICE_HOST_URL + "users/" + currentPost.get().getAuthorId(), HttpMethod.GET, entity, User.class);

        if (currentUser.getBody() != null) {
            int amount = currentUser.getBody().getAmountOfPosts() - 1;
            currentUser.getBody().setAmountOfPosts(amount);
        }

        restTemplate.exchange(USER_SERVICE_HOST_URL + "users/count/" + currentPost.get().getAuthorId(), HttpMethod.PUT, currentUser, User.class);

        postRepository.delete(currentPost.get());
    }
}
