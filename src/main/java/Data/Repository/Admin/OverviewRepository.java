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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class OverviewRepository {

    public int lastMonth = LocalDate.now().minusMonths(1).getMonthValue();
    public int lastMonthYear = LocalDate.now().minusMonths(1).getYear();
    public int thisMonth = LocalDate.now().getMonthValue();
    public int thisMonthYear = LocalDate.now().getYear();

    public int getCountAccount() {
        String query = "SELECT COUNT(*) FROM Account";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public int getCountAccountLastMonth() {
        String query
                = "DECLARE @StartDate DATE;\n"
                + "DECLARE @EndDate DATE;\n"
                + ""
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + ""
                + "SELECT COUNT(*)\n"
                + "FROM Account\n"
                + "WHERE RegisterDate >= @StartDate\n"
                + "  AND RegisterDate <= @EndDate;";
        try {
            ResultSet rs = DbContext.executeQuery(query,
                    lastMonthYear,
                    lastMonth);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public int getProductSold() {
        String query = "SELECT SUM(Quantity)\n"
                + "FROM [Order]\n"
                + "WHERE StatusId = 3";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public int getProductSoldLastMonth() {
        String query
                = "DECLARE @StartDate DATE;\n"
                + "DECLARE @EndDate DATE;\n"
                + ""
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + ""
                + "SELECT SUM(Quantity)\n"
                + "FROM [Order]\n"
                + "WHERE StatusId = 3\n"
                + "  AND OrderDate >= @StartDate\n"
                + "  AND OrderDate <= @EndDate;";
        try {
            ResultSet rs = DbContext.executeQuery(query,
                    lastMonthYear,
                    lastMonth);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public int getProductSoldThisMonth() {
        String query
                = "DECLARE @StartDate DATE;\n"
                + "DECLARE @EndDate DATE;\n"
                + ""
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + ""
                + "SELECT SUM(Quantity)\n"
                + "FROM [Order]\n"
                + "WHERE StatusId = 3\n"
                + "  AND OrderDate >= @StartDate\n"
                + "  AND OrderDate <= @EndDate;";
        try {
            ResultSet rs = DbContext.executeQuery(query,
                    thisMonthYear,
                    thisMonth);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public Map<Product, Integer> getMostSoldProduct() {
        Map<Product, Integer> map = new LinkedHashMap<>();
        String query
                = "DECLARE @StartDate DATETIME;\n"
                + "DECLARE @EndDate DATETIME;\n"
                + ""
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + ""
                + "SELECT TOP (1) \n"
                + "    Product.Id,\n"
                + "    Product.Name,\n"
                + "    Product.Image,\n"
                + "    Product.Price,\n"
                + "    Category.Name AS CategoryName,\n"
                + "    SUM(OrderDetail.Amount) AS TotalSold\n"
                + "FROM OrderDetail\n"
                + "INNER JOIN Product ON OrderDetail.ProductId = Product.Id\n"
                + "INNER JOIN Category ON Product.CategoryId = Category.Id\n"
                + "INNER JOIN [Order] ON OrderDetail.OrderId = [Order].Id\n"
                + "WHERE [Order].StatusId = 3\n"
                + "AND [Order].OrderDate >= @StartDate\n"
                + "AND [Order].OrderDate <= @EndDate\n"
                + "GROUP BY \n"
                + "    Product.Id,\n"
                + "    Product.Name,\n"
                + "    Product.Image,\n"
                + "    Product.Price,\n"
                + "    Category.Name\n"
                + "ORDER BY TotalSold DESC;";
        try {
            ResultSet rs = DbContext.executeQuery(query,
                    thisMonthYear,
                    thisMonth);
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
        map.put(null, 0);
        return map;
    }

    public double getRevenue() {
        String query = "SELECT SUM(Total)\n"
                + "FROM [Order]\n"
                + "WHERE StatusId = 3";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            rs.next();
            return rs.getDouble(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public double getRevenueLastMonth() {
        String query
                = "DECLARE @StartDate DATE;\n"
                + "DECLARE @EndDate DATE;\n"
                + ""
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + ""
                + "SELECT SUM(Total)\n"
                + "FROM [Order]\n"
                + "WHERE StatusId = 3\n"
                + "  AND OrderDate >= @StartDate\n"
                + "  AND OrderDate <= @EndDate;";
        try {
            ResultSet rs = DbContext.executeQuery(query,
                    lastMonthYear,
                    lastMonth);
            rs.next();
            return rs.getDouble(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public double getRevenueThisMonth() {
        String query
                = "DECLARE @StartDate DATE;\n"
                + "DECLARE @EndDate DATE;\n"
                + ""
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + ""
                + "SELECT SUM(Total)\n"
                + "FROM [Order]\n"
                + "WHERE StatusId = 3\n"
                + "  AND OrderDate >= @StartDate\n"
                + "  AND OrderDate <= @EndDate;";
        try {
            ResultSet rs = DbContext.executeQuery(query,
                    thisMonthYear,
                    thisMonth);
            rs.next();
            return rs.getDouble(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Integer> getChartProduct(int productYear) {
        List<Integer> list = new ArrayList<>();
        String query
                = "DECLARE @StartDate DATE;\n"
                + "DECLARE @EndDate DATE;\n"
                + ""
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + ""
                + "SELECT SUM(Quantity)\n"
                + "FROM [Order]\n"
                + "WHERE StatusId = 3\n"
                + "  AND OrderDate >= @StartDate\n"
                + "  AND OrderDate <= @EndDate;";
        try {
            for (int month = 1; month <= 12; month++) {
                ResultSet rs = DbContext.executeQuery(query,
                        productYear,
                        month);
                rs.next();
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Double> getChartRevenue(int revenueYear) {
        List<Double> list = new ArrayList<>();
        String query
                = "DECLARE @StartDate DATE;\n"
                + "DECLARE @EndDate DATE;\n"
                + ""
                + "SET @StartDate = DATEFROMPARTS(?, ?, 1);\n"
                + "SET @EndDate = EOMONTH(@StartDate);\n"
                + ""
                + "SELECT SUM(Total)\n"
                + "FROM [Order]\n"
                + "WHERE StatusId = 3\n"
                + "  AND OrderDate >= @StartDate\n"
                + "  AND OrderDate <= @EndDate;";
        try {
            for (int month = 1; month <= 12; month++) {
                ResultSet rs = DbContext.executeQuery(query,
                        revenueYear,
                        month);
                rs.next();
                list.add(rs.getDouble(1));
            }
        } catch (Exception e) {
        }

        return list;
    }
}
