package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;

/**
 * Created by Пользователь on 17.07.2017.
 */
public class DbConnectionTest {

    @Test
    public void testDbConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select username from mantis_user_table");
            Users users = new Users();
            while (rs.next()) {
                users.add (new UserData().withUsername(rs.getString("username")));
            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(users);

            // Do something with the Connection

           } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
