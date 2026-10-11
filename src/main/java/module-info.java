module com.university.current_generator_simulator {
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires jakarta.validation;
    requires jdk.jdi;
    requires jdk.jfr;
    requires java.desktop;
    requires org.slf4j;
    requires commons.math3;

//    requires com.fasterxml.jackson.databind;
//    requires com.fasterxml.jackson.datatype.jsr310;
//    requires com.fasterxml.jackson.datatype.jdk8;

//    opens com.university.current_generator_simulator.core.values to com.fasterxml.jackson.databind;
//    opens com.university.current_generator_simulator.core.entities to com.fasterxml.jackson.databind;

    exports com.university.current_generator_simulator.application;
}