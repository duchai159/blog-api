package com.spring.blog.springbootblogrestapi.service.impl;

import com.spring.blog.springbootblogrestapi.entity.Post;
import com.spring.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.spring.blog.springbootblogrestapi.payload.PostDto;
import com.spring.blog.springbootblogrestapi.payload.PostResponse;
import com.spring.blog.springbootblogrestapi.repository.PostRepository;
import com.spring.blog.springbootblogrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto) {
        // convert DTO to entity
        Post post = mapToEntity(postDto);
        // save
        Post newPost = postRepository.save(post);
        // convert entity to DTO
        return mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        // get content for page object
        List<Post> postList = posts.getContent();
        List<PostDto> content = postList.stream().map(this::mapToDTO).toList();
        return PostResponse.builder()
                .content(content)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast())
                .build();
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePostById(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return mapToDTO(postRepository.save(post));
    }

    @Override
//    @Transactional
    public void deletePostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
        postRepository.delete(post);
    }

    private PostDto mapToDTO(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
}
