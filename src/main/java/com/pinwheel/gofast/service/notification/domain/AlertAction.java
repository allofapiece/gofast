package com.pinwheel.gofast.service.notification.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.pinwheel.gofast.entity.Views;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonView(Views.WithMeta.class)
public class AlertAction {
    private String text;

    private String code;

    private String link;

    private FlushStatus type = FlushStatus.PRIMARY;

    public AlertAction(String text, String code, String link) {
        this.text = text;
        this.code = code;
        this.link = link;
    }

    public AlertAction(String text, String code, String link, FlushStatus type) {
        this(text, code, link);
        this.type = type;
    }
}
