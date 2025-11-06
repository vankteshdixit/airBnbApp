package com.vanktesh.project.airBnbApp.strategy;

import com.vanktesh.project.airBnbApp.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);

}
