package com.university.current_generator_simulator.infrastructure.services;

import com.university.current_generator_simulator.adapters.desktop.annotations.Route;
import com.university.current_generator_simulator.adapters.desktop.common.components.Screen;
import com.university.current_generator_simulator.core.ports.services.ClassScanner;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.core.ports.services.Router;

import java.util.concurrent.atomic.AtomicBoolean;

public class ScreenRegistrarImpl {

    private static final AtomicBoolean INITIALIZED;

    static {
        INITIALIZED = new AtomicBoolean(false);
    }

    private ScreenRegistrarImpl() {
        throw new UnsupportedOperationException();
    }

    public static void register() {
        if (!INITIALIZED.compareAndSet(false, true)) {
            return;
        }

        final ClassScanner classScanner = ClassScanner.getClassScanner();
        final InjectionService injectionService = InjectionServiceImpl.getInjectionService();
        final Router router = injectionService.getBean(Router.class);

        classScanner.registerHandler(Route.class, clazz -> {
            if (Screen.class.isAssignableFrom(clazz)) {
                try {
                    final Route annotation = clazz.getAnnotation(Route.class);
                    final String name = annotation.name();

                    router.registerScreen(
                            name,
                            injectionService.getBean(clazz, name)
                    );

                } catch (final Exception ex) {
                    throw new RuntimeException("Failed to init bean: " + clazz.getName(), ex);
                }
            }
        });
    }

}
