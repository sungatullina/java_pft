package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Пользователь on 07.08.2017.
 */
public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app){
        this.app = app;
        ftp = new FTPClient();
    }

    public  void upload(File file, String target, String backup) throws IOException {  //загружает новый файл, но при этом старый временно переименовывает(локальный файл, имя файла куда это все загружается, имя резервной копии)
        ftp.connect(app.getProperty("ftp.host"));    //устанавливается соединение с сервером
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));  //логин
        ftp.deleteFile(backup);     //удаляем предыдущую резервную копию
        ftp.rename(target, backup);             //переименовываем удаленный файл, делаем резервную копию
        ftp.enterLocalPassiveMode();             //пассивный режим передачи данных
        ftp.storeFile(target, new FileInputStream(file));  // передается файл
        ftp.disconnect();                        //разрыв соединения
    }

    public  void restore(String backup, String target) throws IOException{   // восстанавливает старый файл
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);   // восстанавливается оригинальный файл из резервной копии
        ftp.disconnect();
    }

}
