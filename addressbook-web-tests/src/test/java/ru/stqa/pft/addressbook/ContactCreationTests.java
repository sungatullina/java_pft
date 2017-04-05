package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    gotoContactPage();
    fillContactForm(new ContactData("Александр", "Пучков", "Москва", "88008888", "test@test.ru"));
    submitContactCreation();
  }

}
