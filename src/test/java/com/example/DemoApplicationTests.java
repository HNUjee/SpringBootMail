package com.example;

import com.example.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

  @Autowired private MailService mailService;
  @Value("${mail.fromMail.addr}")
  private String form;
  @Resource
  TemplateEngine engine;

  @Test
  public void contextLoads() {}

  @Test
  public void sendSimpleMail() throws Exception {
    mailService.sendMail(form, "简单邮件", "springboot实现邮件发送");
  }

  @Test
  public void sendHtmlMail() throws Exception {
      String content="<html>\n"+"<body>\n"
                             + "<h3>hello world!测试发送html格式邮件</h3>\n"
                             +"</body>\n"+"</html>";
    mailService.sendHtmlMail("fjieyou@126.com", "Html邮件", content);
  }

  @Test
  public void sendAttachmentMail() throws Exception {
    String filePath = "D:\\log\\demo.log.2018-11-29.0.gz";
    String content = "附件邮件";
    mailService.sendAttachmentMail(form, "附件邮件", content, filePath);
  }

  @Test
  public void sendInlinResourceMail() throws Exception {
    String filePath = "D:\\file\\images\\2cc81a9e2f424f329c1ffb1acd84324d.jpg";
      String rscId = "001";
    String content = "<html><body>图片邮件：<img src=\'cid:'"+rscId+"\'></img</body></html>";
    mailService.sendInlinResourceMail(form, "附件邮件", content, filePath,rscId);
  }
  @Test
    public void  testTemplateMailTest() throws MessagingException {
      Context context=new Context();
            context.setVariable("id","159954");
            String emailContent=engine.process("emailTemplate",context);
            engine.process("emailTemplate",context);
            
            mailService.sendHtmlMail("fjieyou@126.com","模板引擎",emailContent);
  }
}
