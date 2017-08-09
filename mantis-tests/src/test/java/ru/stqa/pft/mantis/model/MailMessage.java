package ru.stqa.pft.mantis.model;

import java.util.Date;

/**
 * Created by Пользователь on 08.08.2017.
 */
public class MailMessage {

    public String to;
    public Date date;
    public String text;

    public MailMessage(String to, Date date, String text){
        this.to = to;
        this.date = date;
        this.text = text;
    }
}
