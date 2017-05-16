package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


public class GroupCreationTest extends TestBase{

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    app.goTo().groupPage();
    Set<GroupData> after = app.group().all(); //список элементов, после того как создана новая группа
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()); //функция, которая  преобразует объект в идентификатор в число
    before.add(group);
    Assert.assertEquals(before, after);

  }

}
