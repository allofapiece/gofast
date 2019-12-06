package com.pinwheel.gofast.repository.api;

import com.pinwheel.gofast.entity.CargoProjection;
import com.pinwheel.gofast.entity.Order;
import com.pinwheel.gofast.entity.OrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders", excerptProjection = OrderProjection.class)
public interface OrderRepository extends JpaRepository<Order, Long> {
}
