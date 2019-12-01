package com.pinwheel.gofast.entity;

import org.springframework.data.rest.core.config.Projection;

/**
 * @version 1.0.0
 */
@Projection(name = "defaultPoint", types = { Point.class })
public interface PointProjection {
    Long getId();
    String getAddress();
}
