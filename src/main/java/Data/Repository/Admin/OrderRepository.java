/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.Admin;

import Data.DbContext;
import Data.Model.Account;
import Data.Model.Order;
import Data.Model.Shipping;
import Data.Model.Status;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class OrderRepository {

    public List<Order> getWaitingOrder() {
        List<Order> list = new ArrayList<>();
        String query = "SELECT [Order].*, Account.Email, Shipping.RecipientName, Shipping.Phone, Shipping.Address\n"
                + "FROM [Order]\n"
                + "INNER JOIN Account ON [Order].AccountId = Account.Id\n"
                + "INNER JOIN Shipping ON [Order].ShippingId = Shipping.Id\n"
                + "WHERE ([Order].StatusId = 2)";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            while (rs.next()) {
                Account acc = new Account(rs.getInt(5), null, rs.getString(8), null, null, false, null);
                Shipping ship = new Shipping(rs.getInt(7), rs.getString(9), rs.getString(10), rs.getString(11), null, null);
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        acc,
                        new Status(rs.getInt(6), null),
                        ship));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void acceptOrder(int id) {
        String queryToUpdateOrder = "UPDATE [Order]\n"
                + "SET StatusId = 3\n"
                + "WHERE (Id = ?)";
        String queryToUpdateShipping = "UPDATE Shipping\n"
                + "SET DeliveryDate = ?\n"
                + "FROM Shipping\n"
                + "INNER JOIN [Order] ON Shipping.Id = [Order].ShippingId\n"
                + "WHERE ([Order].Id = ?)";
        try {
            DbContext.executeUpdate(queryToUpdateOrder, id);

            int deliveryTime = this.getDeliveryTimeByOrderId(id);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis() + deliveryTime * 60000);

            DbContext.executeUpdate(queryToUpdateShipping, timestamp, id);
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

    public void refuseAllOrder() {
        String query = "UPDATE [Order]\n"
                + "SET StatusId = 4\n"
                + "WHERE StatusId = 2";
        try {
            DbContext.executeUpdate(query);
        } catch (Exception e) {
        }
    }

    public int getDeliveryTimeByOrderId(int orderId) {
        String query = "SELECT Delivery.Time\n"
                + "FROM Delivery\n"
                + "INNER JOIN Shipping ON Delivery.Id = Shipping.DeliveryId\n"
                + "INNER JOIN [Order] ON Shipping.Id = [Order].ShippingId\n"
                + "WHERE ([Order].Id = ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query, orderId);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }
}
