package com.pinwheel.gofast.service;

import com.pinwheel.gofast.entity.Point;
import com.pinwheel.gofast.entity.Route;
import com.pinwheel.gofast.entity.dto.SuggestDto;
import com.pinwheel.gofast.entity.dto.SuggestEdge;
import com.pinwheel.gofast.repository.api.PointRepository;
import com.pinwheel.gofast.repository.api.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SimpleRouteService implements RouteService {
    private final RouteRepository routeRepository;
    private final PointRepository pointRepository;

    @Override
    public List<Route> findByPointIdAndUserId(Long id, Long userId) {
        return routeRepository.findByFromIdOrToIdAndCompanyId(id, userId);
    }

    @Override
    public List<Route> findByUserId(Long userId) {
        return routeRepository.findByCompanyId(userId);
    }

    @Override
    public List<List<SuggestDto>> suggest(Long fromId, Long toId) {
        Point fromPoint = pointRepository.findById(fromId).get();
        Point toPoint = pointRepository.findById(toId).get();

        List<Route> routes = routeRepository.findByCompanyId(fromPoint.getCompany().getId());
        List<Point> points = pointRepository.findByCompanyId(fromPoint.getCompany().getId());

        DefaultDirectedGraph<Point, SuggestEdge> graph
                = new DefaultDirectedGraph<>(SuggestEdge.class);

        points.forEach(graph::addVertex);
        routes.forEach(route -> {
            graph.addEdge(route.getFrom(), route.getTo());
            graph.addEdge(route.getTo(), route.getFrom());
        });

        AllDirectedPaths dp = new AllDirectedPaths(graph);

        List<GraphPath<Point, DefaultEdge>> paths = dp.getAllPaths(fromPoint, toPoint, true, null);

        return paths.stream().map(path -> path.getEdgeList()
                .stream()
                .map(edge -> {
                    var e = (SuggestEdge) edge;
                    return new SuggestDto((Point) e.getSource(), (Point) e.getTarget());
                })
                .collect(Collectors.toList())
        ).collect(Collectors.toList());
    }
}
