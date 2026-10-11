package com.university.current_generator_simulator.adapters.desktop.services;

import com.university.current_generator_simulator.adapters.desktop.common.components.Typography;

public final class TypographyFactoryImpl
        extends AbstractComponentFactoryImpl<Typography>
        implements TypographyFactory
{

    private static final String DEFAULT_TEXT = "Typography";

    public TypographyFactoryImpl() {
        super();
    }

    @Override
    public Typography createPrimaryTypography(final String text) {
        final Typography typography = new Typography(text);
        typography.addInlineStyles("""
                -fx-alignment: center;
                -fx-font-size: 16px;
                -fx-font-family: "Roboto";
        """
        );
        return typography;
    }

    @Override
    public Typography create() {
        return this.createPrimaryTypography(DEFAULT_TEXT);
    }

}
