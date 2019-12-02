package com.pinwheel.gofast.entity;

import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

/**
 * @version 1.0.0
 */
@Projection(name = "defaultVehicle", types = { Vehicle.class })
public interface VehicleProjection {
    Long getId();
    String getName();
}
