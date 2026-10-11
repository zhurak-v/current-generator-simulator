package com.university.current_generator_simulator.core.entities;

public interface Inductable {

    double ZERO_INDUCTANCE = 0.0d;

    double getInductance();

    default boolean isZeroInductance() {
        return this.getInductance() == ZERO_INDUCTANCE;
    }

}
