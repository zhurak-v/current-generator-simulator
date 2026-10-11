package com.university.current_generator_simulator.adapters.desktop.configurations;

import com.university.current_generator_simulator.adapters.desktop.feautures.components.NotFoundScreen;
import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.infrastructure.configurations.BeanConfiguration;

@Configuration(name = "not-found-screen")
public final class NotFoundScreenConfiguration extends BeanConfiguration {

    public NotFoundScreenConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(NotFoundScreen.class)
                .setFactory(NotFoundScreen::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}