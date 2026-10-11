package com.university.current_generator_simulator.adapters.desktop.configurations;

import com.university.current_generator_simulator.adapters.desktop.feautures.components.GuideScreen;
import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.infrastructure.configurations.BeanConfiguration;

@Configuration(name = "guide-screen")
public final class GuideScreenConfiguration extends BeanConfiguration {

    public GuideScreenConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(GuideScreen.class)
                .setFactory(GuideScreen::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}