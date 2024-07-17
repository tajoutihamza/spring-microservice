package com.ms.medicine.controller;

import com.ms.medicine.dto.MedicineDTO;
import com.ms.medicine.dto.DTOMedicine;
import com.ms.medicine.model.Medicine;
import com.ms.medicine.service.IMedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final IMedicineService medicineService;

    public MedicineController(IMedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public List<Medicine> getAllMedicine() {
        return medicineService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        return medicineService.findById(id)
                .map(medicine -> ResponseEntity.ok().body(medicine))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medicine createMedicine(@RequestBody Medicine medicine) {
        return medicineService.save(medicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicineDetails) {
        return medicineService.findById(id)
                .map(medicine -> {
                    medicine.setName(medicineDetails.getName());
                    medicine.setPrice(medicineDetails.getPrice());
                    medicine.setPharmacyId(medicineDetails.getPharmacyId());
                    Medicine updatedMedicine = medicineService.save(medicine);
                    return ResponseEntity.ok().body(updatedMedicine);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMedicine(@PathVariable Long id) {
        return medicineService.findById(id)
                .map(medicine -> {
                    medicineService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/with-pharmacy/{id}")
    public ResponseEntity<MedicineDTO> getMedicineWithPharmacyInfo(@PathVariable Long id) {
        MedicineDTO responseDTO = medicineService.getMedicineWithPharmacyInfo(id);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/pharmacy/{id}")
    public ResponseEntity<DTOMedicine> getMedicineWithPharmacyDto(@PathVariable Long id) {
        DTOMedicine responseDTO = medicineService.getMedicineWithPharmacyDto(id);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/pharmacy")
    public ResponseEntity<List<MedicineDTO>> getMedicineWithPharmacyDto() {

        return ResponseEntity.ok(medicineService.findAll().stream().map(medicine -> {
            return medicineService.getMedicineWithPharmacyInfo(medicine.getId());
        }).collect(Collectors.toList()));
    }
}
