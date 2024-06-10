/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.Admin;

import Data.DbContext;
import Data.Model.Category;
import Data.Model.Product;
import Utils.ImageUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class ProductRepository {

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM Category";
        try {
            ResultSet rs = DbContext.executeQuery(query);
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
            ResultSet rs = DbContext.executeQuery(query);
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
            ResultSet rs = DbContext.executeQuery(query);
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
            ResultSet rs = DbContext.executeQuery(query, categoryName);
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
        String query = "SELECT * FROM\n"
                + "Product INNER JOIN Categoryn"
                + "ON Product.CategoryId = Category.Id\n"
                + "WHERE Product.Price <= ?\n"
                + "AND Product.Available = 1\n"
                + "ORDER BY Product.Price DESC";
        try {
            ResultSet rs = DbContext.executeQuery(query, price);
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

    public Map<Product, Integer> getMostSoldInMonth(int month, int year) {
        Map<Product, Integer> map = new HashMap<>();
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
            ResultSet rs = DbContext.executeQuery(query, year, month);
            while (rs.next()) {
                Product product = new Product(rs.getInt(1),
                        rs.getString(2),
                        ImageUtils.decompressImage(rs.getBytes(3)),
                        rs.getDouble(4),
                        null,
                        0,
                        false,
                        new Category(0, rs.getString(5)));
                map.put(product, rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return map;
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
            ResultSet rs = DbContext.executeQuery(query, year, month);
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
            ResultSet rs = DbContext.executeQuery(query, year, month);
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
            DbContext.executeUpdate(query,
                    name,
                    image,
                    price,
                    description,
                    inventory,
                    categoryId);
        } catch (Exception e) {
        }
    }

    public void updateProduct(int id, String name, byte[] image, double price, String description, int inventory, int categoryId) {
        try {
            if (image != null) {
                String query = "UPDATE [dbo].[Product] SET [Name] = ?, [Image] = ?, [Price] = ?, [Description] = ?, [Inventory] = ?, [CategoryId] = ?\n"
                        + "WHERE Id = ?";
                DbContext.executeUpdate(query,
                        name,
                        image,
                        price,
                        description,
                        inventory,
                        categoryId,
                        id);
            } else {
                String query = "UPDATE [dbo].[Product] SET [Name] = ?, [Price] = ?, [Description] = ?, [Inventory] = ?, [CategoryId] = ? WHERE Id = ?";
                DbContext.executeUpdate(query,
                        name,
                        price,
                        description,
                        inventory,
                        categoryId,
                        id);
            }
        } catch (Exception e) {
        }
    }

    public void deleteProduct(int id) {
        String query = "UPDATE [dbo].[Product] SET [Available] = 0 WHERE Id = ?";
        try {
            DbContext.executeUpdate(query, id);
        } catch (Exception e) {
        }
    }
}
