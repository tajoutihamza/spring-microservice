package com.ms.pharmacy.repository;

import com.ms.pharmacy.model.Pharmacy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PharmacyRepository extends MongoRepository<Pharmacy, String> {
}
