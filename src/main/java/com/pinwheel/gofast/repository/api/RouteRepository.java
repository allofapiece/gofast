package com.pinwheel.gofast.repository.api;

import com.pinwheel.gofast.entity.Route;
import com.pinwheel.gofast.entity.RouteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "routes", path = "routes", excerptProjection = RouteProjection.class)
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByCompanyIdAndVehicles_IdIn(@Param("id") Long id, List<Long> vehicleIds);

    @RestResource(path = "userId", rel = "userId")
    List<Route> findByCompanyId(@Param("id") Long id);

    @RestResource(path = "pointId", rel = "related")
    @Query(value = "select r from Route r where r.company.id = :userId and (r.from.id = :id or r.to.id = :id)")
    List<Route> findByFromIdOrToIdAndCompanyId(@Param("id") Long id, @Param("userId") Long userId);
}
