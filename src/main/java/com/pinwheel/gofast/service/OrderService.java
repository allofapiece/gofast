package com.pinwheel.gofast.service;

import com.pinwheel.gofast.entity.Order;
import com.pinwheel.gofast.entity.Route;
import com.pinwheel.gofast.entity.dto.SuggestDto;

import java.util.List;

public interface OrderService {
    List<Order> findByCompanyId(Long id);

    List<Order> knapsack(Long id);
}
