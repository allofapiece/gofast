package com.pinwheel.gofast.repository.api;

import com.pinwheel.gofast.entity.Point;
import com.pinwheel.gofast.entity.PointProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "points", path = "points", excerptProjection = PointProjection.class)
public interface PointRepository extends JpaRepository<Point, Long> {
    @RestResource(path = "userId", rel = "userId")
    List<Point> findByCompanyId(@Param("id") Long id);

    List<Point> findByAddressContainsIgnoreCase(String search);
}
