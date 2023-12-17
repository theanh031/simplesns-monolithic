package com.msa.post.controller;

import com.msa.post.domain.Post;
import com.msa.post.domain.PostDTO;
import com.msa.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getPostList();
    }
    @GetMapping("/{id}")
    public Post getPost(@PathVariable int id) {
        Optional<Post> post = postService.getPost(id);
        return post.map(e -> new ResponseEntity<>(e, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)).getBody();
    }
    @PostMapping
    public Post addPost(@RequestBody PostDTO postDTO) {
        return postService.addPost(postDTO.getTitle(), postDTO.getContent());
    }
    
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) {
        postService.delete(id);
    }
    
    
    
}