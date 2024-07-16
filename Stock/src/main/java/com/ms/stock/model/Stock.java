package com.ms.stock.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")
public class Stock {
    @Id
    private String id;
    private String zone;

    public String getId() {
        return id;
    }

    public String getZone() {
        return zone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}

