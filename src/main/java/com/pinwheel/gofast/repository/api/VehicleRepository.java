package com.pinwheel.gofast.repository.api;

import com.pinwheel.gofast.entity.Route;
import com.pinwheel.gofast.entity.VehicleProjection;
import com.pinwheel.gofast.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "vehicles", path = "vehicles", excerptProjection = VehicleProjection.class)
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
