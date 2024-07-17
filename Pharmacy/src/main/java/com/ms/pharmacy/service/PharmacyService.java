package com.ms.pharmacy.service;

import com.ms.pharmacy.model.Pharmacy;

import java.util.List;
import java.util.Optional;

public interface PharmacyService {
    List<Pharmacy> findAll();
    Optional<Pharmacy> findById(String id);
    Pharmacy save(Pharmacy pharmacy);
    void deleteById(String id);
}
