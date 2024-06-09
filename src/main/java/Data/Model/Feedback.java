/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Feedback {

    private int id;
    private String feedback;
    private String response;
    private Date feedbackDate;
    private Date responseDate;
    private boolean ignored;
    private Account account;

    public Feedback(int id, String feedback, String response, Date feedbackDate, Date responseDate, boolean ignored, Account account) {
        this.id = id;
        this.feedback = feedback;
        this.response = response;
        this.feedbackDate = feedbackDate;
        this.responseDate = responseDate;
        this.ignored = ignored;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getResponse() {
        return response;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public boolean isIgnored() {
        return ignored;
    }

    public Account getAccount() {
        return account;
    }

}
