package com.university.current_generator_simulator.application;

import com.university.current_generator_simulator.core.entities.*;

public final class Application {

    private Application() {
        throw new UnsupportedOperationException();
    }

    private static final String BASE_PACKAGE;

    static {
        BASE_PACKAGE = "com.university.current_generator_simulator";
    }

    public static void main(final String[] args) {
        final ApplicationRunner applicationRunner = new ApplicationRunner(BASE_PACKAGE, args);
        applicationRunner.run();

    }
}
