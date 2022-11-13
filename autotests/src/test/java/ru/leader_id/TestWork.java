package ru.leader_id;

import java.io.IOException;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.leader_id.elements.Elements;
import ru.leader_id.elements.MainMenu;
import ru.leader_id.pages.EventsCreatePage;
import ru.leader_id.pages.EventsPage;
import ru.leader_id.pages.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class TestWork {
    /**
     * Открытие страницы создания мероприятия (выполняется перед каждым тестом)
     */
    @BeforeEach
    public void openPage() throws IOException {
        new LoginPage().login();
        new MainMenu().open("Мероприятия");
        new EventsPage().eventCreateBtn.click();
    }

    /**
     * Закрытие после каждого теста
     */
    @AfterEach
    public void closePage() {
        closeWebDriver();
    }

    /**
     * Проверка валидации длины строки для поля ввода "Название мероприятия"
     *  - Длина строки до 120 символов
     */
    @Test
    public void testNameValidation() {
        // проверяем, что страница открыта
        EventsCreatePage page = new EventsCreatePage();
        page.heading.shouldHave(text("Создать мероприятие"));

        // проверяем, что нет ошибки валидации при строке в 120 символов
        page.eventName.val("Проверка валидации длины строки для поля ввода 'Название мероприятия'. " +
                "Длина строки до 120 символов, в этой строке 120.");
        page.eventNameValidate.shouldHave(Condition.empty);

        // проверяем ошибку валидации при строке в 121 символ
        page.eventName.val("Проверка валидации длины строки для поля ввода 'Название мероприятия'. " +
                "Длина строки до 120 символов, а в строке уже 121!");
        page.eventNameValidate.shouldHave(text("Поле «Название» не может быть более 120 символов."));
    }

    /**
     * Проверка на смену языка в модальном окне "Выбор изображения" на шаге "Основное"
     *  - На русском языке "Выберите изображение"
     *  - На английском языке "Select Image"
     */
    @Test
    public void testModalTextEnglish() {
        // проверяем, что страница открыта
        EventsCreatePage page = new EventsCreatePage();
        page.heading.shouldHave(text("Создать мероприятие"));

        // нажимаем на кнопку "Выбрать файл" и проверяем заголовок
        new Elements().buttonWithText("Выбрать файл").click();
        page.modalImage.shouldHave(Condition.visible);
        page.modalImageTitle.shouldHave(text("Выберите изображение"));

        // закрываем модальное окно и выбираем английский язык
        page.modalImageClose.click();
        new MainMenu().selectLang("Английский");

        // нажимаем на кнопку "Select a file" и проверяем заголовок
        new Elements().buttonWithText("Select a file").click();
        page.modalImage.shouldHave(Condition.visible);
        page.modalImageTitle.shouldHave(text("Select Image"));
    }

    /**
     * Санити-тест на создание черновика мероприятия
     *  - Входим
     *  - Заполняем шаги создания мероприятия
     *  - Сохраняем черновик
     *  - Удаляем черновик
     */
    @Test
    public void testDraft() {
        // проверяем, что страница открыта
        EventsCreatePage page = new EventsCreatePage();
        page.heading.shouldHave(text("Создать мероприятие"));

        // Шаг "Основное"
        page.eventName.val("Тестовый черновик");
        new Elements().buttonWithText("Далее").click();

        // Шаг "Место и время"
        page.onlineEvent.click();
        page.dateStart.val("31.12.2022");
        page.timeStart.val("09:00");
        page.timeEnd.val("10:00");
        page.eventUrl.val("https://leader-id.ru/");
        new Elements().buttonWithText("Далее").click();

        // Шаг "Дополнительно"
        sleep(1000);
        new Elements().buttonWithText("Сохранить черновик").click();
        // проверка успешного сохранения
        page.notificationTitle.shouldHave(text("Успешно"));
        page.notificationContent.shouldHave(text("Данные обновлены."));
        page.notificationClose.click();
        sleep(1000); // ожидаем закрытие нотификации

        // Удаление черновика
        new Elements().buttonWithText("Удалить").click();
        new Elements().buttonWithText("Подтверждаю").click();
        page.notificationTitle.shouldHave(text("Успешно"));
        page.notificationContent.shouldHave(text("Мероприятие удалено"));
        page.notificationClose.click();
    }

}
