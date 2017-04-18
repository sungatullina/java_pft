package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Sungatullina on 12.04.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    int before =  app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isTheAContact()) {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactData("Александр", "Пучков", "Москва", "88008888", "test@test.ru", "test1"));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact(before -1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Александр", "Пучков", "Москва2", "88008888", "test@test.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    int after =  app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
