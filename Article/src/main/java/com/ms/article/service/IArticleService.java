package com.ms.article.service;


import com.ms.article.dto.ArticleDto;
import com.ms.article.dto.DtoArticle;
import com.ms.article.model.Article;

import java.util.List;
import java.util.Optional;

public interface IArticleService {
    List<Article> findAll();
    Optional<Article> findById(Long id);
    Article save(Article article);
    void deleteById(Long id);
    ArticleDto getArticleWithStockInfo(Long id);
    DtoArticle getArticleWithStockDto(Long id);

}
