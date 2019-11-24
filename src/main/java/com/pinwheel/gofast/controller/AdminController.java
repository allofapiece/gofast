package com.pinwheel.gofast.controller;

import com.pinwheel.gofast.service.notification.NotificationService;
import com.pinwheel.gofast.service.notification.factory.FlushNotificationMessageFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Admin controller. Contains actions allowed for administrators only.
 *
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    /**
     * Injection of {@link ModelMapper} bean.
     */
    private final ModelMapper modelMapper;

    /**
     * Injection of {@link NotificationService} bean.
     */
    private final NotificationService notificationService;

    /**
     * Inject of {@link FlushNotificationMessageFactory} bean.
     */
    private final FlushNotificationMessageFactory flushNotificationMessageFactory;


    /**
     * Returns admin page.
     *
     * @return admin page path.
     */
    @GetMapping
    public String admin() {
        return "admin/main";
    }
}
