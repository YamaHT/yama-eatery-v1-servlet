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
public class Account {
    private int id;
    private String username;
    private String email;
    private String password;
    private Date registerDate;
    private boolean role;
    private Profile profile;

    public Account(int id, String username, String email, String password, Date registerDate, boolean role, Profile profile) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.registerDate = registerDate;
        this.role = role;
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public boolean isRole() {
        return role;
    }

    public Profile getProfile() {
        return profile;
    }

   
    
}
