package com.university.current_generator_simulator.core.ports.services;

import com.university.current_generator_simulator.infrastructure.services.MemoServiceImpl;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.util.function.Supplier;

public interface MemoService {

    @NotNull
    static MemoService getMemoService() {
        return MemoServiceImpl.getMemoService();
    }

    @NotNull
    <T> T memoize(
            @NotNull final String key,
            @NotNull final Supplier<T> supplier
    );

    @NotNull
    <T> T memoize(
            @NotNull final String key,
            @NotNull final Duration ttl,
            @NotNull final Supplier<T> supplier
    );

}
