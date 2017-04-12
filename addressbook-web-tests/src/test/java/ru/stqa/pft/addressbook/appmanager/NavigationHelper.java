package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Sungatullina on 05.04.2017.
 */
public class NavigationHelper extends HelperBase{

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoContactPage() {
    click(By.linkText("add new"));
    wd.get("http://localhost/addressbook/edit.php");
  }

  public void gotoHomePage() {
      click(By.linkText("home"));
      wd.get("http://localhost/addressbook/");
  }
}
