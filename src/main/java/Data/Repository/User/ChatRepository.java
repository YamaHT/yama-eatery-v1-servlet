/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.User;

import Data.DbContext;
import Data.Model.Account;
import Data.Model.Chat;
import Data.Model.Profile;
import Utils.ImageUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChatRepository {

    public List<Chat> getListChat(int index) {
        List<Chat> list = new ArrayList<>();
        String query = "SELECT Chat.Id,\n"
                + "       Chat.Message,\n"
                + "       Chat.AccountId,\n"
                + "       Account.Role,\n"
                + "       Profile.Name,\n"
                + "       Profile.Image\n"
                + "FROM Chat\n"
                + "INNER JOIN Account ON Chat.AccountId = Account.Id\n"
                + "INNER JOIN Profile ON Account.ProfileId = Profile.Id\n"
                + "ORDER BY Chat.Id DESC\n"
                + "OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";
        try {
            ResultSet rs = DbContext.executeQuery(query, index * 10);
            while (rs.next()) {
                list.add(new Chat(rs.getInt(1),
                        rs.getString(2),
                        new Account(rs.getInt(3), null, null, null, null, rs.getBoolean(4),
                                new Profile(0, ImageUtils.decompressImage(rs.getBytes(6)), null, rs.getString(5), null, null))));
            }
        } catch (Exception e) {
        }
        Collections.reverse(list);
        return list;
    }

    public void addChat(String message, Account account) {
        String query = "INSERT INTO Chat VALUES(?, ?)";
        try {
            DbContext.executeUpdate(query, message, account.getId());
        } catch (Exception e) {
        }
    }
}
