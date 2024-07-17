package com.ms.medicine.service;


import com.ms.medicine.dto.MedicineDTO;
import com.ms.medicine.dto.DTOMedicine;
import com.ms.medicine.dto.PharmacyDto;
import com.ms.medicine.feign.IPharmacyClient;
import com.ms.medicine.model.Medicine;
import com.ms.medicine.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements IMedicineService {

    private final MedicineRepository medicineRepository;
    private final IPharmacyClient IPharmacyClient;
    private final PharmacyService pharmacyService;

    public MedicineServiceImpl(MedicineRepository medicineRepository, IPharmacyClient IPharmacyClient, PharmacyService pharmacyService) {
        this.medicineRepository = medicineRepository;
        this.IPharmacyClient = IPharmacyClient;
        this.pharmacyService = pharmacyService;
    }

    @Override
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    @Override
    public Optional<Medicine> findById(Long id) {
        return medicineRepository.findById(id);
    }

    @Override
    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public void deleteById(Long id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public MedicineDTO getMedicineWithPharmacyInfo(Long id) {
        Optional<Medicine> articleOpt = medicineRepository.findById(id);
        if (articleOpt.isPresent()) {
            Medicine medicine = articleOpt.get();
            PharmacyDto pharmacyDTO = pharmacyService.getPharmacyById(medicine.getPharmacyId());
            return new MedicineDTO(medicine.getName(), medicine.getPrice(), pharmacyDTO.pharmacy());
        } else {
            return null; // or throw an exception
        }
    }
    @Override
    public DTOMedicine getMedicineWithPharmacyDto(Long id) {
        Optional<Medicine> articleOpt = medicineRepository.findById(id);
        if (articleOpt.isPresent()) {
            Medicine medicine = articleOpt.get();
            PharmacyDto pharmacyDTO = pharmacyService.getPharmacyByIdRest(medicine.getPharmacyId());
            return new DTOMedicine(medicine.getName(), medicine.getPrice(), pharmacyDTO);

        } else {
            return null;
        }
    }
}

