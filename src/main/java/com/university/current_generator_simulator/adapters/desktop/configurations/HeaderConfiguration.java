package com.university.current_generator_simulator.adapters.desktop.configurations;

import com.university.current_generator_simulator.adapters.desktop.feautures.components.Header;
import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.infrastructure.configurations.BeanConfiguration;

@Configuration
public final class HeaderConfiguration extends BeanConfiguration {

    public HeaderConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(Header.class)
                .setFactory(Header::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}
