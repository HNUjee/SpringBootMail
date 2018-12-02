package com.example.service.impl;

import com.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Felxi
 * @date 2018-11-30
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.fromMail.addr}")
    private String form;
    /**
     * 发送简单邮件
     * @param to 接受者
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        //发起者
        mailMessage.setFrom(form);
        //接受者
        mailMessage.setTo(to);
         //如果发给多个人的：
        //mailMessage.setTo("1xx.com","2xx.com","3xx.com");    
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            mailSender.send(mailMessage);
            System.out.println("发送简单邮件");
        }catch (Exception e){
            System.out.println("发送简单邮件失败");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message =mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(form);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
        
    }
    
    @Override
    public void sendAttachmentMail(String to, String subject, String content, String filePath) throws MessagingException {
        MimeMessage message =mailSender.createMimeMessage();
        MimeMessageHelper helper =new MimeMessageHelper(message,true);
        helper.setFrom(form);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        FileSystemResource fileSystemResource=new FileSystemResource(new File(filePath));
        String fileName=fileSystemResource.getFilename();
        helper.addAttachment(fileName,fileSystemResource);
        mailSender.send(message);
    }
    
    @Override
    public void sendInlinResourceMail(String to, String subject, String content, String rscPath,String rscId) throws MessagingException {
        MimeMessage message =mailSender.createMimeMessage();
        MimeMessageHelper helper =new MimeMessageHelper(message,true);
        helper.setFrom(form);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        FileSystemResource fileSystemResource=new FileSystemResource(new File(rscPath));
        helper.addInline(rscId,fileSystemResource);
        mailSender.send(message);
    }
    
    @Override
    public void sendTemplateMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException {
    
    }
}
