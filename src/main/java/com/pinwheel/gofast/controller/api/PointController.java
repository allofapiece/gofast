package com.pinwheel.gofast.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pinwheel.gofast.entity.Point;
import com.pinwheel.gofast.entity.Views;
import com.pinwheel.gofast.entity.dto.PointDto;
import com.pinwheel.gofast.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RepositoryRestController
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    private final RepositoryEntityLinks links;

    @PostMapping
    @JsonView(Views.WithGeneral.class)
    public ResponseEntity<MappingJacksonValue> create(@RequestBody PointDto pointDto, HttpServletRequest request) {
        Resource<Point> resource = new Resource<>(pointService.create(pointDto));

        resource.add(new Link(request.getRequestURL().toString()).withSelfRel());
        resource.add(links.linkToSingleResource(resource.getContent()).withRel("point"));

        MappingJacksonValue wrapper = new MappingJacksonValue(resource);

        wrapper.setFilters(new SimpleFilterProvider()
                .addFilter("pointFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("id", "address")));

        return ResponseEntity.ok(wrapper);
    }

    @GetMapping("/search/addressLike")
    @JsonView(Views.WithGeneral.class)
    public ResponseEntity<MappingJacksonValue> search(@RequestParam("search") String search,
                                                      @RequestParam("from") Long from,
                                                      HttpServletRequest request) {
        Resources<Point> resources = new Resources<>(pointService.search(search, from));

        resources.add(new Link(request.getRequestURL().toString()).withSelfRel());

        MappingJacksonValue wrapper = new MappingJacksonValue(resources);

        wrapper.setFilters(new SimpleFilterProvider()
                .addFilter("pointFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("id", "address")));

        return ResponseEntity.ok(wrapper);
    }
}
