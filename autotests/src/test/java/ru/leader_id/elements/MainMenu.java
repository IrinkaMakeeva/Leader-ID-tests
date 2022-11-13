package ru.leader_id.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// Главное меню
public class MainMenu {
    // пункты меню
    private final SelenideElement events = $("a[href='/events']");

    // выбор языка
    private final SelenideElement langDropdown = $(".header__top .app-dropdown");
    public final ElementsCollection langDropdownItems = $$(".app-dropdown-popover__item");
    public final SelenideElement langRus = langDropdownItems.findBy(Condition.text("Rus"));
    public final SelenideElement langEng = langDropdownItems.findBy(Condition.text("Eng"));
    public final SelenideElement langKaz = langDropdownItems.findBy(Condition.text("Kaz"));

    // Открытие пункта меню
    public void open(String text) {
        if (text.contains("Мероприятия")) {
            events.click();
        }
    }

    // Выбор языка
    public void selectLang(String text) {
        langDropdown.click();
        if (text.contains("Русский")) {
            langRus.click();
        }
        if (text.contains("Английский")) {
            langEng.click();
        }
        if (text.contains("Казахский")) {
            langKaz.click();
        }
    }
}
