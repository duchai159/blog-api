package com.spring.blog.springbootblogrestapi.repository;

import com.spring.blog.springbootblogrestapi.entity.Comment;
import com.spring.blog.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByPost(Post post);

    List<Comment> findAllByPost(Post post);
}
