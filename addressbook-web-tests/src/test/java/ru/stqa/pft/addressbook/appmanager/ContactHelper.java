package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sungatullina on 12.04.2017.
 */
public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("work"),contactData.getWork_number());
    type(By.name("email"),contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void deleteSelectedContact() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
      wd.switchTo().alert().accept();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById (int id) {
    wd.findElement (By.cssSelector("input[value='"+ id +"']")).click();
  }

  public void initContactModification(int index) {
   // click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    wd.findElements(By.xpath("//table[@id='maintable']//img[@title='Edit']")).get(index).click();
  }

  public void initContactModificationById(int id) {
    wd.get("http://localhost/addressbook/edit.php?id=" + Integer.toString(id));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }


  public void create(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public void modify(int index, ContactData contact) {
   initContactModificationById(index);
   fillContactForm(contact,false);
   submitContactModification();
  }

  public void delete(int index) {
   selectContact(index);
   deleteSelectedContact();
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();

  }


  public boolean isTheAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }



  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr[td]"));
    for (WebElement element : elements) {
      int id =Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")) ;
      contacts.add(new ContactData().withId(id).withFirstname(element.findElement(By.xpath(".//td[3]")).getText()).withLastname(element.findElement(By.xpath(".//td[2]")).getText()));
    }
    return contacts;
  }

}
