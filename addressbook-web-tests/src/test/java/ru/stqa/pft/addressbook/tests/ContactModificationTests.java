package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Sungatullina on 12.04.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size()==0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Александр").withLastname("Пучков").withAddress("Москва").withWork_number("88008888").withEmail("test@test.ru").withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData item = before.iterator().next();
    ContactData contact = new ContactData().withId(item.getId()).withFirstname("Александр").withLastname("Пучков").withAddress("Москва002").withWork_number("188008888").withGroup("test@test.ru");
    app.goTo().homePage();
    app.contact().modify(item.getId(), contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(item).withAdded(contact)));

  }
}
