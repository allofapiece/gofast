package com.pinwheel.gofast.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name"})
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.WithId.class)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "vehicles")
    @RestResource(path = "routes", rel="routes")
    private Set<Route> routes = new HashSet<>();
}
