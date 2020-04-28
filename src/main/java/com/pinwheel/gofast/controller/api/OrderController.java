package com.pinwheel.gofast.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pinwheel.gofast.entity.Order;
import com.pinwheel.gofast.entity.Point;
import com.pinwheel.gofast.entity.Views;
import com.pinwheel.gofast.entity.dto.PointDto;
import com.pinwheel.gofast.service.OrderService;
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
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final RepositoryEntityLinks links;

    @GetMapping("/search/companyId")
    @JsonView(Views.WithGeneral.class)
    public ResponseEntity<MappingJacksonValue> search(@RequestParam("id") Long id,
                                                      HttpServletRequest request) {
        Resources<Order> resources = new Resources<>(orderService.findByCompanyId(id));

        resources.add(new Link(request.getRequestURL().toString()).withSelfRel());

        MappingJacksonValue wrapper = new MappingJacksonValue(resources);

        wrapper.setFilters(new SimpleFilterProvider()
                .addFilter("pointFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "address"))
                .addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "from", "vehicle", "user", "cargo", "weight"))
        );

        return ResponseEntity.ok(wrapper);
    }

    @GetMapping("/calculate")
    @JsonView(Views.WithGeneral.class)
    public ResponseEntity<MappingJacksonValue> knapsack(@RequestParam("id") Long id,
                                                      HttpServletRequest request) {
        Resources<Order> resources = new Resources<>(orderService.knapsack(id));

        resources.add(new Link(request.getRequestURL().toString()).withSelfRel());

        MappingJacksonValue wrapper = new MappingJacksonValue(resources);

        wrapper.setFilters(new SimpleFilterProvider()
                .addFilter("pointFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "address"))
                .addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "from", "user", "cargo", "weight"))
        );

        return ResponseEntity.ok(wrapper);
    }
}
