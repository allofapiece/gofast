package com.pinwheel.gofast.service.validation;

import com.pinwheel.gofast.entity.User;
import com.pinwheel.gofast.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


@RequiredArgsConstructor
public class UniqueSlugValidator implements ConstraintValidator<ValidSlug, Object> {
    /**
     * Inject of {@link UserRepository} bean.
     */
    private final UserRepository userRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        User user = (User) value;
        String slug = user.getSlug();

        if (slug == null) {
            return true;
        }

        return !slugExists(slug, user.getId());
    }

    /**
     *
     */
    private boolean slugExists(String slug, Long id) {
        return id == null ? userRepository.existsBySlug(slug) : userRepository.existsBySlugAndIdNot(slug, id);
    }
}
