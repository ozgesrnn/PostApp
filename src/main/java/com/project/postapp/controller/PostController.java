package com.project.postapp.controller;

import com.project.postapp.entities.Post;
import com.project.postapp.requests.PostCreateRequest;
import com.project.postapp.requests.PostUpdateRequest;
import com.project.postapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public  Post createPost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createPost(newPostRequest);
    }
    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId){
        return  postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updatePostById(postId, updatePost);

    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePostById(postId);
    }

}
