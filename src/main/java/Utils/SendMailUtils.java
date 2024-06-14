/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Data.Repository.Admin.ResourceRepository;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author ADMIN
 */
public class SendMailUtils {

    private void sendEmail(String toEmail, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("DuyLPCE181153@fpt.edu.vn", "srjwwkujkqkzhofq");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("DuyLPCE181153@fpt.edu.vn"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            e.getMessage();
        }
    }

    public String sendMailOTP(String email) {
        try {
            String OTP = RandomStringUtils.randomNumeric(6);
            String body = "<div style=\"background: #0FF4; height: 100vh; width: 100%; padding-top: 5%; font-family: 'Segoe UI';\">\n"
                    + "    <div\n"
                    + "        style=\"width: 50%; height: 90%; margin: 0 auto; box-shadow: 0 0 10px #000; background: linear-gradient(to top, #CCC, #FFF); border: 1px solid #000;\">\n"
                    + "        <div style=\"width: 100%;\">\n"
                    + "            <img src=\"https://img.upanh.tv/2024/06/12/brand.jpg\"\n"
                    + "                style=\"width: 100%; aspect-ratio: 2.75/1; object-fit: cover;\">\n"
                    + "        </div>\n"
                    + "        <div style=\"margin-top: 5%; padding: 0 5%;\">\n"
                    + "            <p style=\"font-size: 1.75rem; margin: 0;\">Hello,</p>\n"
                    + "            <br>\n"
                    + "            <p style=\"font-size: 1.75rem; margin: 0;\">Here is your security code to verify your account</p>\n"
                    + "            <h1\n"
                    + "                style=\"overflow-x: auto; background: #AAF8; border: 1px solid #0004; box-shadow: 0 0 5px #0004; color: #44F; text-shadow: 0 0 1px #000; padding: 3%; margin: 5% 0; font-size: 3rem; letter-spacing: 0.5rem;\">\n"
                    + "                " + OTP + "\n"
                    + "            </h1>\n"
                    + "            <p style=\"text-align: center; font-style: italic; font-size: 1.25rem; font-weight: 300; margin: 0;\">\n"
                    + "                Copyright © 2024 Yama, Inc. FPTU Can Tho\n"
                    + "            </p>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</div>";
            sendEmail(email, "Yama Eatery, Your OTP is " + OTP, body);
            return OTP;
        } catch (Exception e) {
        }
        return null;
    }

    public String sendMailPass(String email) {
        try {
            String password = RandomStringUtils.randomAlphanumeric(14);
            String body = "<div style=\"background: #0FF4; height: 100vh; width: 100%; padding-top: 5%; font-family: 'Segoe UI';\">\n"
                    + "    <div\n"
                    + "        style=\"width: 50%; height: 90%; margin: 0 auto; box-shadow: 0 0 10px #000; background: linear-gradient(to top, #CCC, #FFF); border: 1px solid #000;\">\n"
                    + "        <div style=\"width: 100%;\">\n"
                    + "            <img src=\"https://img.upanh.tv/2024/06/12/brand.jpg\"\n"
                    + "                style=\"width: 100%; aspect-ratio: 2.75/1; object-fit: cover;\">\n"
                    + "        </div>\n"
                    + "        <div style=\"margin-top: 5%; padding: 0 5%;\">\n"
                    + "            <p style=\"font-size: 1.75rem; margin: 0;\">Hello,</p>\n"
                    + "            <br>\n"
                    + "            <p style=\"font-size: 1.75rem; margin: 0;\">Here is your new password</p>\n"
                    + "            <h1\n"
                    + "                style=\"overflow-x: auto; background: #AAF8; border: 1px solid #0004; box-shadow: 0 0 5px #0004; color: #44F; text-shadow: 0 0 1px #000; padding: 3%; margin: 5% 0; font-size: 3rem; letter-spacing: 0.5rem;\">\n"
                    + "                " + password + "\n"
                    + "            </h1>\n"
                    + "            <p style=\"text-align: center; font-style: italic; font-size: 1.25rem; font-weight: 300; margin: 0;\">\n"
                    + "                Copyright © 2024 Yama, Inc. FPTU Can Tho\n"
                    + "            </p>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</div>";
            sendEmail(email, "Yama Eatery, Reset Password", body);
            return password;
        } catch (Exception e) {
        }
        return null;
    }
}
