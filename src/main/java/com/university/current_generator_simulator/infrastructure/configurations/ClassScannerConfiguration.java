package com.university.current_generator_simulator.infrastructure.configurations;

import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.ports.services.ClassScanner;
import com.university.current_generator_simulator.core.ports.services.InjectionService;

@Configuration
public final class ClassScannerConfiguration extends BeanConfiguration {

    public ClassScannerConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(ClassScanner.class)
                .setFactory(ClassScanner::getClassScanner)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}
