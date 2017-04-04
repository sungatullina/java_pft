package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String work_number;
  private final String email;

  public ContactData(String firstname, String lastname, String address, String work_number, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.work_number = work_number;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getWork_number() {
    return work_number;
  }

  public String getEmail() {
    return email;
  }
}
