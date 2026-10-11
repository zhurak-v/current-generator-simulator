package com.university.current_generator_simulator.adapters.desktop.feautures.components;

import com.university.current_generator_simulator.adapters.desktop.annotations.Route;
import com.university.current_generator_simulator.adapters.desktop.common.components.*;
import com.university.current_generator_simulator.adapters.desktop.configurations.PrimaryInput;
import com.university.current_generator_simulator.core.entities.Voltable;
import com.university.current_generator_simulator.core.ports.services.CircuitService;
import com.university.current_generator_simulator.core.ports.services.InjectionService;
import com.university.current_generator_simulator.core.values.Resolution;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Route(name = "home-screen")
public final class HomeScreen extends Screen {

    public static final List<ExperimentData> experimentDataList = new ArrayList<>();

    public static final class ExperimentData {
        private final SimpleIntegerProperty id;
        private final SimpleDoubleProperty voltmeter;
        private final SimpleDoubleProperty ammeter1;
        private final SimpleDoubleProperty ammeter2;
        private final SimpleDoubleProperty rheostat1;
        private final SimpleDoubleProperty rheostat2;
        private final SimpleDoubleProperty inductorResistance; // Оновлено: нове поле для опору котушки

        public ExperimentData(final int id, final double voltmeter, final double ammeter1, final double ammeter2, final double rheostat1, final double rheostat2, final double inductorResistance) {
            this.id = new SimpleIntegerProperty(id);
            this.voltmeter = new SimpleDoubleProperty(voltmeter);
            this.ammeter1 = new SimpleDoubleProperty(ammeter1);
            this.ammeter2 = new SimpleDoubleProperty(ammeter2);
            this.rheostat1 = new SimpleDoubleProperty(rheostat1);
            this.rheostat2 = new SimpleDoubleProperty(rheostat2);
            this.inductorResistance = new SimpleDoubleProperty(inductorResistance); // Оновлено
        }

        public SimpleIntegerProperty idProperty() { return this.id; }
        public SimpleDoubleProperty voltmeterProperty() { return this.voltmeter; }
        public SimpleDoubleProperty ammeter1Property() { return this.ammeter1; }
        public SimpleDoubleProperty ammeter2Property() { return this.ammeter2; }
        public SimpleDoubleProperty rheostat1Property() { return this.rheostat1; }
        public SimpleDoubleProperty rheostat2Property() { return this.rheostat2; }
        public SimpleDoubleProperty inductorResistanceProperty() { return this.inductorResistance; } // Оновлено: геттер для властивості
    }

    public static final class MainSection extends Box {

        private static final class CircuitKey extends Box {

            private final Media openMedia;
            private final Media closeMedia;
            private boolean isOpen;
            private Consumer<Boolean> onStateChanged;

            public CircuitKey(final String openPath, final String closePath, final double w, final double h) {
                super();
                this.isOpen = true;
                this.openMedia = new Media(openPath);
                this.closeMedia = new Media(closePath);

                this.openMedia.setStaticSize(w, h);
                this.closeMedia.setStaticSize(w, h);

                this.setDirection(Direction.Y_DIRECTION);
                this.setAlign(Align.CENTER, Align.CENTER);
                this.setWidth(w);
                this.setHeight(h);
                this.addInlineStyles("-fx-cursor: hand;");

                this.onClick((event, component) -> this.toggle());
                this.updateMedia();
            }

            public void setOnStateChanged(final Consumer<Boolean> onStateChanged) {
                this.onStateChanged = onStateChanged;
            }

            public void toggle() {
                this.setState(!this.isOpen);
            }

            public void setState(final boolean open) {
                if (this.isOpen != open) {
                    this.isOpen = open;
                    this.updateMedia();
                    if (this.onStateChanged != null) {
                        this.onStateChanged.accept(open);
                    }
                }
            }

            public void triggerStateChanged() {
                if (this.onStateChanged != null) {
                    this.onStateChanged.accept(this.isOpen);
                }
            }

            private void updateMedia() {
                this.removeChildren(this.openMedia, this.closeMedia);
                this.addChildren(this.isOpen ? this.openMedia : this.closeMedia);
            }
        }

        private static final class Typer extends Box {
            private final Input input;
            private final Typography error;
            private final Typography unit;
            private Consumer<Double> onValid;
            private Consumer<Exception> onError;

            public Typer(final double initialValue, final String unitName) {
                super();
                this.input = new PrimaryInput();
                this.error = new PrimaryTopography("");
                this.unit = new PrimaryTopography(Objects.requireNonNull(unitName));

                this.setWidth(100);
                this.setDirection(Direction.Y_DIRECTION);
                this.setAlign(Align.CENTER, Align.CENTER);
                this.error.addInlineStyles("-fx-font-size: 12px;");
                this.unit.addInlineStyles("-fx-font-size: 12px;");

                this.input.setText(String.valueOf(initialValue));
                this.input.onTarget(this::handleInput);

                final Box wrapper = new Box(Direction.X_DIRECTION);
                wrapper.setAlign(Align.SPACE_BETWEEN, Align.CENTER);
                wrapper.addChildren(this.input, this.unit);
                this.addChildren(wrapper, this.error);
            }

            public void onValid(final Consumer<Double> onValid) {
                this.onValid = onValid;
            }

            public void onError(final Consumer<Exception> onError) {
                this.onError = onError;
            }

            public double getValue() {
                try {
                    return Double.parseDouble(this.input.getText());
                } catch (final NumberFormatException e) {
                    return 0.0;
                }
            }

            public void setValueSilent(final double value) {
                this.input.setText(String.valueOf(value));
            }

            private void handleInput(final String currentValue, final Input inputComponent) {
                try {
                    final double parsedValue = Double.parseDouble(currentValue);
                    if (parsedValue < 0) {
                        throw new IllegalArgumentException("Опір не може бути від'ємним");
                    }
                    if (this.onValid != null) {
                        this.onValid.accept(parsedValue);
                    }
                    this.clearError();
                } catch (final NumberFormatException ex) {
                    this.showError(new IllegalArgumentException("Невалідне число"));
                } catch (final Exception ex) {
                    this.showError(ex);
                }
            }

            private void clearError() {
                this.error.setText("");
                this.input.removeInlineStyles("-fx-border-color: red;");
            }

            private void showError(final Exception ex) {
                this.input.addInlineStyles("-fx-border-color: red;");
                this.error.setText(ex.getMessage());
                if (this.onError != null) {
                    this.onError.accept(ex);
                }
            }
        }

        private static final class Indicator extends Box {
            private final Typography valueLabel;
            private final String unit;

            public Indicator(final double initialValue, final String unit) {
                super();
                this.unit = Objects.requireNonNull(unit);
                this.valueLabel = new PrimaryTopography("");
                this.updateText(initialValue);
                this.addChildren(this.valueLabel);
            }

            public void updateValue(final double value) {
                if (Platform.isFxApplicationThread()) {
                    this.updateText(value);
                } else {
                    Platform.runLater(() -> this.updateText(value));
                }
            }

            private void updateText(final double value) {
                final double rounded = Math.round(value * 10.0) / 10.0;
                this.valueLabel.setText(rounded + " " + this.unit);
            }
        }

        private final Canvas canvas;
        private final TableView<ExperimentData> tableView;
        private final ObservableList<ExperimentData> tableRows;
        private final CircuitService circuitService;
        private final CircuitKey circuitKeySA1;
        private final CircuitKey circuitKeySA2;
        private final CircuitKey circuitKeyAC1;
        private final CircuitKey circuitKeyBC2;
        private final CircuitKey circuitKeyCC3;
        private final Typer rRheostat1Typer;
        private final Typer rRheostat2Typer;
        private final Typer inductorTyper;
        private final Indicator voltmeterIndicator;
        private final Indicator ammeter1Indicator;
        private final Indicator ammeter2Indicator;
        private final Typer batteryTyper;
        private double backupBatteryVoltage;
        private boolean isShownOnce;
        private double cachedHeaderHeight;
        private boolean isTableMode;

        public MainSection() {
            super();
            this.canvas = new Canvas(518, 602);
            this.tableView = new TableView<>();
            this.tableRows = FXCollections.observableArrayList();
            this.backupBatteryVoltage = 220;
            this.isShownOnce = false;
            this.cachedHeaderHeight = 0.0;
            this.isTableMode = false;

            this.circuitService = InjectionService.getInjectionService().getBean(CircuitService.class);

            this.circuitKeySA1 = new CircuitKey("/images/SA1A2-open-transparent.png", "/images/SA1A2-close-transparent.png", 33.0, 17.0);
            this.circuitKeySA2 = new CircuitKey("/images/SA1A2-open-transparent.png", "/images/SA1A2-close-transparent.png", 33.0, 17.0);

            this.circuitKeyAC1 = this.createGroupKey();
            this.circuitKeyBC2 = this.createGroupKey();
            this.circuitKeyCC3 = this.createGroupKey();

            final double initRheostat1 = this.circuitService.findRheostatById(9L).getResistance();
            final double initRheostat2 = this.circuitService.findRheostatById(23L).getResistance();
            final double initInductor = this.circuitService.findInductorById(12L).getResistance();

            this.rRheostat1Typer = new Typer(initRheostat1, "Ом");
            this.rRheostat2Typer = new Typer(initRheostat2, "Ом");
            this.inductorTyper = new Typer(initInductor, "Ом");

            final double initialVoltage = this.circuitService.findVoltmeterById(100L).getVoltage();
            final double initialAmperage1 = this.circuitService.findAmmeterById(7L).getCurrent();
            final double initialAmperage2 = this.circuitService.findAmmeterById(18L).getCurrent();

            this.voltmeterIndicator = new Indicator(initialVoltage, "В");
            this.ammeter1Indicator = new Indicator(initialAmperage1, "А");
            this.ammeter2Indicator = new Indicator(initialAmperage2, "А");

            this.batteryTyper = new Typer(220, "В");

            this.initBindings();
            this.initTable();
            this.applyStyles();
            this.render();

            this.circuitKeyAC1.triggerStateChanged();
        }

        private CircuitKey createGroupKey() {
            final CircuitKey key = new CircuitKey("/images/AC1BC2CC3-open-transparent.png", "/images/AC1BC2CC3-close-transparent.png", 36.0, 21.0);
            key.setOnStateChanged(isOpen -> {
                this.circuitKeyAC1.setState(isOpen);
                this.circuitKeyBC2.setState(isOpen);
                this.circuitKeyCC3.setState(isOpen);

                if (isOpen) {
                    final double currentVal = this.batteryTyper.getValue();
                    if (currentVal != 0.0) {
                        this.backupBatteryVoltage = currentVal;
                    }
                    this.circuitService.changeBatteryVoltage(0L, Voltable.ZERO_VOLTAGE);
                    this.batteryTyper.setValueSilent(0.0);
                } else {
                    this.circuitService.changeBatteryVoltage(0L, this.backupBatteryVoltage);
                    this.batteryTyper.setValueSilent(this.backupBatteryVoltage);
                }
                this.refreshIndicators();
            });
            return key;
        }

        private void initBindings() {
            this.circuitKeySA1.setOnStateChanged(isOpen -> {
                if (isOpen) {
                    this.circuitService.openSwitch(4L);
                } else {
                    this.circuitService.closeSwitch(4L);
                }
                this.refreshIndicators();
            });

            this.circuitKeySA2.setOnStateChanged(isOpen -> {
                if (isOpen) {
                    this.circuitService.openSwitch(21L);
                } else {
                    this.circuitService.closeSwitch(21L);
                }
                this.refreshIndicators();
            });

            this.rRheostat1Typer.onValid(value -> {
                this.circuitService.changeRheostatResistance(9L, value);
                this.refreshIndicators();
            });

            this.rRheostat2Typer.onValid(value -> {
                this.circuitService.changeRheostatResistance(23L, value);
                this.refreshIndicators();
            });

            this.inductorTyper.onValid(value -> {
                this.circuitService.changeInductorResistance(12L, value);
                this.refreshIndicators();
            });

            this.batteryTyper.onValid(value -> {
                if (!this.circuitKeyAC1.isOpen) {
                    this.circuitService.changeBatteryVoltage(0L, value);
                    this.backupBatteryVoltage = value;
                } else {
                    if (value != 0.0) {
                        this.backupBatteryVoltage = value;
                    }
                }
                this.refreshIndicators();
            });

            this.onClick((event, component) -> {
                if (!(event.getTarget() instanceof TextField)) {
                    this.getNode().requestFocus();
                }
            });
        }

        private void initTable() {
            // Оновлено: Збільшено ширину таблиці з 518 до 620, щоб вмістити нову колонку
            this.tableView.setPrefSize(620, 602);
            this.tableView.setMaxSize(620, 602);

            final TableColumn<ExperimentData, Integer> colId = new TableColumn<>("№ п/п");
            colId.setCellValueFactory(d -> d.getValue().idProperty().asObject());
            colId.setPrefWidth(50);

            final TableColumn<ExperimentData, Double> colV = new TableColumn<>("Напруга PV1, В");
            colV.setCellValueFactory(d -> d.getValue().voltmeterProperty().asObject());
            colV.setPrefWidth(100);

            final TableColumn<ExperimentData, Double> colA1 = new TableColumn<>("Струм PA1, А");
            colA1.setCellValueFactory(d -> d.getValue().ammeter1Property().asObject());
            colA1.setPrefWidth(100);

            final TableColumn<ExperimentData, Double> colA2 = new TableColumn<>("Струм PA2, А");
            colA2.setCellValueFactory(d -> d.getValue().ammeter2Property().asObject());
            colA2.setPrefWidth(100);

            final TableColumn<ExperimentData, Double> colRh1 = new TableColumn<>("Реостат Rр, Ом");
            colRh1.setCellValueFactory(d -> d.getValue().rheostat1Property().asObject());
            colRh1.setPrefWidth(100);

            final TableColumn<ExperimentData, Double> colRh2 = new TableColumn<>("Реостат Rнав, Ом");
            colRh2.setCellValueFactory(d -> d.getValue().rheostat2Property().asObject());
            colRh2.setPrefWidth(110);

            final TableColumn<ExperimentData, Double> colInd = new TableColumn<>("Котушка L, Ом");
            colInd.setCellValueFactory(d -> d.getValue().inductorResistanceProperty().asObject());
            colInd.setPrefWidth(100);

            this.tableView.getColumns().addAll(colId, colV, colA1, colA2, colRh1, colRh2, colInd);
            this.tableView.setItems(this.tableRows);
        }

        public void saveCurrentStateToTable() {
            final double rawV = this.circuitService.findVoltmeterById(100L).getVoltage();
            final double rawA1 = this.circuitService.findAmmeterById(7L).getCurrent();
            final double rawA2 = this.circuitService.findAmmeterById(18L).getCurrent();

            final double v = Math.round(rawV * 10.0) / 10.0;
            final double a1 = Math.round(rawA1 * 10.0) / 10.0;
            final double a2 = Math.round(rawA2 * 10.0) / 10.0;

            final double rh1 = this.rRheostat1Typer.getValue();
            final double rh2 = this.rRheostat2Typer.getValue();
            final double indRes = this.inductorTyper.getValue();

            final int nextId = this.tableRows.size() + 1;
            final ExperimentData data = new ExperimentData(nextId, v, a1, a2, rh1, rh2, indRes);

            this.tableRows.add(data);
            HomeScreen.experimentDataList.add(data);
        }

        public boolean toggleViewMode() {
            this.isTableMode = !this.isTableMode;

            this.tableView.setVisible(this.isTableMode);
            this.tableView.setManaged(this.isTableMode);

            this.canvas.getNode().setVisible(!this.isTableMode);
            this.canvas.getNode().setManaged(!this.isTableMode);

            if (!this.isTableMode) {
                Platform.runLater(() -> this.getNode().requestFocus());
            }

            return this.isTableMode;
        }

        private void refreshIndicators() {
            if (this.circuitService != null) {
                if (this.voltmeterIndicator != null) {
                    this.voltmeterIndicator.updateValue(this.circuitService.findVoltmeterById(100L).getVoltage());
                }
                if (this.ammeter1Indicator != null) {
                    this.ammeter1Indicator.updateValue(this.circuitService.findAmmeterById(7L).getCurrent());
                }
                if (this.ammeter2Indicator != null) {
                    this.ammeter2Indicator.updateValue(this.circuitService.findAmmeterById(18L).getCurrent());
                }
            }
        }

        private void render() {
            this.addChildren(this.canvas);
            this.getNode().getChildren().add(this.tableView);

            this.tableView.setVisible(false);
            this.tableView.setManaged(false);

            this.canvas.addChildren(177.0, 208.0, this.circuitKeySA1);
            this.canvas.addChildren(300.0, 26.0, this.circuitKeySA2);

            final double groupX = 67.0;
            this.canvas.addChildren(groupX, 494.5, this.circuitKeyAC1);
            this.canvas.addChildren(groupX, 537.0, this.circuitKeyBC2);
            this.canvas.addChildren(groupX, 575.0, this.circuitKeyCC3);

            this.canvas.addChildren(180.0, 350, this.rRheostat1Typer);
            this.canvas.addChildren(380.0, 65.0, this.rRheostat2Typer);

            this.canvas.addChildren(360, 320, this.inductorTyper);

            this.canvas.addChildren(345.0, 190.0, this.voltmeterIndicator);

            this.canvas.addChildren(300.0, 255.0, this.ammeter1Indicator);
            this.canvas.addChildren(190.0, 95.0, this.ammeter2Indicator);

            this.canvas.addChildren(240.0, 460, this.batteryTyper);
        }

        private void applyStyles() {
            this.setDirection(Direction.Y_DIRECTION);
            this.setAlign(Align.CENTER, Align.CENTER);

            final String imageUrl = Objects.requireNonNull(Canvas.class.getResource("/images/circuit-diagram.jpg")).toExternalForm();
            this.canvas.addInlineStyles("""
                    -fx-background-image: url('%s');
                    -fx-background-size: cover;
                    -fx-background-position: center center;
                """.formatted(imageUrl));

            this.setupScaling();
        }

        private void setupScaling() {
            final Resolution res = InjectionService.getInjectionService().getBean(Resolution.class);

            this.getNode().sceneProperty().addListener((obs, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.windowProperty().addListener((obsW, oldWin, newWin) -> {
                        if (newWin != null) {
                            final javafx.stage.Window win = newWin;

                            win.setOnShown(event -> {
                                if (!this.isShownOnce) {
                                    final double headerH = win.getHeight() - newScene.getHeight();
                                    if (!Double.isNaN(headerH)) {
                                        this.cachedHeaderHeight = headerH;
                                    }
                                    this.isShownOnce = true;
                                }
                            });

                            final Runnable scaler = () -> {
                                final double sw = win.getWidth() / res.getWidth();
                                final double sh = win.getHeight() / (res.getHeight() + this.cachedHeaderHeight);
                                final double aspect = Math.min(sw, sh);
                                this.canvas.getNode().setScaleX(aspect);
                                this.canvas.getNode().setScaleY(aspect);

                                this.tableView.setScaleX(aspect);
                                this.tableView.setScaleY(aspect);
                            };

                            win.widthProperty().addListener((o, ov, nv) -> scaler.run());
                            win.heightProperty().addListener((o, ov, nv) -> scaler.run());
                        }
                    });
                }
            });
        }
    }

    public static final class FooterSection extends Box {
        private final List<Button> buttons;

        public FooterSection(final HomeScreen homeScreen) {
            super();
            this.setDirection(Direction.X_DIRECTION);
            this.addInlineStyles("-fx-background-color: #eaeef6;");
            this.setAlign(Align.CENTER, Align.CENTER);

            final Button saveBtn = new PrimaryButton();
            saveBtn.addChildren(new PrimaryTopography("Зберегти дослідні дані"));
            saveBtn.setMargin(0.0, 12.0);
            saveBtn.setPadding(8.0, 12.0);
            saveBtn.onClick((e, c) -> homeScreen.mainSection.saveCurrentStateToTable());

            final Typography viewTitle = new PrimaryTopography("Переглянути результат");
            final Button viewBtn = new PrimaryButton();
            viewBtn.addChildren(viewTitle);
            viewBtn.setMargin(0.0, 12.0);
            viewBtn.setPadding(8.0, 12.0);
            viewBtn.onClick((e, c) -> {
                final boolean isTable = homeScreen.mainSection.toggleViewMode();
                if (isTable) {
                    viewTitle.setText("Повернутись до схеми");
                } else {
                    viewTitle.setText("Переглянути результат");
                }
            });

            this.buttons = List.of(saveBtn, viewBtn);
            this.addChildren(this.buttons.toArray(new Button[0]));
        }
    }

    private final Header header;
    private final MainSection mainSection;
    private final FooterSection footerSection;

    public HomeScreen() {
        super();
        this.header = new Header();
        this.mainSection = new MainSection();
        this.footerSection = new FooterSection(this);

        this.applyLayout();
        this.addChildren(this.header, this.mainSection, this.footerSection);
    }

    private void applyLayout() {
        this.header.bindWidthPercent(this, 100.0);
        this.header.setHeight(80.0);

        this.mainSection.bindWidthPercent(this, 100.0);
        this.mainSection.bindHeightPercent(this, 100.0, 160);

        this.footerSection.bindWidthPercent(this, 100.0);
        this.footerSection.setHeight(80.0);
    }
}