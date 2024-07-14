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
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProduct(int page) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product\n"
                + "INNER JOIN Category ON Product.CategoryId = Category.Id\n"
                + "WHERE Product.Available = 1\n"
                + "ORDER BY Product.Id DESC";

        if (page != -1) {
            query += "\nOFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
        }

        try {
            ResultSet rs = DbContext.executeQuery(query, (page - 1) * 6);
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

    public List<Product> getAllProductSearchByName(String name, int page) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category "
                + "ON Product.CategoryId = Category.Id "
                + "WHERE Product.Name LIKE '%" + name + "%'"
                + "AND Product.Available = 1\n"
                + "ORDER BY Product.Id DESC";

        if (page != -1) {
            query += "\nOFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
        }

        try {
            ResultSet rs = DbContext.executeQuery(query, (page - 1) * 6);
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

    public List<Product> getAllProductSearchByCategoryName(String categoryName, int page) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category "
                + "ON Product.CategoryId = Category.Id "
                + "WHERE Category.Name = ? "
                + "AND Product.Available = 1\n"
                + "ORDER BY Product.Id DESC";

        if (page != -1) {
            query += "\nOFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
        }

        try {
            ResultSet rs = DbContext.executeQuery(query, categoryName, (page - 1) * 6);
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

    public List<Product> getAllProductSearchBySearchNameAndCategoryName(String searchName, String categoryName, int page) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM "
                + "Product INNER JOIN Category\n"
                + "ON Product.CategoryId = Category.Id\n"
                + "WHERE Category.Name = ?\n"
                + "AND Product.Name LIKE '%" + searchName + "%'\n"
                + "AND Product.Available = 1\n"
                + "ORDER BY Product.Id DESC";

        if (page != -1) {
            query += "\nOFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
        }

        try {
            ResultSet rs = DbContext.executeQuery(query, categoryName, (page - 1) * 6);
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
                + "Product INNER JOIN Category\n"
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

    public Product getProductById(int id) {
        String query = "SELECT * FROM Product\n"
                + "INNER JOIN Category ON Product.CategoryId = Category.Id\n"
                + "WHERE Product.Id = ?";

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
                        new Category(rs.getInt(9), rs.getString(10))
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int getCountProduct(String action, String name) {
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

        try {
            ResultSet rs = DbContext.executeQuery(query, name);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }

    public int getCountProductBySearchNameAndCaegoryName(String searchName, String categoryName) {
        String query = "SELECT COUNT(*) FROM Product\n"
                + "INNER JOIN Category ON Product.CategoryId = Category.Id\n"
                + "WHERE Category.Name = ?\n"
                + "AND Product.Name LIKE '%" + searchName + "%'\n"
                + "AND Product.Available = 1";

        try {
            ResultSet rs = DbContext.executeQuery(query, categoryName);
            rs.next();
            return rs.getInt(1);
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
                String query = "UPDATE [dbo].[Product] SET "
                        + "[Name] = ?, "
                        + "[Image] = ?, "
                        + "[Price] = ?, "
                        + "[Description] = ?, "
                        + "[Inventory] = ?, "
                        + "[CategoryId] = ?\n"
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
                String query = "UPDATE [dbo].[Product] SET "
                        + "[Name] = ?, "
                        + "[Price] = ?, "
                        + "[Description] = ?, "
                        + "[Inventory] = ?, "
                        + "[CategoryId] = ? "
                        + "WHERE Id = ?";
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
