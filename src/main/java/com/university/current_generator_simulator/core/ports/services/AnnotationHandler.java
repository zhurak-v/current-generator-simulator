package com.university.current_generator_simulator.core.ports.services;

import jakarta.validation.constraints.NotNull;

@FunctionalInterface
public interface AnnotationHandler {

    void handle(@NotNull final Class<?> clazz);

}
