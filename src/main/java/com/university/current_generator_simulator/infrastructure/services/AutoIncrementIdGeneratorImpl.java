package com.university.current_generator_simulator.infrastructure.services;

import com.university.current_generator_simulator.core.ports.services.IdGenerator;

import java.util.concurrent.atomic.AtomicLong;

public final class AutoIncrementIdGeneratorImpl implements IdGenerator {

    private final AtomicLong currentId;

    public AutoIncrementIdGeneratorImpl() {
        this.currentId = new AtomicLong(0);
    }

    @Override
    public long nextId() {
        return this.currentId.incrementAndGet();
    }

}
