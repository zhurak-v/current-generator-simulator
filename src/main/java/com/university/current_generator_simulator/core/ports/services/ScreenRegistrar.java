package com.university.current_generator_simulator.core.ports.services;

import com.university.current_generator_simulator.infrastructure.services.ScreenRegistrarImpl;

public interface ScreenRegistrar {

    static void register() {
        ScreenRegistrarImpl.register();
    }

}
