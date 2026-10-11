package com.university.current_generator_simulator.core.ports.services;

import com.university.current_generator_simulator.adapters.desktop.common.components.Screen;
import com.university.current_generator_simulator.infrastructure.services.RouterImpl;
import jakarta.validation.constraints.NotNull;

public interface Router {

    @NotNull
    Screen navigate(@NotNull final String screenName);

    void registerScreen(@NotNull final String screenName,
                        @NotNull final Screen screen);

    void setMainScreen(@NotNull final Screen screen);

}
