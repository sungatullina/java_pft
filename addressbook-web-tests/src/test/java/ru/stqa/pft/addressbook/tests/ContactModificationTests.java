package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Sungatullina on 12.04.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isTheAContact()) {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactData("Александр", "Пучков", "Москва", "88008888", "test@test.ru", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData(before.get(before.size() -1).getId(), "Александр1", "Пучков", "Москва2", "88008888", "test@test.ru", null);
    app.getContactHelper().fillContactForm(contact,false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));


  }
}
