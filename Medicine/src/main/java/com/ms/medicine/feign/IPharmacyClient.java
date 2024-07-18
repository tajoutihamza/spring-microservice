package com.ms.medicine.feign;

import com.ms.medicine.dto.PharmacyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PHARMACY-SERVICE/pharmacy")
public interface IPharmacyClient {
    @GetMapping("/{id}")
    PharmacyDto getPharmacyById(@PathVariable("id") String id);
}