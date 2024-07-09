/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.Admin;

import Data.DbContext;
import Data.Model.ImageResource;
import Utils.ImageUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ResourceRepository {

    public void upload(String name, byte[] image) {
        String query = "INSERT INTO ImageResource VALUES(?,?)";
        try {
            DbContext.executeUpdate(query, name, image);
        } catch (Exception e) {
        }
    }

    public void delete(String name) {
        String query = "DELETE FROM ImageResource WHERE Name = ?";
        try {
            DbContext.executeUpdate(query, name);
        } catch (Exception e) {
        }
    }

    public byte[] getImageByName(String name) {
        String query = "SELECT * FROM ImageResource WHERE Name = ?";
        try {
            ResultSet rs = DbContext.executeQuery(query, name);
            while (rs.next()) {
                return ImageUtils.decompressImage(rs.getBytes(2));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<ImageResource> getAllResource() {
        List<ImageResource> list = new ArrayList<>();
        String query = "SELECT * FROM ImageResource";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            while (rs.next()) {
                list.add(new ImageResource(rs.getString(1), ImageUtils.decompressImage(rs.getBytes(2))));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ImageResource> getAllResourceSearchByName(String name) {
        List<ImageResource> list = new ArrayList<>();
        String query = "SELECT * FROM ImageResource WHERE Name LIKE '%" + name + "%'";
        try {
            ResultSet rs = DbContext.executeQuery(query);
            while (rs.next()) {
                list.add(new ImageResource(rs.getString(1), ImageUtils.decompressImage(rs.getBytes(2))));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
