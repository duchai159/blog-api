package com.spring.blog.springbootblogrestapi.service;

import com.spring.blog.springbootblogrestapi.payload.PostDto;
import com.spring.blog.springbootblogrestapi.payload.PostResponse;


public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long postId);

    PostDto updatePostById(Long postId, PostDto postDto);

    void deletePostById(Long postId);
}
