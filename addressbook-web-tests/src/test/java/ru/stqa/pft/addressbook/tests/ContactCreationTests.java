package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    app.gotoContactPage();
    app.fillContactForm(new ContactData("Александр", "Пучков", "Москва", "88008888", "test@test.ru"));
    app.submitContactCreation();
  }

}
