/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.User;

import Data.DbContext;
import Data.Model.Account;
import Data.Model.Category;
import Data.Model.Order;
import Data.Model.OrderDetail;
import Data.Model.Product;
import Utils.ImageUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class OrderRepository {

    public Order getOrderByAccount(Account account) {
        String query = "SELECT [Order].*\n"
                + "FROM [Order]\n"
                + "WHERE (AccountId = ?)\n"
                + "  AND (StatusId = 1)";
        try {
            ResultSet rs = DbContext.executeQuery(query, account.getId());
            while (rs.next()) {
                return new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        account,
                        null,
                        null);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<OrderDetail> getOrderDetailByOrder(Order order) {
        List<OrderDetail> list = new ArrayList<>();
        String query = "SELECT OrderDetail.*,\n"
                + "       Product.*,\n"
                + "       Category.*\n"
                + "FROM OrderDetail\n"
                + "INNER JOIN Product ON OrderDetail.ProductId = Product.Id\n"
                + "INNER JOIN Category ON Product.CategoryId = Category.Id\n"
                + "WHERE (OrderDetail.OrderId = ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query, order.getId());
            while (rs.next()) {
                Product product = new Product(rs.getInt(5),
                        rs.getString(6),
                        ImageUtils.decompressImage(rs.getBytes(7)),
                        rs.getDouble(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getBoolean(11),
                        new Category(rs.getInt(13), rs.getString(14)));

                list.add(new OrderDetail(order, product, rs.getInt(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int createOrder(Account account) {
        String query = "INSERT INTO [Order]\n"
                + "OUTPUT INSERTED.Id\n"
                + "VALUES (0, 0, NULL, ?, 1, NULL)";
        try {
            ResultSet rs = DbContext.executeQuery(query, account.getId());
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void updateOrder(Order order, List<OrderDetail> list) {
        String query = "UPDATE [Order]\n"
                + "SET Quantity = ?,\n"
                + "    Total = ?\n"
                + "WHERE (Id = ?)";
        try {
            double total = 0;
            int amount = 0;
            for (OrderDetail orderDetail : list) {
                total += orderDetail.getSubtotal();
                amount += orderDetail.getAmount();
            }
            DbContext.executeUpdate(query,
                    amount,
                    total,
                    order.getId());
        } catch (Exception e) {
        }
    }

    public void addOrderDetail(Order order, Product product, int amount) {
        String query = "INSERT INTO OrderDetail VALUES (?, ?, ?, ?)";
        try {
            DbContext.executeUpdate(query,
                    order.getId(),
                    product.getId(),
                    amount,
                    amount * product.getPrice());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateOrderDetail(Order order, Product product, int amount) {
        String query = "UPDATE OrderDetail\n"
                + "SET Amount = ?,\n"
                + "    Subtotal = ?\n"
                + "WHERE (OrderId = ?)\n"
                + "  AND (ProductId = ?)";
        try {
            DbContext.executeUpdate(query,
                    amount,
                    amount * product.getPrice(),
                    order.getId(),
                    product.getId());
        } catch (Exception e) {
        }
    }

    public void deleteOrderDetail(int orderId, int productId) {
        String query = "DELETE\n"
                + "FROM OrderDetail\n"
                + "WHERE (OrderId = ?)\n"
                + "  AND (ProductId = ?)";
        try {
            DbContext.executeUpdate(query,
                    orderId,
                    productId);
        } catch (Exception e) {
        }
    }

    public int addShipping(String recipientName, String phone, String address, int deliveryId) {
        String query = "INSERT INTO Shipping\n"
                + "OUTPUT INSERTED.Id\n"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query,
                    recipientName,
                    phone,
                    address,
                    null,
                    deliveryId);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void checkout(Order order, int shippingId) {
        if (shippingId == 0) {
            return;
        }
        String query = "UPDATE [Order]\n"
                + "SET StatusId = 2,\n"
                + "OrderDate = ?,"
                + "ShippingId = ?\n"
                + "WHERE (Id = ?)";
        try {
            DbContext.executeUpdate(query,
                    Timestamp.valueOf(LocalDateTime.now()),
                    shippingId,
                    order.getId());
        } catch (Exception e) {
        }
    }
}
