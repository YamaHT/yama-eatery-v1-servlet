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

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct(int page, String filter, String price) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product INNER JOIN Category ON Product.CategoryId = Category.Id WHERE Product.Available = 1";

        if (price != null) {
            query += " AND Product.Price >= " + price.split("/")[0] + " AND Product.Price <= " + price.split("/")[1];
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

    public int getCountProduct() {
        return 0;
    }
}
