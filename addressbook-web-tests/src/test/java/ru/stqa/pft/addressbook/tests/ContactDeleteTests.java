package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTests extends TestBase{


    @Test
    public void testContactDelete() {
        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isTheAContact()) {
            app.goTo().gotoContactPage();
            app.getContactHelper().createContact(new ContactData().withFirstname("Александр").withLastname("Пучков").withAddress("Москва").withWork_number("88008888").withEmail("test@test.ru").withGroup("test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.goTo().gotoHomePage();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().deleteSelectedContact();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
            Assert.assertEquals(before, after);
    }

}
