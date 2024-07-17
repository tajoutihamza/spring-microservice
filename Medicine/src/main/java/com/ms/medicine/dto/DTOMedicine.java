package com.ms.medicine.dto;

import lombok.Builder;

@Builder
public record DTOMedicine(String name, long price, PharmacyDto pharmacyDto){ }
