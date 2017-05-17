package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTest extends TestBase{

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    app.goTo().groupPage();
    Groups after = app.group().all(); //список элементов, после того как создана новая группа
    assertThat(after.size(), equalTo(before.size() + 1));

    //group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()); //функция, которая  преобразует объект в идентификатор в число
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

}
