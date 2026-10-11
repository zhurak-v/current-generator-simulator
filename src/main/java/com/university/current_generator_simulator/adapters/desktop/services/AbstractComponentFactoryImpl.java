package com.university.current_generator_simulator.adapters.desktop.services;

import com.university.current_generator_simulator.adapters.desktop.common.components.Component;
import com.university.current_generator_simulator.core.ports.services.InjectionService;

public abstract class AbstractComponentFactoryImpl<T extends Component>
        implements ComponentFactory<T>
{

    protected final InjectionService injectionService;

    protected AbstractComponentFactoryImpl() {
        this.injectionService = InjectionService.getInjectionService();
    }

}
