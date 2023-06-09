package com.spring.blog.springbootblogrestapi.controller;

import com.spring.blog.springbootblogrestapi.payload.PostDto;
import com.spring.blog.springbootblogrestapi.payload.PostResponse;
import com.spring.blog.springbootblogrestapi.service.PostService;
import com.spring.blog.springbootblogrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private PostService postService;
    @PreAuthorize("hasAnyRole('ROLE_USER'.'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    @PreAuthorize("hasAnyRole('ROLE_USER'.'ROLE_ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable Long postId,@Valid @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePostById(postId, postDto));
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable Long postId){
        postService.deletePostById(postId);
        return ResponseEntity.ok("Post entity deleted successfully.");
    }
}
