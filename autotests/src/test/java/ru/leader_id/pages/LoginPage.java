package ru.leader_id.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

// Страница входа
public class LoginPage {
    private final SelenideElement modal = $("*[data-qa=loginModal]");
    private final SelenideElement loginEmail = $("input[dataqa=loginEmail]");
    private final SelenideElement loginPassword = $("input[data-qa=loginPassword]");
    private final SelenideElement smartCaptcha = $("*[data-testid='smartCaptcha-container']");

    private final SelenideElement loginSubmitBtn = $("button[data-qa=loginSubmitBtn]");
    private final ElementsCollection theUser = $$("*[data-qa=theUser]");

    // Вход
    public void login() throws IOException {
        Properties prop = new Properties();
        prop.load(this.getClass().getResourceAsStream("/test.properties"));
        String url = prop.getProperty("url");
        String login = prop.getProperty("login");
        String password = prop.getProperty("password");

        // открываем страницу
        open(url);

        // ожидание появления формы
        modal.shouldBe(Condition.visible);

        // ввод данных и вход
        loginEmail.val(login);
        loginPassword.val(password);
        loginSubmitBtn.click();

        // если появилась CAPTCHA, ждём ручного ввода
        sleep(1000);
        if (smartCaptcha.isDisplayed()) {
            while (theUser.size() == 0) {
                sleep(1000);
            }
        }

        // успешный вход
        theUser.shouldHave(CollectionCondition.size(1));
    }
}
