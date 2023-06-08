package com.spring.blog.springbootblogrestapi.repository;

import com.spring.blog.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
