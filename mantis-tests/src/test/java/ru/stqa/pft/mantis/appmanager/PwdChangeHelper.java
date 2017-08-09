package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;


/**
 * Created by Пользователь on 09.08.2017.
 */
public class PwdChangeHelper extends HelperBase {

    public PwdChangeHelper(ApplicationManager app){
        super(app);
    }

    public void goToManagePage () {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }


   public void selectUser(String username){
       click(By.linkText(username));
   }

    public void submitResetPassword(){
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void resetPassword(String username){
        goToManagePage();
        selectUser(username);
        submitResetPassword();
    }

    public void login(String username, String password){
           wd.get(app.getProperty("web.baseUrl"));
            type(By.name("username"), username);
            type(By.name("password"), password);
            click(By.cssSelector("input[value='Login']"));
             }
}
