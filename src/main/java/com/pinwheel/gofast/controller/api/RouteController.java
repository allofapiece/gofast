package com.pinwheel.gofast.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pinwheel.gofast.entity.Point;
import com.pinwheel.gofast.entity.Route;
import com.pinwheel.gofast.entity.User;
import com.pinwheel.gofast.entity.Views;
import com.pinwheel.gofast.entity.dto.PointDto;
import com.pinwheel.gofast.repository.api.RouteRepository;
import com.pinwheel.gofast.service.PointService;
import com.pinwheel.gofast.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


@RepositoryRestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    private final RepositoryEntityLinks links;

    @GetMapping("search/pointId")
    @JsonView(Views.WithGeneral.class)
    public ResponseEntity<MappingJacksonValue> related(@RequestParam Long id, Authentication authentication, HttpServletRequest request) {
        User user = (User) authentication.getPrincipal();
        List<Route> routes = routeService.findByPointIdAndUserId(id, user.getId());

        Resources<Route> resources = new Resources<>(routes);

        resources.add(new Link(request.getRequestURL().toString()).withSelfRel());

        MappingJacksonValue wrapper = new MappingJacksonValue(resources);

        wrapper.setFilters(new SimpleFilterProvider()
                .addFilter("routeFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("id", "to", "from", "vehicles"))
        .addFilter("pointFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "address")));

        return ResponseEntity.ok(wrapper);
    }

    @GetMapping("search/userId")
    @JsonView(Views.WithGeneral.class)
    public ResponseEntity<MappingJacksonValue> userId(@RequestParam Long id, HttpServletRequest request) {
        List<Route> routes = routeService.findByUserId(id);

        Resources<Route> resources = new Resources<>(routes);

        resources.add(new Link(request.getRequestURL().toString()).withSelfRel());

        MappingJacksonValue wrapper = new MappingJacksonValue(resources);

        wrapper.setFilters(new SimpleFilterProvider()
                .addFilter("routeFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("id", "to", "from", "vehicles"))
        .addFilter("pointFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "address")));

        return ResponseEntity.ok(wrapper);
    }
}
