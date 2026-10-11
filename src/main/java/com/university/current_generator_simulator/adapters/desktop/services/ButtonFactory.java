package com.university.current_generator_simulator.adapters.desktop.services;

import com.university.current_generator_simulator.adapters.desktop.common.components.Button;
import jakarta.validation.constraints.NotNull;

public interface ButtonFactory extends ComponentFactory<Button> {

    @NotNull
    Button createPrimaryButton();

}
