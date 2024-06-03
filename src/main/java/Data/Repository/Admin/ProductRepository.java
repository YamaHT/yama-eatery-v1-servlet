/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.Admin;

import Data.DbContext;
import Data.Model.Category;
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
public class ProductRepository {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM Category";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product INNER JOIN Category ON Product.CategoryId = Category.Id WHERE Product.Available = 1\n"
                + "ORDER BY Product.Id DESC";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        ImageUtils.decompressImage(rs.getBytes(3)),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBoolean(7),
                        new Category(rs.getInt(9), rs.getString(10))
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProductSearchByName(String name) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category "
                + "ON Product.CategoryId = Category.Id "
                + "WHERE Product.Name LIKE '%" + name + "%'"
                + "AND Product.Available = 1\n"
                + "ORDER BY Product.Id DESC";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        ImageUtils.decompressImage(rs.getBytes(3)),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBoolean(7),
                        new Category(rs.getInt(9), rs.getString(10))
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProductSearchByCategoryName(String categoryName) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category "
                + "ON Product.CategoryId = Category.Id "
                + "WHERE Category.Name = ? "
                + "AND Product.Available = 1\n"
                + "ORDER BY Product.Id DESC";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        ImageUtils.decompressImage(rs.getBytes(3)),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBoolean(7),
                        new Category(rs.getInt(9), rs.getString(10))
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProductSearchByPrice(double price) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category "
                + "ON Product.CategoryId = Category.Id "
                + "WHERE Product.Price <= ? "
                + "AND Product.Available = 1"
                + "ORDER BY Product.Price DESC";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setDouble(1, price);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        ImageUtils.decompressImage(rs.getBytes(3)),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBoolean(7),
                        new Category(rs.getInt(9), rs.getString(10))
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Object> getMostSoldInMonth(int month, int year) {
        List<Object> list = new ArrayList<>();
        String query
                = "DECLARE @StartDate DATETIME;\n"
                + "DECLARE @EndDate DATETIME;\n"
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + ""
                + "SELECT TOP (1) Product.Id,\n"
                + "       Product.Name,\n"
                + "       Product.Image,\n"
                + "       Product.Price,\n"
                + "       Category.Name AS CategoryName,\n"
                + "       SUM(OrderDetail.Amount) AS TotalSold\n"
                + "FROM OrderDetail\n"
                + "INNER JOIN Product ON OrderDetail.ProductId = Product.Id\n"
                + "INNER JOIN Category ON Product.CategoryId = Category.Id\n"
                + "INNER JOIN [Order] ON OrderDetail.OrderId = [Order].Id\n"
                + "GROUP BY Product.Id,\n"
                + "         Product.Name,\n"
                + "         Product.Image,\n"
                + "         Product.Price,\n"
                + "         Category.Name,\n"
                + "         [Order].OrderDate\n"
                + "HAVING ([Order].OrderDate >= @StartDate)\n"
                + "AND ([Order].OrderDate <= @EndDate)\n"
                + "ORDER BY TotalSold DESC";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, year);
            ps.setInt(2, month);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        ImageUtils.decompressImage(rs.getBytes(3)),
                        rs.getDouble(4),
                        null,
                        0,
                        false,
                        new Category(0, rs.getString(5))));
                list.add(rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getProductSoldInMonth(int month, int year) {
        String query = "DECLARE @StartDate DATETIME;\n"
                + "DECLARE @EndDate DATETIME;\n"
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + "SELECT Quantity\n"
                + "FROM [Order]\n"
                + "WHERE (OrderDate >= @StartDate) AND (OrderDate <= @EndDate)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, year);
            ps.setInt(2, month);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double getRevenueInMonth(int month, int year) {
        String query = "DECLARE @StartDate DATETIME;\n"
                + "DECLARE @EndDate DATETIME;\n"
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + "SELECT Total\n"
                + "FROM [Order]\n"
                + "WHERE (OrderDate >= @StartDate) AND (OrderDate <= @EndDate)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, year);
            ps.setInt(2, month);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void addProduct(String name, byte[] image, double price, String description, int inventory, int categoryId) {
        String query = "INSERT INTO [dbo].[Product] VALUES (?,?,?,?,?, 1,?)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setBytes(2, ImageUtils.compressImageFromWinform(image));
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setInt(5, inventory);
            ps.setInt(6, categoryId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateProduct(int id, String name, byte[] image, double price, String description, int inventory, int categoryId) {
        String query = "UPDATE [dbo].[Product] SET [Name] = ?, [Image] = ?, [Price] = ?, [Description] = ?, [Inventory] = ?, [CategoryId] = ? WHERE Id = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setBytes(2, ImageUtils.compressImageFromWinform(image));
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setInt(5, inventory);
            ps.setInt(6, categoryId);
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateProductWithoutImage(int id, String name, double price, String description, int inventory, int categoryId) {
        String query = "UPDATE [dbo].[Product] SET [Name] = ?, [Price] = ?, [Description] = ?, [Inventory] = ?, [CategoryId] = ? WHERE Id = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, description);
            ps.setInt(4, inventory);
            ps.setInt(5, categoryId);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProduct(int id) {
        String query = "UPDATE [dbo].[Product] SET [Available] = 0 WHERE Id = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
