package com.pinwheel.gofast.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.pinwheel.gofast.entity.Point;
import com.pinwheel.gofast.entity.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuggestDto {
    @JsonView(Views.WithGeneral.class)
    private Point from;

    @JsonView(Views.WithGeneral.class)
    private Point to;
}
