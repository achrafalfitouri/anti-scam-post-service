package com.anti_scam.post.repository;

import com.anti_scam.post.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
