package com.ms.pharmacy.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "pharmacy")
public class Pharmacy {
    @Id
    private String id;
    private String pharmacy;
}

