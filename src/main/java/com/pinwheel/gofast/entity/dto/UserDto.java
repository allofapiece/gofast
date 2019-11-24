package com.pinwheel.gofast.entity.dto;

import com.pinwheel.gofast.service.validation.PasswordMatches;
import com.pinwheel.gofast.service.validation.UniqueEmail;
import com.pinwheel.gofast.service.validation.ValidEmail;
import com.pinwheel.gofast.service.validation.ValidPassword;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@PasswordMatches(affectedObject = "confirmedPassword")
public class UserDto {
    @ValidEmail()
    private String email;

    /**
     * Company name.
     */
    @Size(min = 2, max = 15)
    private String name;

    @Size(min = 2, max = 15)
    private String firstName;

    @Size(min = 2, max = 15)
    private String lastName;

    @NotNull
    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 4, max = 18)
    private String confirmedPassword;

    private boolean isCompany;
}
