package ru.leader_id.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// Страница создания мероприятия
public class EventsCreatePage {
    public final SelenideElement heading = $("h1.app-heading-1");
    public final SelenideElement eventNameValidate = $("*[data-qa=eventName] .app-validate.is-error");


    // шаг "Основное"
    public final SelenideElement eventName = $("input[data-qa=eventName]");
    // модальное окно выбора изображения
    public final SelenideElement modalImage = $(".app-upload-with-crop .el-dialog");
    public final SelenideElement modalImageTitle = modalImage.$(".app-dialog__title h3");
    public final SelenideElement modalImageClose = modalImage.$("button[aria-label=Close]");

    // шаг "Место и время"
    public final ElementsCollection heading4 = $$("h4.app-heading-4");
    public final SelenideElement onlineEvent = heading4.findBy(Condition.text("Онлайн-мероприятие"));
    public final SelenideElement dateStart = $("*[placeholder='Дата начала'] input");
    public final SelenideElement timeStart = $("*[placeholder='Время начала'] input");
    public final SelenideElement timeEnd = $("*[placeholder='Время окончания'] input");
    public final SelenideElement eventUrl = $("*[placeholder='Ссылка'] input");

    // всплывающее сообщение
    public final SelenideElement notificationTitle = $(".el-notification__title");
    public final SelenideElement notificationContent = $(".el-notification__content");
    public final SelenideElement notificationClose = $(".el-notification__closeBtn");
}
