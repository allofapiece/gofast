package com.pinwheel.gofast.service;

import com.pinwheel.gofast.entity.Point;
import com.pinwheel.gofast.entity.Route;
import com.pinwheel.gofast.entity.dto.PointDto;

import java.util.List;

public interface RouteService {
    List<Route> findByPointIdAndUserId(Long id, Long userId);
    List<Route> findByUserId(Long userId);
}
