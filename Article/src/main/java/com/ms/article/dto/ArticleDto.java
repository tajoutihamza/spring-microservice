package com.ms.article.dto;

import lombok.Builder;

@Builder
public record ArticleDto(String name, long quantity, String stockZone) {
    }
