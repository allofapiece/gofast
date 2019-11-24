package com.pinwheel.gofast.service.validation;

import com.pinwheel.gofast.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@RequiredArgsConstructor
public class SlugValidator implements ConstraintValidator<ValidSlug, String> {
    /**
     * Inject of {@link UserRepository} bean.
     */
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return !slugExists(value);
    }

    private boolean slugExists(String slug) {
        return userRepository.existsBySlug(slug);
    }
}
