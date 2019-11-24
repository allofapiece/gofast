package com.pinwheel.gofast.service.notification;

import com.pinwheel.gofast.service.notification.exception.IllegalNotificationStateException;
import com.pinwheel.gofast.service.notification.notifier.Notifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Simple notifier resolver. Implementation of the {@link NotifierResolver} interface.
 *
 * @author Listratenko Stanislav
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class SimpleNotifierResolver implements NotifierResolver {
    /**
     * Injection of {@link NotifiersHolder} bean.
     */
    private final NotifiersHolder notifiersHolder;

    /**
     * {@inheritDoc}
     * <p>
     * Uses {@link NotifiersHolder} for determining necessary notifier by notifier name. Throws
     * {@link IllegalNotificationStateException} if notifier cannot be resolved.
     *
     * @throws IllegalNotificationStateException if notifier cannot be resolved.
     */
    @Override
    public Notifier resolve(String notifierName) {
        Map<String, Notifier> notifiers = notifiersHolder.getNotifiers();

        if (!notifiers.containsKey(notifierName)) {
            throw new IllegalNotificationStateException("Cannot resolve notifier. Illegal notifier name.");
        }

        return notifiers.get(notifierName);
    }
}
