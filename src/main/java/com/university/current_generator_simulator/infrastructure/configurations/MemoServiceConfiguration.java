package com.university.current_generator_simulator.infrastructure.configurations;

import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.core.ports.services.MemoService;

@Configuration
public final class MemoServiceConfiguration extends BeanConfiguration {

    public MemoServiceConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(MemoService.class)
                .setFactory(MemoService::getMemoService)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}
