package com.ms.medicine.dto;

import lombok.Builder;

@Builder
public record MedicineDTO(String name, long price, String pharmacy) {
    }
