package com.ms.medicine.service;

import com.ms.medicine.dto.PharmacyDto;
import com.ms.medicine.feign.IPharmacyClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PharmacyService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IPharmacyClient pharmacyClient;



    private static final String PHARMACY_SERVICE_URL = "http://localhost:8080/pharmacy/";

    @CircuitBreaker(name = "pharmacyServiceCircuitBreaker", fallbackMethod = "getDefaultPharmacy")
    public PharmacyDto getPharmacyByIdRest(String id) {
        return restTemplate.getForObject(PHARMACY_SERVICE_URL + id, PharmacyDto.class);
    }
    @CircuitBreaker(name = "pharmacyCircuitBreaker", fallbackMethod = "getDefaultPharmacy")
    public PharmacyDto getPharmacyById(String id) {
        return pharmacyClient.getPharmacyById(id);
    }
    public PharmacyDto getDefaultPharmacy(String id, Throwable throwable) {
        return new PharmacyDto(id,"Pharmacy is not Available");

    }
}
