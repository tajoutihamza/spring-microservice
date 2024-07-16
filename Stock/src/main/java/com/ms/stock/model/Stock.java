package com.ms.stock.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "stocks")
public class Stock {
    @Id
    private String id;
    private String zone;
}

