package ru.leader_id.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Elements {
    // Выбор кнопки по тексту
    public SelenideElement buttonWithText(String text) {
        return $(byXpath(".//button[contains(text(), '" + text + "')]"));
    }

}
