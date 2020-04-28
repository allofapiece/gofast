package com.pinwheel.gofast.repository.api;

import com.pinwheel.gofast.entity.Order;
import com.pinwheel.gofast.entity.OrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders", excerptProjection = OrderProjection.class)
public interface OrderRepository extends JpaRepository<Order, Long> {

    @RestResource(path = "userId", rel = "userId")
    List<Order> findByUser_Id(@Param("id") Long id);

    @RestResource(path = "companyId", rel = "companyId")
    List<Order> findByFrom_Company_Id(@Param("id") Long id);

    @RestResource(path = "pointId", rel = "pointId")
    List<Order> findByFrom_Id(@Param("id") Long id);
}
