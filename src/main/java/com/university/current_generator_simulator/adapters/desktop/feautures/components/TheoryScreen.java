package com.university.current_generator_simulator.adapters.desktop.feautures.components;

import com.university.current_generator_simulator.adapters.desktop.annotations.Route;
import com.university.current_generator_simulator.adapters.desktop.common.components.Box;
import com.university.current_generator_simulator.adapters.desktop.common.components.Screen;
import com.university.current_generator_simulator.adapters.desktop.common.components.Typography;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;

@Route(name = "theory-screen")
public final class TheoryScreen extends Screen {

    public static final class ContentSection extends Box {

        public ContentSection() {
            super();

            this.setDirection(Direction.Y_DIRECTION);
            this.setAlign(Align.START, Align.CENTER);
            this.addInlineStyles("-fx-background-color: #ffffff;");

            final ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setPannable(true);
            scrollPane.setStyle("""
                    -fx-background-color: #ffffff;
                    -fx-background-insets: 0;
                    -fx-padding: 0;
                    """);

            final Box container = new Box(Direction.Y_DIRECTION);
            container.setAlign(Align.START, Align.CENTER);
            container.setPadding(40.0, 60.0);
            container.addInlineStyles("-fx-background-color: #ffffff;");
            container.getNode().setMinHeight(Region.USE_PREF_SIZE);

            final Typography mainTitle = new PrimaryTopography("ЛАБОРАТОРНА РОБОТА №3");
            mainTitle.addInlineStyles("""
                    -fx-font-size: 24px;
                    -fx-font-weight: bold;
                    -fx-text-fill: #1a202c;
                    -fx-padding: 0 0 4 0;
                    """);

            final Typography subTitle = new PrimaryTopography(
                    "Дослідження генератора постійного струму паралельного збудження"
            );
            subTitle.addInlineStyles("""
                    -fx-font-size: 18px;
                    -fx-font-weight: bold;
                    -fx-text-fill: #2d3748;
                    -fx-padding: 0 0 24 0;
                    """);

            final Typography section1Title = new PrimaryTopography("МЕТА РОБОТИ");
            section1Title.addInlineStyles("""
                    -fx-font-size: 16px;
                    -fx-font-weight: bold;
                    -fx-text-fill: #2b6cb0;
                    -fx-padding: 12 0 8 0;
                    """);

            final Typography section1Text = new PrimaryTopography(
                    """
                    1. Вивчити будову і принцип дії генератора постійного струму.
                    2. Вибрати контрольно-вимірювальні прилади для проведення дослідів.
                    3. Дослідити генератор постійного струму паралельного збудження в різних режимах роботи.
                    4. За результатами дослідних даних побудувати характеристики: неробочого режиму, зовнішню, регулювальну.
                    5. Визначити процентну зміну напруги генератора.
                    """
            );
            section1Text.addInlineStyles("""
                    -fx-font-size: 14px;
                    -fx-text-fill: #2d3748;
                    -fx-line-spacing: 6px;
                    -fx-padding: 0 0 20 0;
                    """);

            final Typography section2Title = new PrimaryTopography("ОСНОВНІ ТЕОРЕТИЧНІ ВІДОМОСТІ");
            section2Title.addInlineStyles("""
                    -fx-font-size: 16px;
                    -fx-font-weight: bold;
                    -fx-text-fill: #2b6cb0;
                    -fx-padding: 12 0 8 0;
                    """);

            final Typography section2Text = new PrimaryTopography(
                    """
                    Електричні машини постійного струму оборотні і за конструкцією не відрізняються між собою. В режимі генератора вони перетворюють механічну енергію в електричну енергію постійного струму. Машина складається з нерухомої станини (де кріпляться головні полюси з котушками збудження) та рухомого якоря (осердя, обмотки й колектор). Колектор призначений для випрямлення змінної ЕРС обмотки в постійну напругу на затискачах.

                    Принцип роботи генератора паралельного збудження базується на законі електромагнітної індукції та явищі самозбудження. При обертанні якоря в слабкому магнітному полі залишкового магнетизму індукується початкова ЕРС Е0 (близько 2-3% від номінальної). Оскільки обмотка збудження підімкнена паралельно до якоря, ця ЕРС створює в ній невеликий струм збудження, який підсилює основний магнітний потік, що призводить до лавиноподібного самозбудження машини.

                    Основні розрахункові рівняння генератора:
                    1. Рівняння напруги на затискачах:
                       U = E - Rя * Iя
                       де E — ЕРС якоря, Rя — опір обмоток якоря, Iя — струм якоря.
                    2. Рівняння струмів генератора:
                       Iя = Iз + Iнав
                       де Iз — струм збудження, Iнав — струм зовнішнього навантаження.
                    3. Струм збудження генератора:
                       Iз = U / (Rз + Rр)
                       де Rз — опір обмотки збудження, Rр — опір регулювального реостата.
                    """
            );
            section2Text.addInlineStyles("""
                    -fx-font-size: 14px;
                    -fx-text-fill: #2d3748;
                    -fx-line-spacing: 6px;
                    -fx-max-width: 900px;
                    -fx-padding: 0 0 20 0;
                    """);

            // --- ХАРАКТЕРИСТИКИ ГЕНЕРАТОРА ---
            final Typography section3Title = new PrimaryTopography("ХАРАКТЕРИСТИКИ ГЕНЕРАТОРА");
            section3Title.addInlineStyles("""
                    -fx-font-size: 16px;
                    -fx-font-weight: bold;
                    -fx-text-fill: #2b6cb0;
                    -fx-padding: 12 0 8 0;
                    """);

            final Typography section3Text = new PrimaryTopography(
                    """
                    1. Характеристика неробочого режиму: залежність E = f(Iз) при сталій швидкості обертання валу та відсутності навантаження (Iнав = 0).
                    2. Зовнішня характеристика: залежність U = f(Iнав) при сталому опорі кола збудження та незмінній швидкості валу валу. Показує падіння напруги через опір якоря та реакцію якоря.
                    3. Регулювальна характеристика: залежність Iз = f(Iнав). Показує, як потрібно змінювати струм збудження при зростанні навантаження, щоб утримувати вихідну напругу сталою (U = const).
                    4. Процентна зміна напруги генератора при переході від номінального режиму до неробочого ходу:
                       dU = ((U0 - Uн) / Uн) * 100%
                    """
            );
            section3Text.addInlineStyles("""
                    -fx-font-size: 14px;
                    -fx-text-fill: #2d3748;
                    -fx-line-spacing: 6px;
                    -fx-padding: 0 0 20 0;
                    """);

            final Typography section4Title = new PrimaryTopography("ПОРЯДОК ВИКОНАННЯ РОБОТИ / ХІД РОБОТИ");
            section4Title.addInlineStyles("""
                    -fx-font-size: 16px;
                    -fx-font-weight: bold;
                    -fx-text-fill: #2b6cb0;
                    -fx-padding: 12 0 8 0;
                    """);

            final Typography section4Text = new PrimaryTopography(
                    """
                    Завдання 1. Ознайомлення та перевірка самозбудження
                    1. Розглянути схему віртуального стенда. Привідним пристроєм для генератора виступає трифазний асинхронний двигун (АД), що запускається автоматом SF1.
                    2. Увімкнути SF1 для розкручування валу генератора. Перевірити наявність залишкової ЕРС на вольтметрі PV1 при розімкнених ключах SA1 та SA2.

                    Завдання 2. Зняття характеристики неробочого режиму E = f(Iз)
                    1. Переконатися, що тумблер навантаження SA2 розімкнений (Iнав = 0). Замкнути вимикач кола збудження SA1.
                    2. За допомогою реостата Rр встановити максимальний струм збудження, щоб напруга перевищувала номінальну на 20-25%.
                    3. Поступово переміщувати повзунок реостата Rр ТІЛЬКИ в напрямі зменшення струму. Зафіксувати 6-8 точок залежності напруги від струму збудження та занести їх до таблиці результатів.
                    4. Розімкнути вимикач SA1 та зафіксувати чисте значення залишкової ЕРС Е0.
                    5. Знову замкнути SA1 і виконати аналогічне зняття 6-8 точок, але тепер плавно переміщуючи реостат Rр ТІЛЬКИ в напрямі збільшення струму збудження.

                    Завдання 3. Зняття зовнішньої характеристики U = f(Iнав)
                    1. При увімкненому SF1 та замкненому SA1 налаштувати реостат Rр так, щоб напруга генератора становила (1.2 - 1.25) від номінальної.
                    2. Замкнути вимикач навантаження SA2. За допомогою реостата навантаження Rнав досягти номінального режиму роботи (U = Uн, I = Iн). ПІСЛЯ ЦЬОГО РЕОСТАТ Rп БІЛЬШЕ НЕ РУХАТИ.
                    3. Розімкнути вимикач SA2 та записати початкову точку холостого ходу в таблицю.
                    4. Знову замкнути SA2 і, поступово зменшуючи опір реостата Rнав (збільшуючи струм навантаження до критичного значення), зафіксувати 6-8 дослідних точок падіння напруги. Записати дані кнопкою збереження.

                    Завдання 4. Зняття регулювальної характеристики Iз = f(Iнав)
                    1. Розімкнути вимикач навантаження SA2. Реостатом Rр виставити номінальну напругу генератора на вольтметрі PV1. Записати початкове значення струму збудження Іоз.
                    2. Замкнути вимикач навантаження SA2.
                    3. Поступово зменшувати опір реостата навантаження Rнав. Кожен раз при зміні струму навантаження підрегульовувати реостат збудження Rр так, щоб напруга на вольтметрі PV1 незмінно залишалася номінальною (U = const).
                    4. Зафіксувати 6-8 точок вимірювань та зберегти дослідні дані для подальшої побудови графіків у звіті.
                    """
            );
            section4Text.addInlineStyles("""
                    -fx-font-size: 14px;
                    -fx-text-fill: #2d3748;
                    -fx-line-spacing: 6px;
                    -fx-max-width: 900px;
                    -fx-padding: 0 0 20 0;
                    """);

            container.addChildren(
                    mainTitle,
                    subTitle,
                    section1Title,
                    section1Text,
                    section2Title,
                    section2Text,
                    section3Title,
                    section3Text,
                    section4Title,
                    section4Text
            );

            scrollPane.setContent(container.getNode());
            this.getNode().getChildren().add(scrollPane);
        }
    }

    private final Header header;
    private final ContentSection contentSection;

    public TheoryScreen() {
        super();

        this.header = new Header();
        this.contentSection = new ContentSection();

        this.applyLayout();

        this.addChildren(
                this.header,
                this.contentSection
        );
    }

    private void applyLayout() {
        this.header.bindWidthPercent(this, 100.0);
        this.header.setHeight(80.0);

        this.contentSection.bindWidthPercent(this, 100.0);
        this.contentSection.bindHeightPercent(this, 100.0, 80);
    }
}