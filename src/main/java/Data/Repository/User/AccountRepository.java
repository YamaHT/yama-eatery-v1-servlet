/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.User;

import Data.DbContext;
import Data.Model.Account;
import Data.Model.Profile;
import Utils.ImageUtils;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class AccountRepository {

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
            ResultSet rs = DbContext.executeQuery(query, username, password);
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

    public void register(String username, String email, String password, int profileId) {
        String query = "INSERT INTO [dbo].[Account]\n"
                + "VALUES (?, ?, ?, ?, 0, ?)";
        try {
            DbContext.executeUpdate(query,
                    username,
                    email,
                    password,
                    Date.valueOf(LocalDate.now()),
                    profileId);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public Account getAccountById(int accountId) {
        String query = "SELECT Account.*,\n"
                + "       Profile.*\n"
                + "FROM Account\n"
                + "INNER JOIN PROFILE ON Account.ProfileId = Profile.Id\n"
                + "WHERE (Account.Id = ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query, accountId);
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

    public boolean checkAccountExisted(String username) {
        String query = "SELECT *\n"
                + "FROM Account\n"
                + "INNER JOIN PROFILE ON Account.ProfileId = Profile.Id\n"
                + "WHERE (Account.Username = ?)";
        if (username.contains("@")) {
            query = "SELECT *\n"
                    + "FROM Account\n"
                    + "INNER JOIN PROFILE ON Account.ProfileId = Profile.Id\n"
                    + "WHERE (Account.Email = ?)";
        }
        try {
            return DbContext.executeQuery(query, username).next();
        } catch (Exception e) {
        }
        return true;
    }

    public void changePassword(String email, String password) {
        String query = "UPDATE Account\n"
                + "SET Password = ?\n"
                + "WHERE (Email = ?)";
        try {
            DbContext.executeUpdate(query, email, password);
        } catch (Exception e) {
        }
    }

    public int addProfile() {
        String query = "INSERT INTO [dbo].[Profile]\n"
                + "OUTPUT inserted.Id\n"
                + "VALUES (NULL, '1900-01-01', NULL, NULL, NULL)";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public void updateProfile(Account account, byte[] image, String birthday, String name, String phone, String address) {
        try {
            if (image != null) {
                String query = "UPDATE [dbo].[Profile]\n"
                        + "SET [Image] = ? ,\n"
                        + "    [Birthday] = ? ,\n"
                        + "    [Name] = ? ,\n"
                        + "    [Phone] = ? ,\n"
                        + "    [Address] = ?\n"
                        + "WHERE Id = ?";
                DbContext.executeUpdate(query,
                        image,
                        new Date(new SimpleDateFormat("dd-MM-yyyy").parse(birthday).getTime()),
                        name,
                        phone,
                        address,
                        account.getProfile().getId());
            } else {
                String query = "UPDATE [dbo].[Profile]\n"
                        + "SET [Birthday] = ? ,\n"
                        + "    [Name] = ? ,\n"
                        + "    [Phone] = ? ,\n"
                        + "    [Address] = ?\n"
                        + "WHERE Id = ?";
                DbContext.executeUpdate(query,
                        new Date(new SimpleDateFormat("dd-MM-yyyy").parse(birthday).getTime()),
                        name,
                        phone,
                        address,
                        account.getProfile().getId());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
