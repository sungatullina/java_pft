package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{


  @Test 
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData().withFirstname("Александр").withLastname("Пучков").withAddress("Москва").withWork_number("88008888").withEmail("test@test.ru").withGroup("test1");
    app.goTo().gotoContactPage();
    app.getContactHelper().createContact(contact);
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

   /* int max = 0;
    for (ContactData g : after) {
      if (g.getId()>max) {
        max = g.getId();
      }
    }*/


    before.add(contact);
    Comparator<? super ContactData> byId = (c1 , c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
