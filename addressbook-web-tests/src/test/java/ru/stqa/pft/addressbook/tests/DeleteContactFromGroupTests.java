package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Пользователь on 03.08.2017.
 */
public class DeleteContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Александр").withLastname("Пучков").withAddress("Москва").withWorkPhone("88008888").withEmail("test@test.ru"));
            app.goTo().homePage();
        }
    }



    @Test
    public void testDeleteContactFromGroup(){
        app.goTo().homePage();
        Contacts before = app.db().contacts(); // получаем набор контактов из БД
        Groups groups = app.db().groups(); // получаем набор групп из БД

        ContactData contact = before.iterator().next(); // получаем какой-то один контакт
        int contactId = contact.getId();// получим ID пользоватетля
        Groups beforeContactGroup = contact.getGroups(); // получаем набор групп контакта

        if (beforeContactGroup.size() == 0){ //если контакт не состоит ни в одной группе
            GroupData addGroup = groups.iterator().next();
            app.contact().addContacts(contact,addGroup); // добавим пользователя в группу
            app.goTo().homePage();
            contact = app.db().getContactsById(contactId); // заново получим данные по пользователь из БД
            beforeContactGroup = contact.getGroups(); // получаем набор групп контакта
        }

        GroupData deleteGroup = beforeContactGroup.iterator().next();//использует первую попавшуюся группу, в которую входит контакт
        app.contact().deleteContactFromGroup(contact, deleteGroup); //удаляем пользователя из группы
        app.goTo().homePage();

        Groups afterContactGroup = app.db().getContactsById(contactId).getGroups();
        Groups aContactGroup = contact.getGroups();
        assertThat(beforeContactGroup, equalTo(aContactGroup));
    }
}