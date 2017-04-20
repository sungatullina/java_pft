package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTests extends TestBase{


    @Test
    public void testContactDelete() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isTheAContact()) {
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("Александр", "Пучков", "Москва", "88008888", "test@test.ru", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
            Assert.assertEquals(before, after);
    }

}
