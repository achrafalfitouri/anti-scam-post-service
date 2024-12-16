package com.anti_scam.post.models;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String username;

    @Size(max = 20)
    @NotBlank
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Collection<Comment> comments = new ArrayList<>();
    private Collection<Likes> likedBy = new ArrayList<>();
    private Collection<Dislikes> dislikedBy = new ArrayList<>();

    // Default constructor
    public Post() {
    }

    // Parameterized constructor
    public Post(String id, String username, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setLikedBy(List<Likes> likedBy) {
        this.likedBy = likedBy;
    }

    public void setDislikedBy(List<Dislikes> dislikedBy) {
        this.dislikedBy = dislikedBy;
    }

    // Utility methods


    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public int getLikeCount() {
        return likedBy.size();
    }

    public int getDislikeCount() {
        return dislikedBy.size();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        this.updatedAt = LocalDateTime.now();
    }

    public void likePost(String username) {
        // Check if the user has already liked the post
        if (likedBy.stream().anyMatch(like -> like.getUsername().equals(username))) {
            // If already liked, remove the like
            likedBy.removeIf(like -> like.getUsername().equals(username));
        } else {
            // Otherwise, add a new like
            likedBy.add(new Likes(id, username, LocalDateTime.now(), LocalDateTime.now()));

            // Remove any existing dislike from the user
            dislikedBy.removeIf(dislike -> dislike.getUsername().equals(username));
        }
        // Update the timestamp
        this.updatedAt = LocalDateTime.now();
    }

    public void dislikePost(String username) {
        // Check if the user has already disliked the post
        if (dislikedBy.stream().anyMatch(dislike -> dislike.getUsername().equals(username))) {
            // If already disliked, remove the dislike
            dislikedBy.removeIf(dislike -> dislike.getUsername().equals(username));
        } else {
            // Otherwise, add a new dislike
            dislikedBy.add(new Dislikes(id, username, LocalDateTime.now(), LocalDateTime.now()));

            // Remove any existing like from the user
            likedBy.removeIf(like -> like.getUsername().equals(username));
        }
        // Update the timestamp
        this.updatedAt = LocalDateTime.now();
    }



    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", comments=" + comments +
                ", likedBy=" + likedBy +
                ", dislikedBy=" + dislikedBy +
                '}';
    }
}
