package com.pinwheel.gofast.service;

import com.pinwheel.gofast.entity.Route;
import com.pinwheel.gofast.repository.api.PointRepository;
import com.pinwheel.gofast.repository.api.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SimpleRouteService implements RouteService {
    private final RouteRepository routeRepository;

    @Override
    public List<Route> findByPointIdAndUserId(Long id, Long userId) {
        return routeRepository.findByFromIdOrToIdAndCompanyId(id, userId);
    }

    @Override
    public List<Route> findByUserId(Long userId) {
        return routeRepository.findByCompanyId(userId);
    }
}
