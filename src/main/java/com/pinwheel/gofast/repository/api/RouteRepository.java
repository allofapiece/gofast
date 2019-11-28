package com.pinwheel.gofast.repository.api;

import com.pinwheel.gofast.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "routes", path = "routes")
public interface RouteRepository extends JpaRepository<Route, Long> {
}
