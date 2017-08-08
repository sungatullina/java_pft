package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Пользователь on 08.08.2017.
 */
public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app){
        this.app = app;
        wiser = new Wiser();  //при инициализации создается объект типа Wiser(почтовый клиент)
    }

    public List<MailMessage> waitForMail(int count, long timeout)throws MessagingException, IOException{   //(количество писем которое должно прийти; время ожидания)
        long start = System.currentTimeMillis();   //запоминаем текущее время
        while (System.currentTimeMillis() < start + timeout){
            if (wiser.getMessages().size() >= count){
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
            }
            try{ // в случае если почты мало
                Thread.sleep(1000);  //ждать 1000 млсек
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        throw  new Error("No mail :(");
    }

    public static MailMessage toModelMail(WiserMessage m){
        try{
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException e){
            e.printStackTrace();
            return null;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void start(){
        wiser.start();
    }

    public void stop(){
        wiser.stop();
    }
}
