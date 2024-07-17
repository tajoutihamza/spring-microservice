package com.ms.pharmacy.service;

import com.ms.pharmacy.model.Pharmacy;
import com.ms.pharmacy.repository.PharmacyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository){this.pharmacyRepository = pharmacyRepository;}
    @Override
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Optional<Pharmacy> findById(String id) {
        return pharmacyRepository.findById(id);
    }

    @Override
    public Pharmacy save(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public void deleteById(String id) {
        pharmacyRepository.deleteById(id);
    }
}
