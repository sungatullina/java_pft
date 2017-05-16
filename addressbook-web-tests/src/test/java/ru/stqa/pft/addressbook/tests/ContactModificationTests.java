package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Random;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sungatullina on 12.04.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test (enabled = false)
  public void testContactModification(){
    app.goTo().gotoHomePage();
    if (! app.getContactHelper().isTheAContact()) {
      app.goTo().gotoContactPage();
      app.getContactHelper().createContact(new ContactData("Александр", "Пучков", "Москва", "88008888", "test@test.ru", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    Random randomGenerator = new Random();
    int index =(before.size()-1)-1;
    ContactData item = before.get(index);
    app.goTo().gotoHomePage();
    app.getContactHelper().initContactModification(index);
    ContactData contact = new ContactData(item.getId(), "Александр", "Пучков", "Москва008", "88008888", "test@test.ru", null);
    app.getContactHelper().fillContactForm(contact,false);
    app.getContactHelper().submitContactModification();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1 , c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


  }
}
