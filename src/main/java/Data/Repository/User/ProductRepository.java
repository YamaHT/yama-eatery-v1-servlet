/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.User;

import Data.DbContext;
import Data.Model.Category;
import Data.Model.Product;
import Utils.ImageUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProduct(int page, String filter, String minPrice, String maxPrice) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category ON Product.CategoryId = Category.Id "
                + "\nWHERE Product.Available = 1";

        if (minPrice != null && maxPrice != null) {
            query += " AND Product.Price >= " + minPrice + " AND Product.Price <= " + maxPrice;
        }
        query += (filter == null || filter.equals("")) ? "\nORDER BY Product.Id DESC" : "\nORDER BY Product." + filter;
        if (page != -1) {
            query += "\nOFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        }
        try ( ResultSet rs = (page != -1)
                ? DbContext.executeQuery(query, (page - 1) * 12)
                : DbContext.executeQuery(query)) {
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

    public List<Product> getAllProductBySearchName(String name, int page, String filter, String minPrice, String maxPrice) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category ON Product.CategoryId = Category.Id "
                + "\nWHERE Product.Name LIKE '%" + name + "%'"
                + "\nAND Product.Available = 1";

        if (minPrice != null && maxPrice != null) {
            query += " AND Product.Price >= " + minPrice + " AND Product.Price <= " + maxPrice;
        }
        query += (filter == null || filter.equals("")) ? "\nORDER BY Product.Id DESC" : "\nORDER BY Product." + filter;
        if (page != -1) {
            query += "\nOFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        }
        try ( ResultSet rs = (page != -1)
                ? DbContext.executeQuery(query, (page - 1) * 12)
                : DbContext.executeQuery(query)) {
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

    public List<Product> getAllProductByCategoryName(String categoryName, int page, String filter, String minPrice, String maxPrice) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category ON Product.CategoryId = Category.Id "
                + "\nWHERE Category.Name = ?"
                + "\nAND Product.Available = 1";

        if (minPrice != null && maxPrice != null) {
            query += " AND Product.Price >= " + minPrice + " AND Product.Price <= " + maxPrice;
        }
        query += (filter == null || filter.equals("")) ? "\nORDER BY Product.Id DESC" : "\nORDER BY Product." + filter;
        if (page != -1) {
            query += "\nOFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        }
        try ( ResultSet rs = (page != -1)
                ? DbContext.executeQuery(query, categoryName, (page - 1) * 12)
                : DbContext.executeQuery(query, categoryName)) {
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
            e.getMessage();
        }
        return list;
    }

    public Product getProductById(int id) {
        String query = "SELECT Product.*,\n"
                + "       Category.*\n"
                + "FROM Product\n"
                + "INNER JOIN Category ON Product.CategoryId = Category.Id\n"
                + "WHERE (Product.Id = ?)";
        try {
            ResultSet rs = DbContext.executeQuery(query, id);
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        ImageUtils.decompressImage(rs.getBytes(3)),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBoolean(7),
                        new Category(rs.getInt(9), rs.getString(10)));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int getCountProduct(String action, String name, String minPrice, String maxPrice) {
        String query = "SELECT COUNT(*) FROM Product";
        switch (String.valueOf(action)) {
            case "null":
                query += "\nWHERE Product.Available = 1";
                break;
            case "search":
                query += "\nWHERE Product.Name LIKE '%" + name + "%'"
                        + "\nAND Product.Available = 1";
                break;
            case "category":
                query += "\nINNER JOIN Category ON Product.CategoryId = Category.Id "
                        + "\nWHERE Category.Name = ?"
                        + "\nAND Product.Available = 1";
                break;
        }

        if (minPrice != null && maxPrice != null) {
            query += " AND Product.Price >= " + minPrice + " AND Product.Price <= " + maxPrice;
        }
        try {
            ResultSet rs = DbContext.executeQuery(query, name);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public int getMinPriceProduct(String action, String name) {
        String query = "SELECT TOP 1 Product.Price FROM Product";
        switch (String.valueOf(action)) {
            case "null":
                query += "\nWHERE Product.Available = 1";
                break;
            case "search":
                query += "\nWHERE Product.Name LIKE '%" + name + "%'"
                        + "\nAND Product.Available = 1";
                break;
            case "category":
                query += "\nINNER JOIN Category ON Product.CategoryId = Category.Id "
                        + "\nWHERE Category.Name = ?"
                        + "\nAND Product.Available = 1";
                break;
        }
        query += "\nOrder By Product.Price ASC";
        try {
            ResultSet rs = DbContext.executeQuery(query, name);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public int getMaxPriceProduct(String action, String name) {
        String query = "SELECT TOP 1 Product.Price FROM Product";
        switch (String.valueOf(action)) {
            case "null":
                query += "\nWHERE Product.Available = 1";
                break;
            case "search":
                query += "\nWHERE Product.Name LIKE '%" + name + "%'"
                        + "\nAND Product.Available = 1";
                break;
            case "category":
                query += "\nINNER JOIN Category ON Product.CategoryId = Category.Id "
                        + "\nWHERE Category.Name = ?"
                        + "\nAND Product.Available = 1";
                break;
        }
        query += "\nOrder By Product.Price DESC";
        try {
            ResultSet rs = DbContext.executeQuery(query, name);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }
}
