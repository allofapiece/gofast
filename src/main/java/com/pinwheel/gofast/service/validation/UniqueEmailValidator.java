package com.pinwheel.gofast.service.validation;

import com.pinwheel.gofast.entity.User;
import com.pinwheel.gofast.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Unique email validator. Validates email already existing.
 *
 * @version 1.0
 */
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        User user = (User) obj;

        return !(user.getId() == null
                ? userRepository.existsByEmail(user.getEmail())
                : userRepository.existsByEmailAndIdNot(user.getEmail(), user.getId()));
    }
}
