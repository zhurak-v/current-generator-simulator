# Current Generator Simulator

A custom desktop application for simulating DC electrical circuits, built with **Java** and **JavaFX**. Users assemble a circuit from a battery, rheostats, an inductor, switches, ammeters and a voltmeter, tweak parameters in real time, and instantly see the readings of the measuring devices.

Under the hood the project combines numerical linear algebra, a physics-based circuit model, and a hand-written application framework: a dependency injection container, a screen router and a reusable UI component toolkit.

## Highlights

- **Physics-driven core.** Circuit behavior is computed with Modified Nodal Analysis: the circuit is turned into a system of linear equations and solved numerically.
- **Real-time interaction.** Changing a battery voltage, a resistance or a switch state triggers an immediate recalculation of every ammeter and voltmeter.
- **Custom UI toolkit on JavaFX.** A set of composable components with flexbox-style layouts, percentage-based sizing, absolute positioning and inline styling.
- **Resolution-aware interface.** Screens take their sizing from a configurable resolution value, and layouts are built from relative bindings instead of hard-coded pixel sizes.
- **Own IoC container.** Annotation-driven bean registration with classpath scanning, singleton and prototype scopes, and named beans.
- **Screen routing.** Declarative `@Route` screens with automatic registration and a fallback screen for unknown routes.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17+ | core language (records, pattern matching, switch expressions) |
| JavaFX | desktop user interface |
| Apache Commons Math 3 | linear algebra and matrix decompositions |
| SLF4J | logging |

## Circuit Simulation

### Supported Elements

| Element | Description |
|---|---|
| Battery | ideal voltage source with adjustable voltage |
| Wire | near-zero-resistance connection |
| Switch | opens or closes a branch |
| Rheostat | adjustable resistor |
| Inductor | adjustable-resistance element |
| Ammeter | measures current through a branch |
| Voltmeter | measures potential difference between two points |

### How It Works

1. The circuit is modeled as a graph of elements connected through pins.
2. Pins that are electrically joined are merged into physical nodes.
3. Passive elements contribute conductance stamps to the system matrix, and voltage sources add extra equations for their currents.
4. A reference node is grounded and a small `Gmin` conductance is added for numerical stability.
5. The system is solved with Singular Value Decomposition, which stays robust on degenerate or poorly conditioned circuits.
6. Node potentials are converted into ammeter and voltmeter readings.

Every parameter change goes through `CircuitService`, which recalculates the circuit automatically:

```java
circuitService.changeBatteryVoltage(0L, 12.0d);
circuitService.changeRheostatResistance(9L, 25.0d);
circuitService.toggleSwitch(4L);
```

### Building a Circuit

Circuits are assembled programmatically by adding elements and connecting their pins:

```java
final CircuitEntity circuit = new CircuitEntity(1L);
final CircuitConnectionServiceImpl connections = new CircuitConnectionServiceImpl();

final BatteryElementEntity battery = new BatteryElementEntity(0L, 0);
final WireElementEntity wire = new WireElementEntity(1L);

connections.addElement(circuit, battery);
connections.addElement(circuit, wire);

connections.connect(battery, 0, wire, 0);
```

The default circuit contains a main loop (battery, switch, ammeter, rheostat, inductor) and a parallel branch (ammeter, switch, rheostat) with a voltmeter connected across it.

## User Interface

The UI is built on a custom component toolkit that wraps JavaFX nodes behind a small, consistent API.

| Component | Description |
|---|---|
| `Box` | flexbox-like container with horizontal or vertical direction, main and cross axis alignment, margins, paddings and percentage-based sizing |
| `Button` | clickable `Box` with a pointer cursor |
| `Canvas` | fixed-size surface with absolute and relative positioning |
| `Input` | text field with change listeners |
| `Media` | image component with aspect-ratio helpers |
| `Typography` | wrapping, centered text |
| `Screen` | base class for application screens |
| `Root` | hosts the active screen and keeps it sized to the window |

```java
final Box row = new Box(Box.Direction.X_DIRECTION);
row.setAlign(Box.Align.SPACE_BETWEEN, Box.Align.CENTER);
row.setPadding(8.0d, 16.0d);
row.bindWidthPercent(parent, 50.0d);
row.addChildren(title, button);
```

### Screens

| Screen | Purpose |
|---|---|
| Home | main simulation screen |
| Guide | user guide |
| Theory | theoretical background |
| About Us | information about the authors |
| Not Found | fallback for unknown routes |

Screens are declared with `@Route` and registered automatically. Navigation is a single call:

```java
router.navigate("home-screen");
```

## Dependency Injection

A lightweight IoC container manages the application's services and UI components.

- Annotation-based configuration with `@Configuration`
- `SINGLETON` and `PROTOTYPE` scopes
- Named beans with validation of naming conflicts
- Automatic discovery through classpath scanning, including JAR archives

```java
@Configuration(name = "autoIncrement")
public final class IdGeneratorConfiguration extends BeanConfiguration {

    public IdGeneratorConfiguration() {
        super();
    }

    @Override
    protected void defineBean(final BeanDefinitionBuilder builder) {
        builder
                .setType(IdGenerator.class)
                .setFactory(AutoIncrementIdGeneratorImpl::new)
                .setScope(InjectionService.Scope.SINGLETON);
    }

}
```

```java
final IdGenerator ids = InjectionService.getInjectionService()
        .getBean(IdGenerator.class, "autoIncrement");
```

## Getting Started

### Requirements

- JDK 17 or newer
- JavaFX (`javafx-controls`)
- Apache Commons Math 3
- SLF4J with a logging backend

### Startup Sequence

```java
ConfigurationRegistrarImpl.register();
ClassScanner.getClassScanner().scan();

ScreenRegistrarImpl.register();
ClassScanner.getClassScanner().scan();
```

The first scan registers all beans, and the second one registers screens in the router. After that, the `Root` bean can be placed into a JavaFX `Scene`.