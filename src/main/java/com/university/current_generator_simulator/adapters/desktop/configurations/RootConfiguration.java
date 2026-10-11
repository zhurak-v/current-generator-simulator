package com.university.current_generator_simulator.adapters.desktop.configurations;

import com.university.current_generator_simulator.adapters.desktop.common.components.Root;
import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.infrastructure.configurations.BeanConfiguration;

@Configuration
public final class RootConfiguration extends BeanConfiguration {

    public RootConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(Root.class)
                .setFactory(Root::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}
