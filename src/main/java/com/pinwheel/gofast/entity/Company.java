package com.pinwheel.gofast.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @version 1.0.0
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class Company extends User {
    @JsonView(Views.WithGeneral.class)
    private String name;

    @Override
    public String getFullName() {
        return getName();
    }
}
