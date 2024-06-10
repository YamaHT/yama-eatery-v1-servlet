/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.Admin;

import Data.DbContext;
import Data.Model.Account;
import Data.Model.Order;
import Data.Model.Shipping;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class OrderRepository {

    public List<Order> getWaitingOrder() {
        List<Order> list = new ArrayList<>();
        String query = "SELECT [Order].*, Account.Username, Shipping.Phone, Shipping.Address\n"
                + "FROM [Order]\n"
                + "INNER JOIN Account ON [Order].AccountId = Account.Id\n"
                + "INNER JOIN Shipping ON [Order].ShippingId = Shipping.Id\n"
                + "WHERE ([Order].StatusId = 2)";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            while (rs.next()) {
                Account acc = new Account(rs.getInt(5), rs.getString(8), null, null, null, false, null);
                Shipping ship = new Shipping(rs.getInt(7), rs.getString(9), rs.getString(10), null, null, null);
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        acc,
                        null,
                        ship));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void acceptOrder(int id) {
        String query1 = "UPDATE [Order]\n"
                + "SET StatusId = 3\n"
                + "WHERE (Id = ?)";
        String query2 = "UPDATE Shipping\n"
                + "SET DeliveryDate = ?\n"
                + "FROM Shipping\n"
                + "INNER JOIN [Order] ON Shipping.Id = [Order].ShippingId\n"
                + "WHERE ([Order].Id = ?)";
        try {
            DbContext.executeUpdate(query1, id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp.getTime());
            int deliveryTime = getDeliveryTime(id);
            calendar.add(Calendar.MINUTE, deliveryTime);
            timestamp = new Timestamp(calendar.getTimeInMillis());

            DbContext.executeUpdate(query2, timestamp, id);
        } catch (Exception e) {
        }
    }

    public void refuseOrder(int id) {
        String query = "UPDATE [Order]\n"
                + "SET StatusId = 4\n"
                + "WHERE (Id = ?)";
        try {
            DbContext.executeUpdate(query, id);
        } catch (Exception e) {
        }
    }

    public int getDeliveryTime(int orderId) {
        String query = "SELECT Delivery.Time\n"
                + "FROM Delivery\n"
                + "INNER JOIN Shipping ON Delivery.Id = Shipping.DeliveryId\n"
                + "INNER JOIN [Order] ON Shipping.Id = [Order].ShippingId\n"
                + "WHERE ([Order].Id = ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query, orderId);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
