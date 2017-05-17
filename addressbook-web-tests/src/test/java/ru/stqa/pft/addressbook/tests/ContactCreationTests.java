package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Александр").withLastname("Пучков").withAddress("Москва").withWork_number("88008888").withEmail("test@test.ru").withGroup("test1");
    app.goTo().contactPage();
    app.contact().create(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }

}
