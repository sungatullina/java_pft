package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTests extends TestBase{


    @Test
    public void testContactDelete() {
        app.getNavigationHelper().gotoHomePage();
        int before =  app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isTheAContact()) {
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("Александр", "Пучков", "Москва", "88008888", "test@test.ru", "test1"));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact(before -1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().gotoHomePage();
        int after =  app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }

}
