package com.spring.blog.springbootblogrestapi.payload;

import com.spring.blog.springbootblogrestapi.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min = 2)
    private String title;
    @NotEmpty
    @Size(min = 10)
    private String description;
    @NotEmpty
    private String content;
    private Set<Comment> comments;
}
