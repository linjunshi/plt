package com.santrong.plt.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author weinianjie
 * @date 2014年11月25日
 * @time 上午11:01:52
 */
public class MailUtils {
	
	    private static final String smtpServer = "smtp.exmail.qq.com";//发件服务器
	    //private static final String smtpServer = "smtp.gmail.com";//发件服务器
	    private static final String account = "santrong@santrong.com";//发件用户名
	    private static final String pwd = "sanjian20140409";//发件密码

	    public static int sendMail(String address, String subject, String content) {
	        //int rs = -1;
	        Properties props = System.getProperties(); // Setup mail server
	                //props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	                props.setProperty("mail.smtp.socketFactory.fallback", "false");
	                //props.setProperty("mail.smtp.port", "25");
	                //props.setProperty("mail.smtp.socketFactory.port", "25");
	                //props.setProperty("mail.smtp.port", "465");
	                //props.setProperty("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.host", smtpServer); // Get session
	        props.put("mail.smtp.auth", "true"); //这样才能通过验证
	        MyAuthenticator myauth = new MyAuthenticator(account, pwd);
	        Session session = Session.getDefaultInstance(props, myauth);
	        // session.setDebug(true);
	        try{
	            // Define message
	            MimeMessage message = new MimeMessage(session);
	            // Set the from address
	            message.setFrom(new InternetAddress(account));
	            // Set the to address
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
	            // Set the subject
	            message.setSubject(subject);
	            // Set the content
	            //message.setText(content);
	            message.setContent(new String(content.getBytes(), "iso-8859-1"), "text/html");
	            message.saveChanges();
	            Transport.send(message);
	        }catch(Exception e){
	            e.printStackTrace();
	            return -1;
	        }
	        return 1;
	    }
	    
	    public static void main(String[] args) {
	    	StringBuilder sb = new StringBuilder();
	    	sb.append("请点击以下链接激活帐号（如不能点击请复制到浏览器地址栏打开）</br/>");
	    	sb.append("<a href=\"\">").append("百度").append("</a><br/>");
	    	sb.append("激活链接24小时有效");
	    	sendMail("304468211@qq.com", "test", sb.toString());
	    }	    

	}

	class MyAuthenticator extends Authenticator {
	    private String strUser;
	    private String strPwd;

	    public MyAuthenticator(String user, String password) {
	        this.strUser = user;
	        this.strPwd = password;
	    }

	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(strUser, strPwd);
	    }
	}

