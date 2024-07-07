/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.User;

import Data.DbContext;
import Data.Model.Account;
import Data.Model.Feedback;
import Data.Model.Profile;
import Utils.ImageUtils;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class FeedbackRepository {

    public List<Feedback> getAllFeedback(int page) {
        List<Feedback> list = new ArrayList<>();
        String query = "SELECT Feedback.*,\n"
                + "       Profile.Name,\n"
                + "       Profile.Image\n"
                + "FROM Feedback\n"
                + "INNER JOIN Account ON Feedback.AccountId = Account.Id\n"
                + "INNER JOIN PROFILE ON Account.ProfileId = Profile.Id\n"
                + "ORDER BY Feedback.FeedbackDate DESC";
        if (page != -1) {
            query += "\nOFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
        }
        try {
            ResultSet rs = DbContext.executeQuery(query, (page - 1) * 10);
            while (rs.next()) {
                list.add(new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        rs.getTimestamp(5),
                        rs.getBoolean(6),
                        new Account(rs.getInt(7), null, null, null, null, false,
                                new Profile(0, ImageUtils.decompressImage(rs.getBytes(9)),
                                        null, rs.getString(8), null, null))));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Feedback> getAllFeedbackByAccount(Account account) {
        List<Feedback> list = new ArrayList<>();
        String query = "SELECT Feedback.*,\n"
                + "       Profile.Name,\n"
                + "       Profile.Image\n"
                + "FROM Feedback\n"
                + "INNER JOIN Account ON Feedback.AccountId = Account.Id\n"
                + "INNER JOIN PROFILE ON Account.ProfileId = Profile.Id\n"
                + "WHERE Account.Id = ?\n"
                + "ORDER BY Feedback.FeedbackDate DESC";
        try {
            ResultSet rs = DbContext.executeQuery(query, account.getId());
            while (rs.next()) {
                list.add(new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        rs.getTimestamp(5),
                        rs.getBoolean(6),
                        new Account(rs.getInt(7), null, null, null, null, false,
                                new Profile(0, ImageUtils.decompressImage(rs.getBytes(9)),
                                        null, rs.getString(8), null, null))));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void sendFeedback(Account account, String feedback) {
        String query = "INSERT INTO Feedback\n"
                + "VALUES (?, NULL, ?, NULL, 0, ?)";
        try {
            DbContext.executeUpdate(query,
                    feedback,
                    new Timestamp(System.currentTimeMillis()),
                    account.getId());
        } catch (Exception e) {
        }
    }

    public int getCountFeedback() {
        String query = "SELECT COUNT(*) FROM Feedback";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

}
