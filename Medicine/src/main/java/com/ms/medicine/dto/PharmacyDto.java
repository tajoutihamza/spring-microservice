package com.ms.medicine.dto;

import lombok.Builder;

@Builder
public record PharmacyDto(String id, String pharmacy) {
}
