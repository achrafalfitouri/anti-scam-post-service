package com.anti_scam.post.services;

import com.anti_scam.post.models.Comment;
import com.anti_scam.post.models.Post;
import com.anti_scam.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;



    public Post createPost(String username, String content) {
        try {



                Post post = new Post();
                post.setUsername(username);
                post.setContent(content);
                post.setCreatedAt(LocalDateTime.now());
                post.setUpdatedAt(LocalDateTime.now());
                return postRepository.save(post);

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Token validation failed: " + e.getMessage());
        }
    }

    public Optional<Post> getPostById(String postId) {
        return postRepository.findById(postId);
    }

    public void addComment(String postId, String username, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = new Comment(java.util.UUID.randomUUID().toString(), username, content, LocalDateTime.now());
        post.addComment(comment);
        postRepository.save(post);
    }

    public void likePost(String postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.likePost(username);
        postRepository.save(post);
    }

    public void dislikePost(String postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.dislikePost(username);
        postRepository.save(post);
    }
}
