package com.pinwheel.gofast.entity;

import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

/**
 * @version 1.0.0
 */
@Projection(name = "defaultOrder", types = { Order.class })
public interface OrderProjection {
    Long getId();
    Cargo getCargo();
    User getUser();
    Point getFrom();
    Vehicle getVehicle();
    float getWeight();
}
