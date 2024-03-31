package ru.netology.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DbDataHelper;
import ru.netology.page.*;

import static com.codeborne.selenide.Selenide.open;


public class LoginTest {
    @AfterAll
    static void clearTables() {
        DbDataHelper.clearTables();
    }

    @Test
    void shouldLogin() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
}
