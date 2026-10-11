package com.university.current_generator_simulator.infrastructure.configurations;


import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.IdGenerator;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.infrastructure.services.AutoIncrementIdGeneratorImpl;

@Configuration(name = "autoIncrement")
public final class IdGeneratorConfiguration extends BeanConfiguration {

    public IdGeneratorConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(IdGenerator.class)
                .setFactory(AutoIncrementIdGeneratorImpl::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}
