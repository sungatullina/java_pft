package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Пользователь on 07.08.2017.
 */
public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration(){
        app.registration().start("user1", "user1@localdomain");
    }
}
