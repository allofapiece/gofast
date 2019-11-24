package com.pinwheel.gofast.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;

/**
 * @version 1.0.0
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class Client extends User {
    @JsonView(Views.WithGeneral.class)
    private String firstName;

    @JsonView(Views.WithGeneral.class)
    private String lastName;

    @Override
    public String getFullName() {
        String name = "";

        if (!StringUtils.isEmpty(this.firstName)) {
            name = this.firstName;
        }

        if (!StringUtils.isEmpty(this.lastName)) {
            name = StringUtils.isEmpty(name) ? this.lastName : name + " " + this.lastName;
        }

        return name;
    }
}
