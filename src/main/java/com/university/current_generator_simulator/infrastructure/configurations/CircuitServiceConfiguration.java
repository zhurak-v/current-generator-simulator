package com.university.current_generator_simulator.infrastructure.configurations;

import com.university.current_generator_simulator.core.annotations.Configuration;
import com.university.current_generator_simulator.core.entities.*;
import com.university.current_generator_simulator.core.ports.services.CircuitService;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.infrastructure.services.CircuitConnectionServiceImpl;
import com.university.current_generator_simulator.infrastructure.services.CircuitServiceImpl;

import java.util.stream.Stream;

@Configuration
public final class CircuitServiceConfiguration extends BeanConfiguration {

    public CircuitServiceConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        final CircuitEntity circuit = new CircuitEntity(1L);
        final CircuitConnectionServiceImpl connectionService = new CircuitConnectionServiceImpl();

        final CircuitElements elements = this.createElements();
        this.addElementsToCircuit(circuit, elements);
        this.connectElements(connectionService, elements);

        final CircuitService circuitService = new CircuitServiceImpl(circuit);

        builder
                .setType(CircuitService.class)
                .setFactory(() -> circuitService)
                .setScope(InjectionService.Scope.SINGLETON);
    }

    private CircuitElements createElements() {
        return new CircuitElements(
                new BatteryElementEntity(0L, 0),
                new WireElementEntity(1L),
                new WireElementEntity(2L),
                new WireElementEntity(3L),
                new SwitchElementEntity(4L),
                new WireElementEntity(5L),
                new WireElementEntity(6L),
                new AmmeterElementEntity(7L),
                new WireElementEntity(8L),
                new RheostatElementEntity(9L, 11),
                new WireElementEntity(10L),
                new WireElementEntity(11L),
                new InductorElementEntity(12L, 11, 0),
                new WireElementEntity(13L),
                new WireElementEntity(14L),
                new WireElementEntity(15L),
                new WireElementEntity(16L),
                new VoltmeterElementEntity(100L),
                new WireElementEntity(17L),
                new AmmeterElementEntity(18L),
                new WireElementEntity(19L),
                new WireElementEntity(20L),
                new SwitchElementEntity(21L),
                new WireElementEntity(22L),
                new RheostatElementEntity(23L, 10),
                new WireElementEntity(24L),
                new WireElementEntity(25L),
                new WireElementEntity(26L)
        );
    }

    private void addElementsToCircuit(
            final CircuitEntity circuit,
            final CircuitElements e
    ) {
        Stream.of(
                e.battery, e.wire1, e.wire2, e.wire3, e.switch1, e.wire4,
                e.wire5, e.ammeter1, e.wire6, e.rheostat1, e.wire7, e.wire8,
                e.inductor, e.wire9, e.wire10, e.wire11, e.wire12, e.voltmeter,
                e.wire13, e.ammeter2, e.wire14, e.wire15, e.switch2, e.wire16,
                e.rheostat2, e.wire17, e.wire18, e.wire19
        ).forEach(circuit::addElement);
    }

    private void connectElements(
            final CircuitConnectionServiceImpl connectionService,
            final CircuitElements e
    ) {
        connectionService.connect(e.battery, 0, e.wire1, 0);
        connectionService.connect(e.wire1, 1, e.wire2, 0);
        connectionService.connect(e.wire2, 1, e.wire3, 0);
        connectionService.connect(e.wire3, 1, e.switch1, 0);
        connectionService.connect(e.switch1, 1, e.wire4, 0);
        connectionService.connect(e.wire4, 1, e.wire5, 0);
        connectionService.connect(e.wire5, 1, e.ammeter1, 0);
        connectionService.connect(e.ammeter1, 1, e.wire6, 0);
        connectionService.connect(e.wire6, 1, e.rheostat1, 0);
        connectionService.connect(e.rheostat1, 1, e.wire7, 0);
        connectionService.connect(e.wire7, 1, e.wire8, 0);
        connectionService.connect(e.wire8, 1, e.inductor, 0);
        connectionService.connect(e.inductor, 1, e.wire9, 0);
        connectionService.connect(e.wire9, 1, e.wire10, 0);
        connectionService.connect(e.wire10, 1, e.wire11, 0);
        connectionService.connect(e.wire11, 1, e.battery, 1);

        connectionService.connect(e.wire2, 1, e.wire12, 0);
        connectionService.connect(e.wire12, 1, e.wire13, 0);

        connectionService.connect(e.wire12, 1, e.voltmeter, 0);
        connectionService.connect(e.wire18, 1, e.voltmeter, 1);

        connectionService.connect(e.wire13, 1, e.ammeter2, 0);
        connectionService.connect(e.ammeter2, 1, e.wire14, 0);
        connectionService.connect(e.wire14, 1, e.wire15, 0);
        connectionService.connect(e.wire15, 1, e.switch2, 0);
        connectionService.connect(e.switch2, 1, e.wire16, 0);
        connectionService.connect(e.wire16, 1, e.rheostat2, 0);
        connectionService.connect(e.rheostat2, 1, e.wire17, 0);
        connectionService.connect(e.wire17, 1, e.wire18, 0);
        connectionService.connect(e.wire18, 1, e.wire19, 0);

        connectionService.connect(e.wire19, 1, e.wire10, 0);
    }

    private record CircuitElements(
            BatteryElementEntity battery,
            WireElementEntity wire1,
            WireElementEntity wire2,
            WireElementEntity wire3,
            SwitchElementEntity switch1,
            WireElementEntity wire4,
            WireElementEntity wire5,
            AmmeterElementEntity ammeter1,
            WireElementEntity wire6,
            RheostatElementEntity rheostat1,
            WireElementEntity wire7,
            WireElementEntity wire8,
            InductorElementEntity inductor,
            WireElementEntity wire9,
            WireElementEntity wire10,
            WireElementEntity wire11,
            WireElementEntity wire12,
            VoltmeterElementEntity voltmeter,
            WireElementEntity wire13,
            AmmeterElementEntity ammeter2,
            WireElementEntity wire14,
            WireElementEntity wire15,
            SwitchElementEntity switch2,
            WireElementEntity wire16,
            RheostatElementEntity rheostat2,
            WireElementEntity wire17,
            WireElementEntity wire18,
            WireElementEntity wire19
    ) {}
}