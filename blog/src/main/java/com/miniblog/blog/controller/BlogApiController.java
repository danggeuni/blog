package com.miniblog.blog.controller;

import com.miniblog.blog.controller.dto.AddArticleRequest;
import com.miniblog.blog.controller.dto.ArticleResponse;
import com.miniblog.blog.controller.dto.UpdateArticleRequest;
import com.miniblog.blog.domain.entity.Article;
import com.miniblog.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BlogApiController {

    private final BlogService blogService;

    @Autowired
    public BlogApiController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findArticles() {
        List<ArticleResponse> articles = blogService.findAll().stream().map(ArticleResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok().body(updateArticle);
    }
}
