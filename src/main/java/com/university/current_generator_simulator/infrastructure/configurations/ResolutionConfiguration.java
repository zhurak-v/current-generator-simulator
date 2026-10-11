package com.university.current_generator_simulator.infrastructure.configurations;

import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.core.values.Resolution;

@Configuration
public final class ResolutionConfiguration extends BeanConfiguration {

    public ResolutionConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(Resolution.class)
                .setFactory(() -> Resolution.R_1440x810)
                .setScope(InjectionService.Scope.SINGLETON);
    }
}
