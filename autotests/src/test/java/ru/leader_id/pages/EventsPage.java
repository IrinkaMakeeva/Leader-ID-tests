package ru.leader_id.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

// Страница событий
public class EventsPage {
    public final SelenideElement eventCreateBtn = $("*[data-qa=eventCreateBtn]");

}
