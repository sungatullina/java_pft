package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    int before =  app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().createContact(new ContactData("Александр", "Пучков", "Москва", "88008888", "test@test.ru", "test1" ));
    app.getNavigationHelper().gotoHomePage();
    int after =  app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}
