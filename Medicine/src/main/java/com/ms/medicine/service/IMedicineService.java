package com.ms.medicine.service;


import com.ms.medicine.dto.MedicineDTO;
import com.ms.medicine.dto.DTOMedicine;
import com.ms.medicine.model.Medicine;

import java.util.List;
import java.util.Optional;

public interface IMedicineService {
    List<Medicine> findAll();
    Optional<Medicine> findById(Long id);
    Medicine save(Medicine medicine);
    void deleteById(Long id);
    MedicineDTO getMedicineWithPharmacyInfo(Long id);
    DTOMedicine getMedicineWithPharmacyDto(Long id);

}
