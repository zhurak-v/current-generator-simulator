package com.university.current_generator_simulator.adapters.desktop.configurations;

import com.university.current_generator_simulator.adapters.desktop.feautures.components.TheoryScreen;
import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.infrastructure.configurations.BeanConfiguration;

@Configuration(name = "theory-screen")
public final class TheoryScreenConfiguration extends BeanConfiguration {

    public TheoryScreenConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(TheoryScreen.class)
                .setFactory(TheoryScreen::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}