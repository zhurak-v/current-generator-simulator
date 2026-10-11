package com.university.current_generator_simulator.infrastructure.services;

import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.ConfigurationRegistrar;
import com.university.current_generator_simulator.core.ports.services.ClassScanner;
import com.university.current_generator_simulator.infrastructure.configurations.BeanConfiguration;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ConfigurationRegistrarImpl implements ConfigurationRegistrar {

    private static final AtomicBoolean INITIALIZED;

    static {
        INITIALIZED = new AtomicBoolean(false);
    }

    private ConfigurationRegistrarImpl() {
        throw new UnsupportedOperationException();
    }

    public static void register() {
        if (!INITIALIZED.compareAndSet(false, true)) {
            return;
        }

        final ClassScanner classScanner = ClassScanner.getClassScanner();

        classScanner.registerHandler(Configuration.class, clazz -> {
            if (BeanConfiguration.class.isAssignableFrom(clazz)) {
                try {
                    final Class<? extends BeanConfiguration> configurationClass =
                            clazz.asSubclass(BeanConfiguration.class);

                    final BeanConfiguration configuration = configurationClass
                                    .getDeclaredConstructor()
                                    .newInstance();

                    configuration.configure();

                } catch (final Exception ex) {
                    throw new RuntimeException("Failed to init bean: " + clazz.getName(), ex);
                }
            }
        });
    }
}