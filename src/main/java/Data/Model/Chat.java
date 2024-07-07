/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

/**
 *
 * @author ADMIN
 */
public class Chat {

    private int id;
    private String message;
    private Account account;

    public Chat(int id, String message, Account account) {
        this.id = id;
        this.message = message;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Account getAccount() {
        return account;
    }

}
