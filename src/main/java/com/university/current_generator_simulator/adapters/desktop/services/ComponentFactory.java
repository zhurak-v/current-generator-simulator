package com.university.current_generator_simulator.adapters.desktop.services;

import com.university.current_generator_simulator.adapters.desktop.common.components.Component;
import jakarta.validation.constraints.NotNull;

public interface ComponentFactory<T extends Component> {

    @NotNull
    T create();

}
