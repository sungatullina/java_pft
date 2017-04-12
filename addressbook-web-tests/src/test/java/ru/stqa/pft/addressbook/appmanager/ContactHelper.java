package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Sungatullina on 12.04.2017.
 */
public class ContactHelper extends HelperBase{


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("work"),contactData.getWork_number());
    type(By.name("email"),contactData.getEmail());
      }

  public void deleteSelectedContact() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
      wd.switchTo().alert().accept();
  }

  public void selectContact() {
      click(By.name("selected[]"));
  }
}
