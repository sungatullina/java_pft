package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.time.Instant;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Пользователь on 25.07.2017.
 */
public class AddContactToGroupTest extends TestBase{

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
   public void testAddContactToGroup(){
        app.goTo().homePage(); // на главную страницу
        Contacts before = app.db().contacts(); // получаем набор контактов из БД
        Groups groups = app.db().groups(); // получаем набор групп из БД

        ContactData addContact = before.iterator().next(); // получаем какой-то один контакт
        Groups beforeContactGroup = addContact.getGroups(); // получаем набор групп контакта

        Groups readyGroups = groups.without(beforeContactGroup); // вычитаем из всех групп группы, в которых есть контакт т.е. получаем группы в которых пользователь не состоит
        if (readyGroups.size() == 0 ){ // если групп нет, то
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test " + Instant.now().toString() )); // создаем новую группу. Для уникальности в конце названия подставим текущее время
            // надо получить id это группы. для этого:
            groups = app.db().groups(); // получаем набор групп из БД (т.к. количество групп обновилось)
            readyGroups = groups.without(beforeContactGroup); // вычитаем из всех групп группы, в которых есть контакт т.е. получаем группы в которых пользователь не состоит
            app.goTo().homePage();
        }
        GroupData addGroup = readyGroups.iterator().next(); // используем первую попавшуюся группу (из набора групп, в которых пользователя нет)

        app.contact().addContacts(addContact,addGroup); // добавим пользователя в группу
        app.goTo().homePage();

        int contactId = addContact.getId();// получим ID пользоватетля
        Groups afterContactGroup = app.db().getContactsById(contactId).getGroups(); // заново получим данные по пользователь из БД
        beforeContactGroup.add(addGroup); // добавим к набору групп пользователя (которые были до добавления) группу (которую мы ему добавили)
        assertThat(beforeContactGroup, equalTo(afterContactGroup));
    }
}
