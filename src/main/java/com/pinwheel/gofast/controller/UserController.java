package com.pinwheel.gofast.controller;

import com.pinwheel.gofast.entity.User;
import com.pinwheel.gofast.entity.dto.UserChangePasswordDto;
import com.pinwheel.gofast.entity.dto.UserEditGeneralDto;
import com.pinwheel.gofast.entity.dto.UserSlugDto;
import com.pinwheel.gofast.repository.UserRepository;
import com.pinwheel.gofast.service.UserService;
import com.pinwheel.gofast.service.notification.NotificationService;
import com.pinwheel.gofast.service.notification.domain.Notification;
import com.pinwheel.gofast.service.notification.factory.FlushNotificationMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * User controller.
 *
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Controller
public class UserController {
    /**
     * Injection of {@link NotificationService} bean.
     */
    private final NotificationService notificationService;

    /**
     * Inject of {@link FlushNotificationMessageFactory} bean.
     */
    private final FlushNotificationMessageFactory flushNotificationMessageFactory;

    /**
     * Inject of {@link UserService} bean.
     */
    private final UserService userService;
    /**
     * Inject of {@link UserRepository} bean.
     */
    private final UserRepository userRepository;

    /**
     * Returns user settings page.
     *
     * @param model
     * @param user
     * @return user settings page template.
     */
    @GetMapping("/user/settings")
    @PreAuthorize("hasAuthority('USER')")
    public String siteSettings(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        model.addAttribute("userChangePasswordDto", new UserChangePasswordDto());
        model.addAttribute("userSlugDto", new UserSlugDto(user.getSlug()));

        return "user/settings/settings";
    }

    /**
     * Updates slug.
     *
     * @return redirect to settings in success case.
     */
    @PostMapping("/user/settings/slug")
    @PreAuthorize("hasAuthority('USER')")
    public String slug(
            @Valid UserSlugDto userSlugDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userChangePasswordDto", new UserChangePasswordDto());
            return "user/settings/settings";
        }

        user.setSlug(userSlugDto.getSlug());
        userRepository.save(user);

        model.addAttribute("userSlugDto", userSlugDto);

        notificationService.send(Notification.builder()
                .put(
                        "flush",
                        new User(),
                        flushNotificationMessageFactory.createSuccess(
                                redirectAttributes,
                                "user.slug.update.success")
                ).build());

        return "redirect:/user/settings";
    }

    /**
     * Updates password.
     *
     * @return redirect to settings in success case.
     */
    @PostMapping("/user/settings/password")
    @PreAuthorize("hasAuthority('USER')")
    public String password(
            @Valid UserChangePasswordDto userChangePasswordDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (bindingResult.hasErrors() || !userService.changePassword(user, userChangePasswordDto, bindingResult)) {
            model.addAttribute("userSlugDto", new UserSlugDto(user.getSlug()));
            model.addAttribute("tab", "security");

            return "user/settings/settings";
        }

        notificationService.send(Notification.builder()
                .put(
                        "flush",
                        new User(),
                        flushNotificationMessageFactory.createSuccess(
                                redirectAttributes,
                                "user.password.update.success")
                ).build());

        return "redirect:/user/settings";
    }
}
