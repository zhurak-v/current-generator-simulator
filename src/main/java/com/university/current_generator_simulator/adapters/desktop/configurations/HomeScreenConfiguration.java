package com.university.current_generator_simulator.adapters.desktop.configurations;

import com.university.current_generator_simulator.adapters.desktop.feautures.components.HomeScreen;
import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.infrastructure.configurations.BeanConfiguration;

@Configuration(name = "home-screen")
public final class HomeScreenConfiguration extends BeanConfiguration {

    public HomeScreenConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(HomeScreen.class)
                .setFactory(HomeScreen::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}