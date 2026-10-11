package com.university.current_generator_simulator.core.entities;

import com.university.current_generator_simulator.core.values.Pin;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public final class InductorElementEntity
        extends AbstractCircuitElementEntity
        implements Inductable, Resistable {

    private double resistance;
    private final double inductance;

    public InductorElementEntity(
            final Long id,
            final double resistance,
            final double inductance
    ) {
        super(
                Objects.requireNonNull(id),
                AbstractCircuitElementEntity.CircuitElementType.INDUCTOR
        );

        this.resistance = resistance;
        this.inductance = inductance;
    }

    public InductorElementEntity(
            final Long id,
            final OffsetDateTime createdAt,
            final OffsetDateTime updatedAt,
            final double resistance,
            final double inductance
    ) {
        super(
                Objects.requireNonNull(id),
                Objects.requireNonNull(createdAt),
                updatedAt,
                CircuitElementType.INDUCTOR
        );
        this.resistance = resistance;
        this.inductance = inductance;
    }

    @Override
    public double getResistance() {
        return this.resistance;
    }

    public void setResistance(final double resistance) {
        this.resistance = resistance;
    }

    @Override
    public double getInductance() {
        return this.inductance;
    }

    @Override
    protected List<PinDefinition> definePins() {
        return List.of(
                new PinDefinition(Pin.PinType.PASSIVE),
                new PinDefinition(Pin.PinType.PASSIVE)
        );
    }
}