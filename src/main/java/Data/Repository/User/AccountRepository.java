/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.User;

import Data.DbContext;
import Data.Model.Account;
import Data.Model.Profile;
import Utils.ImageUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class AccountRepository {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public Account login(String username, String password) {
        String query = "SELECT *\n"
                + "FROM Account\n"
                + "INNER JOIN PROFILE ON Account.ProfileId = Profile.Id\n"
                + "WHERE (Account.Username = ?)\n"
                + "  AND (Account.Password = ? COLLATE SQL_Latin1_General_CP1_CS_AS)";
        if (username.contains("@")) {
            query = "SELECT *\n"
                    + "FROM Account\n"
                    + "INNER JOIN PROFILE ON Account.ProfileId = Profile.Id\n"
                    + "WHERE (Account.Email = ?)\n"
                    + "  AND (Account.Password = ? COLLATE SQL_Latin1_General_CP1_CS_AS)";
        }
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5),
                        rs.getBoolean(6),
                        new Profile(rs.getInt(8),
                                ImageUtils.decompressImage(rs.getBytes(9)),
                                rs.getTimestamp(10),
                                rs.getString(11),
                                rs.getString(12),
                                rs.getString(13)));

            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account getAccountById(int accountId) {
        String query = "SELECT Account.*,\n"
                + "       Profile.*\n"
                + "FROM Account\n"
                + "INNER JOIN PROFILE ON Account.ProfileId = Profile.Id\n"
                + "WHERE (Account.Id = ?)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5),
                        rs.getBoolean(6),
                        new Profile(rs.getInt(8),
                                ImageUtils.decompressImage(rs.getBytes(9)),
                                rs.getTimestamp(10),
                                rs.getString(11),
                                rs.getString(12),
                                rs.getString(13)));

            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateProfile(Account account, byte[] image, String birthday, String name, String phone, String address) {
        String query = "UPDATE [dbo].[Profile]\n"
                + "SET [Birthday] = ? ,\n"
                + "    [Name] = ? ,\n"
                + "    [Phone] = ? ,\n"
                + "    [Address] = ?\n";
        if (image != null) {
            query += ", [Image] = ?\n";
        }
        query += "WHERE Id = " + account.getProfile().getId();
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setDate(1, new Date(new SimpleDateFormat("dd-MM-yyyy").parse(birthday).getTime()));
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setString(4, address);
            if (image != null) {
                ps.setBytes(5, ImageUtils.compressImageFromWinform(image));
            }
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
