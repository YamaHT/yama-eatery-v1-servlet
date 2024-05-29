/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class DbContext {
     public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301_SE1804_Group1_Project";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, "sa", "sa");
    }

    public static void main(String[] args) {
        try {
            System.out.println(new DbContext().getConnection());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
