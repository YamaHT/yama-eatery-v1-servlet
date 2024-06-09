/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.User;

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

    public List<Product> getAllProduct(int page, String filter, String minPrice, String maxPrice) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product INNER JOIN Category ON Product.CategoryId = Category.Id WHERE Product.Available = 1";

        if (minPrice != null && maxPrice != null) {
            query += " AND Product.Price >= " + minPrice + " AND Product.Price <= " + maxPrice;
        }
        query += filter != null ? " ORDER BY Product." + filter : " ORDER BY Product.Id DESC";
        try {
            conn = new DbContext().getConnection();
            if (page != -1) {
                query += " OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
                ps = conn.prepareStatement(query);
                ps.setInt(1, (page - 1) * 12);
            } else {
                ps = conn.prepareStatement(query);
            }
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

    public List<Product> getAllProductSearchByName(String name, int page, String filter, String minPrice, String maxPrice) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category "
                + "ON Product.CategoryId = Category.Id"
                + "\nWHERE Product.Name LIKE '%" + name + "%'\n"
                + "AND Product.Available = 1";
        if (minPrice != null && maxPrice != null) {
            query += " AND Product.Price >= " + minPrice + " AND Product.Price <= " + maxPrice;
        }
        query += filter != null ? "\nORDER BY Product." + filter : " ORDER BY Product.Id DESC";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            if (page != -1) {
                query += "\nOFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
                ps = conn.prepareStatement(query);
                ps.setInt(1, page);
            } else {
                ps = conn.prepareStatement(query);
            }
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

    public List<Product> getAllProductSearchByCategoryName(String categoryName, int page, String filter, String minPrice, String maxPrice) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category "
                + "ON Product.CategoryId = Category.Id"
                + "\nWHERE Category.Name = ? "
                + "AND Product.Available = 1";
        if (minPrice != null && maxPrice != null) {
            query += " AND Product.Price >= " + minPrice + " AND Product.Price <= " + maxPrice;
        }
        query += filter != null ? "\nORDER BY Product." + filter : " ORDER BY Product.Id DESC";
        try {
            conn = new DbContext().getConnection();
            if (page != -1) {
                query += "\nOFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
                ps = conn.prepareStatement(query);
                ps.setInt(2, page);
            } else {
                ps = conn.prepareStatement(query);
            }
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
}
