package com.pinwheel.gofast.entity;

import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

/**
 * @version 1.0.0
 */
@Projection(name = "defaultRoute", types = { Route.class })
public interface RouteProjection {
    Long getId();
    Point getFrom();
    Point getTo();
    Set<Vehicle> getVehicles();
}
