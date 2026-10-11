package com.university.current_generator_simulator.infrastructure.configurations;

import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.core.ports.services.Router;
import com.university.current_generator_simulator.infrastructure.services.RouterImpl;

@Configuration
public final class RouterConfiguration extends BeanConfiguration {

    public RouterConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(Router.class)
                .setFactory(RouterImpl::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}
