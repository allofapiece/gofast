package com.pinwheel.gofast.entity;

import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

/**
 * @version 1.0.0
 */
@Projection(name = "defaultCargo", types = { Cargo.class })
public interface CargoProjection {
    Long getId();
    String getName();
    BigDecimal getPrice();
}
