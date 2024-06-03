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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class OrderRepository {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public OrderRepository() {
    }
    
    public Order getOrderByAccount(Account account) {
        String query = "SELECT [Order].*\n"
                + "FROM [Order]\n"
                + "WHERE (AccountId = ?)\n"
                + "  AND (StatusId = 1)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, account.getId());
            rs = ps.executeQuery();
            System.out.println(rs.next());
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
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, order.getId());
            rs = ps.executeQuery();
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
}
