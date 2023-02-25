package com.project.postapp.controller;

import com.project.postapp.entities.Comment;
import com.project.postapp.requests.CommentCreateRequest;
import com.project.postapp.requests.CommentUpdateRequest;
import com.project.postapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
                                        @RequestParam Optional<Long> postId){
        return commentService.getAllComments(userId,postId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest request){
        return commentService.createComment(request);
    }
    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updateRequest){
        return commentService.updateCommentById(commentId, updateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteCommentById(commentId);
    }
}
