/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class DbContext {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301_SE1804_Group1_Project";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, "sa", "sa");
    }

    public static void setParameters(PreparedStatement ps, Object... params) {
        try {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        } catch (Exception e) {
        }
    }

    public static int executeUpdate(String query, Object... params) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            setParameters(ps, params);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public static ResultSet executeQuery(String query, Object... params) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            setParameters(ps, params);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
}
