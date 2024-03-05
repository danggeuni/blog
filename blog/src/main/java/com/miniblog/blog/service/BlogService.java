package com.miniblog.blog.service;

import com.miniblog.blog.controller.dto.AddArticleRequest;
import com.miniblog.blog.controller.dto.UpdateArticleRequest;
import com.miniblog.blog.domain.entity.Article;
import com.miniblog.blog.domain.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found" + id));
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found" + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }

}
