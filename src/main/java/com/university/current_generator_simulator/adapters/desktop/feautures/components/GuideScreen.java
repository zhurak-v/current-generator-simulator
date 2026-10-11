package com.university.current_generator_simulator.adapters.desktop.feautures.components;

import com.university.current_generator_simulator.adapters.desktop.annotations.Route;
import com.university.current_generator_simulator.adapters.desktop.common.components.*;

import java.util.List;

@Route(name = "guide-screen")
public final class GuideScreen extends Screen {

    public static final class ContentSection extends Box {

        public ContentSection() {
            super();
            this.setDirection(Direction.Y_DIRECTION);
            this.setAlign(Align.START, Align.CENTER);
            this.setPadding(60.0, 80.0);

            final Typography title = new PrimaryTopography("Керівництво користувача симулятора");
            title.addInlineStyles("""
                    -fx-font-size: 30px;
                    -fx-font-weight: bold;
                    -fx-text-fill: #1a202c;
                    -fx-padding: 0 0 16 0;
            """);

            final Typography description = new PrimaryTopography(
                    "Цей посібник містить перелік усіх доступних функцій інтерфейсу програми " +
                            "для взаємодії з віртуальною електричною схемою та збереження результатів."
            );
            description.addInlineStyles("""
                    -fx-font-size: 16px;
                    -fx-text-fill: #718096;
                    -fx-max-width: 850px;
                    -fx-padding: 0 0 48 0;
            """);

            final Box stepsBox = new Box(Direction.Y_DIRECTION);

            final List<String> steps = List.of(
                    "1. Регулювання вхідної напруги джерела\nВи можете самостійно змінювати напругу живлення всієї системи. Для цього використовуйте відповідне інтерактивне текстове поле та вводьте туди необхідне значення у вольтах.",
                    "2. Комутація за допомогою ключів кола\nПрограма дозволяє в реальному часі керувати станом елементів схеми. Натискайте на перемикачі SA1 та SA2, щоб замикати або розмикати відповідні ділянки електричного ланцюга.",
                    "3. Налаштування опору реостатів\nВам доступне керування параметрами опору в колі. Вводьте числові значення в омах у спеціальні поля для зміни опору регулювального реостата Rр та реостата навантаження Rнав.",
                    "4. Спостереження за показниками приладів\nГрафічна схема динамічно відображає поточний стан. Слідкуйте за зміною напруги на цифровому табло вольтметра PV1, а також за силою струму на амперметрах PA1 і PA2.",
                    "5. Фіксація результатів у журналі\nНатискайте кнопку 'Зберегти дослідні дані', щоб занести поточні виміри приладів та параметри реостатів до таблиці. Перемикайтеся на режим 'Переглянути результат' для аналізу збереженого журналу дослідів."
            );

            for (final String step : steps) {
                final Typography stepText = new PrimaryTopography(step);
                stepText.addInlineStyles("""
                    -fx-font-size: 16px;
                    -fx-text-fill: #2d3748;
                    -fx-line-spacing: 8px;
                    -fx-padding: 0 0 36 0;
                    -fx-max-width: 850px;
                """);
                stepsBox.addChildren(stepText);
            }

            this.addChildren(title, description, stepsBox);
        }
    }

    private final Header header;
    private final ContentSection contentSection;

    public GuideScreen() {
        super();
        this.header = new Header();
        this.contentSection = new ContentSection();

        this.applyLayout();
        this.addChildren(this.header, this.contentSection);
    }

    private void applyLayout() {
        this.header.bindWidthPercent(this, 100.0);
        this.header.setHeight(80.0);

        this.contentSection.bindWidthPercent(this, 100.0);
        this.contentSection.bindHeightPercent(this, 100.0, 80);
    }
}