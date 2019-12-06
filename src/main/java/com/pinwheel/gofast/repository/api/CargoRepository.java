package com.pinwheel.gofast.repository.api;

import com.pinwheel.gofast.entity.Cargo;
import com.pinwheel.gofast.entity.CargoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cargos", path = "cargos", excerptProjection = CargoProjection.class)
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
