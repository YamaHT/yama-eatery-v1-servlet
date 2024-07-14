/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.User;

import Data.DbContext;
import Data.Model.Account;
import Data.Model.Category;
import Data.Model.Delivery;
import Data.Model.Order;
import Data.Model.OrderDetail;
import Data.Model.Product;
import Data.Model.Shipping;
import Data.Model.Status;
import Utils.ImageUtils;
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

    public List<Order> getAllOrderByAccount(Account account) {
        List<Order> list = new ArrayList<>();
        String query = "SELECT [Order].*,\n"
                + "       Status.*,\n"
                + "       Shipping.*,\n"
                + "       Delivery.*\n"
                + "FROM [Order]\n"
                + "LEFT JOIN Shipping ON [Order].ShippingId = Shipping.Id\n"
                + "LEFT JOIN Status ON [Order].StatusId = Status.Id\n"
                + "LEFT JOIN Delivery ON Shipping.DeliveryId = Delivery.Id\n"
                + "WHERE ([Order].AccountId = ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query, account.getId());
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        account,
                        new Status(rs.getInt(8), rs.getString(9)),
                        new Shipping(rs.getInt(10),
                                rs.getString(11),
                                rs.getString(12),
                                rs.getString(13),
                                rs.getTimestamp(14),
                                new Delivery(rs.getInt(16),
                                        rs.getString(17),
                                        rs.getDouble(18),
                                        rs.getInt(19)))));
            }
        } catch (Exception e) {
            int a = 0;
        }
        return list;
    }

    public List<OrderDetail> getAllOrderDetailByOrder(Order order) {
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

                list.add(new OrderDetail(order, product, rs.getInt(3), rs.getDouble(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public OrderDetail getOrderDetailByOrderAndProduct(Order order, Product product) {
        String query = "SELECT *\n"
                + "FROM OrderDetail\n"
                + "WHERE (OrderId = ?)\n"
                + "  AND (ProductId = ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query, order.getId(), product.getId());
            while (rs.next()) {
                return new OrderDetail(order, new ProductRepository().getProductById(product.getId()),
                        rs.getInt(3), rs.getDouble(4));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Delivery> getAllDelivery() {
        List<Delivery> list = new ArrayList<>();
        String query = "SELECT * FROM Delivery";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            while (rs.next()) {
                list.add(new Delivery(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)));
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

    private void forCheckoutPurpose() {
    }

    //////////////////////////////////////////////////
    ////////////// FOR CHECKOUT PURPOSE //////////////
    ////////////// FOR CHECKOUT PURPOSE //////////////
    ////////////// FOR CHECKOUT PURPOSE //////////////
    //////////////////////////////////////////////////
    public void checkout(Order order, Shipping shipping) {
        String query = "UPDATE [Order]\n"
                + "SET Total = ?,\n"
                + "StatusId = 2,\n"
                + "OrderDate = ?,\n"
                + "ShippingId = ?\n"
                + "WHERE (Id = ?)";
        try {
            DbContext.executeUpdate(query,
                    order.getTotal() + shipping.getDelivery().getPrice(),
                    Timestamp.valueOf(LocalDateTime.now()),
                    shipping.getId(),
                    order.getId());
        } catch (Exception e) {
        }
    }

    public Shipping getShippingById(int shippingId) {
        String query = "SELECT *\n"
                + "FROM Shipping\n"
                + "INNER JOIN Delivery ON Shipping.DeliveryId = Delivery.Id\n"
                + "WHERE (Shipping.Id = ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query, shippingId);
            while (rs.next()) {
                return new Shipping(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5),
                        new Delivery(rs.getInt(7),
                                rs.getString(8),
                                rs.getDouble(9),
                                rs.getInt(10)));
            }
        } catch (Exception e) {
        }
        return null;
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
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Integer> deleteAllOrderDetailByProduct(Product product) {
        List<Integer> list = new ArrayList<>();
        String query = "DELETE\n"
                + "FROM OrderDetail OUTPUT deleted.OrderId\n"
                + "FROM OrderDetail\n"
                + "INNER JOIN [Order] ON OrderDetail.OrderId = [Order].Id\n"
                + "WHERE ([Order].StatusId = 1)\n"
                + "  AND (OrderDetail.ProductId = ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query, product.getId());
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Integer> updateAllOrderDetailByProduct(Product product) {
        List<Integer> list = new ArrayList<>();
        String query = "UPDATE OrderDetail\n"
                + "SET Amount = ?,\n"
                + "    Subtotal = ?\n"
                + "OUTPUT inserted.OrderId\n"
                + "FROM OrderDetail\n"
                + "INNER JOIN [Order] ON OrderDetail.OrderId = [Order].Id\n"
                + "INNER JOIN Product ON OrderDetail.ProductId = Product.Id\n"
                + "WHERE [Order].StatusId = 1\n"
                + "  AND Product.Id = ?\n"
                + "  AND OrderDetail.Amount > ?";
        try {
            ResultSet rs = DbContext.executeQuery(query,
                    product.getInventory(),
                    product.getInventory() * product.getPrice(),
                    product.getId(),
                    product.getInventory());
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void updateAllProductInventoryAfterCheckout(List<OrderDetail> list) {
        String query = "UPDATE Product\n"
                + "SET Inventory = ?\n"
                + "WHERE (Id = ?)";
        try {
            for (OrderDetail orderDetail : list) {
                Product product = orderDetail.getProduct();
                DbContext.executeUpdate(query,
                        product.getInventory() - orderDetail.getAmount(),
                        product.getId());
            }
        } catch (Exception e) {
        }
    }

    public void updateAllOrderAfterCheckout(List<OrderDetail> list) {
        try {
            for (OrderDetail orderDetail : list) {
                Product product = orderDetail.getProduct();
                List<Integer> listOrderId;

                if (product.getInventory() == 0) {
                    listOrderId = deleteAllOrderDetailByProduct(product);
                } else {
                    listOrderId = updateAllOrderDetailByProduct(product);
                }

                // Trả về mấy cái orderId mà cái orderDetail amount của nó bị thay đổi
                for (Integer orderId : listOrderId) {
                    Order order = new Order(orderId);
                    List<OrderDetail> listOrderDetail = getAllOrderDetailByOrder(order);
                    updateOrder(order, listOrderDetail);
                }
            }
        } catch (Exception e) {
        }
    }
}
