package com.ms.article.dto;

import lombok.Builder;

@Builder
public record StockDto(String id, String zone) {
}
