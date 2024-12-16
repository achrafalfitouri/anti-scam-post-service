package com.anti_scam.post.models;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Comment {
    private String id;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    // Default constructor
    public Comment() {
    }

    // Parameterized constructor
    public Comment(String id, String username, String content, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
