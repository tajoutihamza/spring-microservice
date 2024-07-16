package com.ms.article.dto;

import lombok.Builder;

@Builder
public record DtoArticle (String name, long quantity, StockDto stockDto){ }
