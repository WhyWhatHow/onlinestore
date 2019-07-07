package com.sdut.onlinestore;

import com.sdut.onlinestore.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by summer on 2017/5/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

//    @Autowired
//    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail()  {
        try{
            mailService.sendSimpleMail("another_nash@163.com","test simple mail"," hello this is simple mail, i'm your ....");

        }catch(Exception e){
            System.err.println(163+"----------------------------");
        }
        try {
            mailService.sendSimpleMail("anothernash@126.com","test simple mail"," hello this is simple mail, i'm your ....");

        }catch (Exception e ){
            System.err.println(126+"-------------------------------");
        }
        try {

            mailService.sendSimpleMail("1246389103@qq.com","test simple mail"," hello this is simple mail, i'm your ....");
        }catch (Exception e ){
            System.err.println("qqq-------------------------------");
        }

    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("anothernash@126.com","test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="e:\\tmp\\application.log";
        mailService.sendAttachmentsMail("anothernash@126.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\summer\\Pictures\\favicon.png";

        mailService.sendInlineResourceMail("anothernash@126.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


//    @Test
//    public void sendTemplateMail() {
//        //创建邮件正文
//        Context context = new Context();
//        context.setVariable("id", "006");
//        String emailContent = templateEngine.process("emailTemplate", context);
//        mailService.sendHtmlMail("anothernash@126.com","主题：这是模板邮件",emailContent);
//    }
}
