package com.ms.article.controller;

import com.ms.article.dto.ArticleDto;
import com.ms.article.dto.DtoArticle;
import com.ms.article.model.Article;
import com.ms.article.service.IArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleService.findById(id)
                .map(article -> ResponseEntity.ok().body(article))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.save(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
        return articleService.findById(id)
                .map(article -> {
                    article.setName(articleDetails.getName());
                    article.setQuantity(articleDetails.getQuantity());
                    article.setStockId(articleDetails.getStockId());
                    Article updatedArticle = articleService.save(article);
                    return ResponseEntity.ok().body(updatedArticle);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteArticle(@PathVariable Long id) {
        return articleService.findById(id)
                .map(article -> {
                    articleService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}/with-stock")
    public ResponseEntity<ArticleDto> getArticleWithStockInfo(@PathVariable Long id) {
        ArticleDto responseDTO = articleService.getArticleWithStockInfo(id);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}/stock")
    public ResponseEntity<DtoArticle> getArticleWithStockDto(@PathVariable Long id) {
        DtoArticle responseDTO = articleService.getArticleWithStockDto(id);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
