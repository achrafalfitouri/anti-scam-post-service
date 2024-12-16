package com.anti_scam.post.controllers;

import com.anti_scam.post.models.Post;
import com.anti_scam.post.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Create a new post
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody Post request, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder("Validation failed: ");
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorMessage.append(error.getField())
                            .append(" - ")
                            .append(error.getDefaultMessage())
                            .append("; ");
                }
                return ResponseEntity.badRequest().body(errorMessage.toString());
            }

            String username = getAuthenticatedUsername();
            System.out.println("Authenticated Username: " + username);
            System.out.println("Payload Content: " + request.getContent());

            Post createdPost = postService.createPost(username, request.getContent());
            return ResponseEntity.ok(createdPost);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Invalid input: " + ex.getMessage());
        }
    }

    // Get a post by ID
    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable String postId) {
        return postService.getPostById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    // Public content example
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    // Add a comment to a post
    @PostMapping("/{postId}/comments")
    public void addComment(@PathVariable String postId, @RequestBody Post request) {
        String username = getAuthenticatedUsername();
        System.out.println("Authenticated Username: " + username);
        postService.addComment(postId, username, request.getContent());
    }

    // Like a post
    @PostMapping("/{postId}/like")
    public void likePost(@PathVariable String postId) {
        String username = getAuthenticatedUsername();
        System.out.println("Received like request for postId: " + postId + ", username: " + username);
        postService.likePost(postId, username);
    }

    // Dislike a post
    @PostMapping("/{postId}/dislike")
    public void dislikePost(@PathVariable String postId) {
        String username = getAuthenticatedUsername();
        System.out.println("Received dislike request for postId: " + postId + ", username: " + username);
        postService.dislikePost(postId, username);
    }

    // Helper method to get the authenticated username
    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // This returns the username from the JWT
    }
}
