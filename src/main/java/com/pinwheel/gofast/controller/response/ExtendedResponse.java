package com.pinwheel.gofast.controller.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.pinwheel.gofast.entity.Views;
import com.pinwheel.gofast.service.notification.domain.Alert;
import lombok.*;

import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonView(Views.WithMeta.class)
public class ExtendedResponse<T> extends Response<T> {
    public ExtendedResponse(T _embedded) {
        super(_embedded);
    }

    @Builder
    public ExtendedResponse(T _embedded, List<Alert> alerts) {
        super(_embedded);
        this.alerts = alerts;
    }

    public ExtendedResponse(T _embedded, Alert alert) {
        this(_embedded, new LinkedList<>(List.of(alert)));
    }

    @Singular
    private List<Alert> alerts = new LinkedList<>();
}
