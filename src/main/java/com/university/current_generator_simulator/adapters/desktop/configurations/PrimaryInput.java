package com.university.current_generator_simulator.adapters.desktop.configurations;

import com.university.current_generator_simulator.adapters.desktop.common.components.Input;

public final class PrimaryInput extends Input {

    public PrimaryInput() {
        super();
        this.applyStyles();
    }

    private void applyStyles() {
        this.addInlineStyles("""
            -fx-min-width: 60px;
            -fx-pref-width: 60px;
            -fx-max-width: 60px;
        """);
    }

}
