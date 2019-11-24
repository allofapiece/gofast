package com.pinwheel.gofast.entity.dto;

import com.pinwheel.gofast.service.validation.ValidSlug;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSlugDto {
    @NotNull
    @Size(min = 4, max = 32)
    @ValidSlug
    @Pattern(regexp = "[a-zA-Z0-9-]*", message = "{form.user-slug.slug.error.format}")
    private String slug;
}
