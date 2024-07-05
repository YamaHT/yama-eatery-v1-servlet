/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

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
            String body = "<div\n"
                    + "    style=\"color: #000; font-family: 'Segoe UI'; padding: 5% 10%; background: url('https://img.upanh.tv/2024/07/04/foodBackground.jpg'); background-repeat: no-repeat; background-size: cover;\">\n"
                    + "    <div\n"
                    + "        style=\"margin: 0 auto; width: 80%;height: max-content;border: 1px solid #000; background: url('https://img.upanh.tv/2024/07/04/hinh-anh_2024-07-04_202225706.png'); background-size: cover; background-repeat: no-repeat; background-position: right;\">\n"
                    + "        <div style=\"width: 100%; \">\n"
                    + "            <img src=\"https://img.upanh.tv/2024/06/12/brand.jpg\"\n"
                    + "                style=\"width: 100%; aspect-ratio: 2.75/1; object-fit: cover;\">\n"
                    + "        </div>\n"
                    + "        <div style=\"padding: 5%; \">\n"
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
            String body = "<div\n"
                    + "    style=\"color: #000; font-family: 'Segoe UI'; padding: 5% 10%; background: url('https://img.upanh.tv/2024/07/04/foodBackground.jpg'); background-repeat: no-repeat; background-size: cover;\">\n"
                    + "    <div\n"
                    + "        style=\"margin: 0 auto; width: 80%;height: max-content;border: 1px solid #000; background: url('https://img.upanh.tv/2024/07/04/hinh-anh_2024-07-04_202225706.png'); background-size: cover; background-repeat: no-repeat; background-position: right;\">\n"
                    + "        <div style=\"width: 100%; \">\n"
                    + "            <img src=\"https://img.upanh.tv/2024/06/12/brand.jpg\"\n"
                    + "                style=\"width: 100%; aspect-ratio: 2.75/1; object-fit: cover;\">\n"
                    + "        </div>\n"
                    + "        <div style=\"padding: 5%; \">\n"
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

    public String sendMailNormal(String email, String title, String message) {
        try {
            String password = RandomStringUtils.randomAlphanumeric(14);
            String body = "<div\n"
                    + "    style=\"color: #000; font-family: 'Segoe UI'; padding: 5% 10%; background: url('https://img.upanh.tv/2024/07/04/foodBackground.jpg'); background-repeat: no-repeat; background-size: cover;\">\n"
                    + "    <div\n"
                    + "        style=\"margin: 0 auto; width: 80%;height: max-content;border: 1px solid #000; background: url('https://img.upanh.tv/2024/07/04/hinh-anh_2024-07-04_202225706.png'); background-size: cover; background-repeat: no-repeat; background-position: right;\">\n"
                    + "        <div style=\"width: 100%; \">\n"
                    + "            <img src=\"https://img.upanh.tv/2024/06/12/brand.jpg\"\n"
                    + "                style=\"width: 100%; aspect-ratio: 2.75/1; object-fit: cover;\">\n"
                    + "        </div>\n"
                    + "        <div style=\"padding: 5%; \">\n"
                    + "            <p style=\"font-size: 1.75rem; margin: 0;\">" + message + "</p>\n"
                    + "            <p style=\"text-align: center; font-style: italic; font-size: 1.25rem; font-weight: 300; margin: 0;\">\n"
                    + "                Copyright © 2024 Yama, Inc. FPTU Can Tho\n"
                    + "            </p>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</div>";
            sendEmail(email, title, body);
            return password;
        } catch (Exception e) {
        }
        return null;
    }
}
