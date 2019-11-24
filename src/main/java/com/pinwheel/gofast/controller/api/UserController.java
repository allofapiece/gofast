package com.pinwheel.gofast.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.pinwheel.gofast.entity.User;
import com.pinwheel.gofast.entity.Views;
import com.pinwheel.gofast.exception.ResourceNotFoundException;
import com.pinwheel.gofast.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * User controller.
 *
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController("userApiController")
@RequestMapping("/api/users")
public class UserController {
    /**
     * Inject of {@link UserRepository} bean.
     */
    private final UserRepository userRepository;

    @GetMapping("/current")
    @PreAuthorize("hasAuthority('USER')")
    @JsonView(Views.WithGeneral.class)
    public User current(@AuthenticationPrincipal User user) {
        return user;
    }

    /**
     * Returns user profile.
     *
     * @return user profile template path.
     */
    @GetMapping("/{slug:^(?![\\d])[a-z\\-]+$}")
    @JsonView(Views.WithGeneral.class)
    public User getBySlug(@PathVariable(value = "slug") String slug) {
        return Optional.ofNullable(userRepository.findBySlug(slug)).orElseThrow(ResourceNotFoundException::new);
    }
}
