package com.university.current_generator_simulator.adapters.desktop.services;

import com.university.current_generator_simulator.adapters.desktop.common.components.Typography;
import jakarta.validation.constraints.NotNull;

public interface TypographyFactory extends ComponentFactory<Typography> {

    @NotNull
    Typography createPrimaryTypography(@NotNull final String text);

}
