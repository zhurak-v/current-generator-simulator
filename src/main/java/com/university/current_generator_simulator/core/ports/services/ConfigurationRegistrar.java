package com.university.current_generator_simulator.core.ports.services;

import com.university.current_generator_simulator.infrastructure.services.ConfigurationRegistrarImpl;

public interface ConfigurationRegistrar {

    static void register() {
        ConfigurationRegistrarImpl.register();
    }

}
