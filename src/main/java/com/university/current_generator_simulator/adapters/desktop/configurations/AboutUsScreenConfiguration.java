package com.university.current_generator_simulator.adapters.desktop.configurations;

import com.university.current_generator_simulator.adapters.desktop.feautures.components.AboutUsScreen;
import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.infrastructure.configurations.BeanConfiguration;

@Configuration(name = "about-us-screen")
public final class AboutUsScreenConfiguration extends BeanConfiguration {

    public AboutUsScreenConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(AboutUsScreen.class)
                .setFactory(AboutUsScreen::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}