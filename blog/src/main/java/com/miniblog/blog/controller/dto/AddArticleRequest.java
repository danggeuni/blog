package com.miniblog.blog.controller.dto;

import com.miniblog.blog.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity() {
        return new Article(title, content);
    }
}
