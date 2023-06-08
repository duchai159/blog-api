package com.spring.blog.springbootblogrestapi.service.impl;

import com.spring.blog.springbootblogrestapi.entity.Comment;
import com.spring.blog.springbootblogrestapi.entity.Post;
import com.spring.blog.springbootblogrestapi.exception.BlogApiException;
import com.spring.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.spring.blog.springbootblogrestapi.payload.CommentDto;
import com.spring.blog.springbootblogrestapi.repository.CommentRepository;
import com.spring.blog.springbootblogrestapi.repository.PostRepository;
import com.spring.blog.springbootblogrestapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return mapToDto(comment);
    }

    @Override
    public List<CommentDto> getAllComment(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId.toString()));
        List<Comment> comments = commentRepository.findAllByPost(post);
        return comments.stream().map(this::mapToDto).toList();
    }

    @Override
    public CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId.toString()));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId.toString()));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .build();
    }

    private Comment mapToEntity(CommentDto commentDto) {
        return Comment.builder()
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .body(commentDto.getBody())
                .build();
    }
}
