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
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Александр").withLastname("Пучков").withAddress("Москва").withWorkPhone("88008888").withEmail("test@test.ru").withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.db().contacts();
    ContactData item = before.iterator().next();
    ContactData contact = new ContactData().withId(item.getId()).withFirstname("Александр").withLastname("Пучков").withAddress("Москва002").withWorkPhone("188008888").withGroup("test@test.ru");
    app.goTo().homePage();
    app.contact().modify(item.getId(), contact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(item).withAdded(contact)));

  }
}
