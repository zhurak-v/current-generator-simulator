package com.university.current_generator_simulator.core.annotations;

import jakarta.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Configuration {
    String DEFAULT_NAME = "DEFAULT_BEAN_NAME";
    @NotNull String name() default DEFAULT_NAME;
}
