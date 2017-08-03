package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Пользователь on 18.07.2017.
 */
public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test (enabled = true)
    public void testHbConnectionContact(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();

        session.getTransaction().commit();
        session.close();
        for (ContactData contact : result) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }
    }

   @Test (enabled = false)
    public void testHbConnection(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for (GroupData group : result) {
            System.out.println(group);
        }
        session.getTransaction().commit();
        session.close();
    }
}
