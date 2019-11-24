package com.pinwheel.gofast.entity.dto;

import com.pinwheel.gofast.service.validation.PasswordMatches;
import com.pinwheel.gofast.service.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@PasswordMatches(affectedObject = "confirmedPassword")
public class UserChangePasswordDto {
    @NotBlank
    private String oldPassword;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank
    private String confirmedPassword;
}
