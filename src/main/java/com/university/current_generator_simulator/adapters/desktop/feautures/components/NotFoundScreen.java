package com.university.current_generator_simulator.adapters.desktop.feautures.components;

import com.university.current_generator_simulator.adapters.desktop.annotations.Route;
import com.university.current_generator_simulator.adapters.desktop.common.components.Box;
import com.university.current_generator_simulator.adapters.desktop.common.components.Screen;
import com.university.current_generator_simulator.adapters.desktop.common.components.Typography;

import java.net.URL;
import java.util.Objects;

@Route(name = "not-found-screen")
public final class NotFoundScreen extends Screen {

    public NotFoundScreen() {
        super();
        this.addNotFoundChildren();
    }

    public void addNotFoundChildren() {
        final Box box = new Box();
        final Typography title = new Typography("Resource is not found");

        box.addChildren(title);

        this.addChildren(box);
    }

}
