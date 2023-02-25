package com.project.postapp.services;

import com.project.postapp.entities.Comment;
import com.project.postapp.entities.Like;
import com.project.postapp.entities.Post;
import com.project.postapp.entities.User;
import com.project.postapp.repository.LikeRepository;
import com.project.postapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

        public LikeService(LikeRepository likeRepository,
                          UserService userService, PostService postService){
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }
    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        }
        return likeRepository.findAll();
    }

    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createLike(LikeCreateRequest request) {
        User user = userService.getUserById(request.getUserId());
        Post post = postService.getPostById(request.getPostId());
        if (user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);
        }
        return null;
    }

    public void deleteLikeById(Long likeId) {
            likeRepository.deleteById(likeId);
    }
}
