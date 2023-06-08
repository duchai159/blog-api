package com.spring.blog.springbootblogrestapi.service;

import com.spring.blog.springbootblogrestapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);

    CommentDto getCommentById(Long postId, Long commentId);

    List<CommentDto> getAllComment(Long postId);

    CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentDto);

    void deleteCommentById(Long postId, Long commentId);
}
