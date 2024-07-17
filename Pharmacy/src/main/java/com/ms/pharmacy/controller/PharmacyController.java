package com.ms.pharmacy.controller;

import com.ms.pharmacy.model.Pharmacy;
import com.ms.pharmacy.service.PharmacyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {


    private final PharmacyService pharmacyService;
    public PharmacyController(PharmacyService pharmacyService){
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public List<Pharmacy> getAllPharmacy() {
        return pharmacyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable String id) {
        return pharmacyService.findById(id)
                .map(pharmacy -> ResponseEntity.ok().body(pharmacy))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pharmacy createPharmacy(@RequestBody Pharmacy pharmacy) {
        return pharmacyService.save(pharmacy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pharmacy> updatePharmacy(@PathVariable String id, @RequestBody Pharmacy pharmacyDetails) {
        return pharmacyService.findById(id)
                .map(pharmacy -> {
                    pharmacy.setPharmacy(pharmacyDetails.getPharmacy());
                    Pharmacy updatedPharmacy = pharmacyService.save(pharmacy);
                    return ResponseEntity.ok().body(updatedPharmacy);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePharmacy(@PathVariable String id) {
        return pharmacyService.findById(id)
                .map(pharmacy -> {
                    pharmacyService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
