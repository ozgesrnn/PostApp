package com.project.postapp.controller;

import com.project.postapp.entities.Like;
import com.project.postapp.entities.User;
import com.project.postapp.requests.LikeCreateRequest;
import com.project.postapp.services.LikeService;
import com.project.postapp.services.PostService;
import com.project.postapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;


    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> userId,
                                  @RequestParam Optional<Long> postId){
        return likeService.getAllLikes(userId, postId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest request){
        return likeService.createLike(request);

    }
    @GetMapping("/{likeId}")
    public Like getLike(@PathVariable Long likeId){
        return likeService.getLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public  void  deleteLike(@PathVariable Long likeId){
        likeService.deleteLikeById(likeId);
    }

}
