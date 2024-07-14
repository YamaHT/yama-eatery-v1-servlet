/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.Admin;

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

    public List<Feedback> getAllFeedback() {
        List<Feedback> list = new ArrayList<>();
        String query = "SELECT Feedback.*,\n"
                + "Profile.Name,\n"
                + "Profile.Image\n"
                + "FROM Feedback\n"
                + "INNER JOIN Account ON Feedback.AccountId = Account.Id\n"
                + "INNER JOIN PROFILE ON Account.ProfileId = Profile.Id\n"
                + "WHERE Feedback.Response IS NULL AND Feedback.Ignored = 0\n"
                + "ORDER BY Feedback.FeedbackDate ASC";
        try {
            ResultSet rs = DbContext.executeQuery(query);
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

    public void response(int feedbackId, String response) {
        String query = "UPDATE Feedback SET Response = ?, ResponseDate = ? WHERE Id = ?";
        try {
            DbContext.executeUpdate(query,
                    response,
                    new Timestamp(System.currentTimeMillis()),
                    feedbackId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ignore(int feedbackId) {
        String query = "UPDATE Feedback SET Ignored = 1 WHERE Id = ?";
        try {
            DbContext.executeUpdate(query, feedbackId);
        } catch (Exception e) {
        }
    }
}
