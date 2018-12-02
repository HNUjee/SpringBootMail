package com.example.service;

import javax.mail.MessagingException;

/**
 * @author Felxi
 * @date 2018-11-30
 */
public interface MailService {
    /**
     * 发送简单邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendMail(String to,String subject,String content);
	
	/***
	 * 发送html邮件
	 *
	 * @param to
	 * @param subject
	 * @param content
	 * @throws MessagingException
	 */
    void sendHtmlMail(String to,String subject,String content) throws MessagingException;
	
	/***
	 * 发送附件邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 * @throws MessagingException
	 */
    void sendAttachmentMail(String to,String subject,String content,String filePath) throws MessagingException;
	
	/***
	 * 发送图片附件邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param rscPath
	 * @param rscId
	 * @throws MessagingException
	 */
    void sendInlinResourceMail(String to,String subject,String content, String rscPath,String rscId) throws MessagingException;
	
	/***
	 * 基于模板发送邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param rscPath
	 * @param rscId
	 * @throws MessagingException
	 */
	void sendTemplateMail(String to,String subject,String content, String rscPath,String rscId) throws MessagingException;
	
}
