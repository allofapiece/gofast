package com.pinwheel.gofast.controller;

import com.pinwheel.gofast.entity.User;
import com.pinwheel.gofast.entity.dto.UserDto;
import com.pinwheel.gofast.service.UserService;
import com.pinwheel.gofast.service.notification.NotificationService;
import com.pinwheel.gofast.service.notification.domain.Alert;
import com.pinwheel.gofast.service.notification.domain.AlertAction;
import com.pinwheel.gofast.service.notification.domain.FlushStatus;
import com.pinwheel.gofast.service.notification.domain.Notification;
import com.pinwheel.gofast.service.notification.factory.FlushNotificationMessageFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Describes logic of authentication, registration and etc. Generalizes logic of application security.
 *
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Controller
public class AuthController {
    /**
     * Injection of the user service.
     */
    private final UserService userService;

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
     * Injection of {@link MessageSource} bean.
     */
    private final MessageSource messageSource;

    /**
     * Returns template of login page.
     *
     * @return registration page path.
     */
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    /**
     * Returns template of registration page.
     *
     * @return registration page path.
     */
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/signup";
    }

    /**
     * Delegates verification code to the user service. Checks verification code in database and whether it is not
     * expired. If verification code is valid set user status as active.
     *
     * @param token Verification code from email for checking user.
     * @return Login page.
     */
    @GetMapping("/activate/{token}")
    public String activate(@PathVariable String token, Locale locale, RedirectAttributes redirectAttributes) {
        boolean isActivated = this.userService.activateUser(token);

        redirectAttributes.addAttribute("token", token);

        if (isActivated) {
            redirectAttributes.addFlashAttribute("alert",
                    new Alert(messageSource.getMessage(
                            "auth.register.activation.verified", null, locale),
                            "auth.register.activation.verified",
                            "alert.signup.verified",
                            FlushStatus.SUCCESS)
            );
        } else {
            redirectAttributes.addFlashAttribute("alert",
                    new Alert(messageSource.getMessage("auth.register.activation.expired", null, locale),
                            "auth.register.activation.expired",
                            "alert.signup.expired",
                            FlushStatus.WARNING,
                            List.of(new AlertAction(
                                    messageSource.getMessage(
                                            "auth.register.activation.expired.action",
                                            null,
                                            "Send again.",
                                            locale
                                    ),
                                    "auth.register.activation.expired.action",
                                    "/api/action/reactivate",
                                    FlushStatus.ERROR
                            ))
                    )
            );
        }

        return "redirect:/signin";
    }

    /**
     * Creates new verification token for user. Old token will be set as expired.
     *
     * @param oldToken           old verification token for identifying user.
     * @param redirectAttributes redirect attributes for sending response to user.
     * @return redirect to login page.
     */
    @GetMapping("/reactivate/{oldToken}")
    public String reactivate(
            @PathVariable String oldToken,
            RedirectAttributes redirectAttributes
    ) {
        userService.resendActivation(oldToken);

        notificationService.send(Notification.builder()
                .put(
                        "flush",
                        new User(),
                        flushNotificationMessageFactory.create("verifyYourEmail", redirectAttributes))
                .build());

        return "redirect:/login";
    }
}
