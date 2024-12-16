package com.anti_scam.post.models;



import java.time.LocalDateTime;

public class Dislikes {
    private String id;
    private String username;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    // Default constructor
    public Dislikes() {
    }

    // Parameterized constructor
    public Dislikes(String id, String username,  LocalDateTime createdAt , LocalDateTime updatedAt)   {
        this.id = id;
        this.username = username;
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


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Dislikes{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
