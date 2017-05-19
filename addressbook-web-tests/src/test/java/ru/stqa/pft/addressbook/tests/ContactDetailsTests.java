package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Пользователь on 19.05.2017.
 */
public class ContactDetailsTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size()==0) {
            app.goTo().contactPage();
           app.contact().create(new ContactData().withFirstname("Александр").withLastname("Пучков").withAddress("Москва").withWorkPhone("88008888").withEmail("test@test.ru")
                    .withEmail2("test2@test").withEmail3("test3@test.ru"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();  //загружаем множество контактов, выбираем контакт случайным образом
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact); // infoFromEditForm -  информация  о контакте из формы редактирования

        contactInfoFromEditForm.withAllPhones(mergePhones(contactInfoFromEditForm));
        contactInfoFromEditForm.withHomePhone(null).withMobilePhone(null).withWorkPhone(null);

        contactInfoFromEditForm.withAllEmail(mergeEmails(contactInfoFromEditForm));
        contactInfoFromEditForm.withEmail(null).withEmail2(null).withEmail3(null);

        assertThat(contact, equalTo(contactInfoFromEditForm));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s)-> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s)-> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
