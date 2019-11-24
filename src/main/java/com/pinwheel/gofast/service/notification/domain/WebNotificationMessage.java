package com.pinwheel.gofast.service.notification.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Listratenko Stanislav
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WebNotificationMessage extends NotificationMessage {
    @Builder
    public WebNotificationMessage(String message) {
        super(message);
    }
}
