package com.ms.article.service;


import com.ms.article.dto.ArticleDto;
import com.ms.article.dto.DtoArticle;
import com.ms.article.dto.StockDto;
import com.ms.article.feign.IStockClient;
import com.ms.article.model.Article;
import com.ms.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements IArticleService {

    private final ArticleRepository articleRepository;
    private final IStockClient IStockClient;
    private final StockService stockService;

    public ArticleServiceImpl(ArticleRepository articleRepository, IStockClient IStockClient, StockService stockService) {
        this.articleRepository = articleRepository;
        this.IStockClient = IStockClient;
        this.stockService = stockService;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public ArticleDto getArticleWithStockInfo(Long id) {
        Optional<Article> articleOpt = articleRepository.findById(id);
        if (articleOpt.isPresent()) {
            Article article = articleOpt.get();
            StockDto stockDTO = stockService.getStockById(article.getStockId());
            return new ArticleDto(article.getName(),article.getQuantity(),stockDTO.zone());
        } else {
            return null; // or throw an exception
        }
    }
    @Override
    public DtoArticle getArticleWithStockDto(Long id) {
        Optional<Article> articleOpt = articleRepository.findById(id);
        if (articleOpt.isPresent()) {
            Article article = articleOpt.get();
            StockDto stockDTO = stockService.getStockByIdRest(article.getStockId());
            return new DtoArticle(article.getName(),article.getQuantity(),stockDTO);

        } else {
            return null; // or throw an exception
        }
    }
}

