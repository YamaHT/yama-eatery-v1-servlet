/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Repository.Admin;

import Data.DbContext;
import Data.Model.ImageResource;
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
public class ResourceRepository {

    public void upload(String name, byte[] image) {
        String query = "insert into ImageResource values(?,?)";
        try {
            DbContext.executeUpdate(query, name, image);
        } catch (Exception e) {
        }
    }

    public void delete(String name) {
        String query = "delete from ImageResource where Name = ?";
        try {
            DbContext.executeUpdate(query, name);
        } catch (Exception e) {
        }
    }

    public byte[] getImageByName(String name) {
        String query = "select * from ImageResource where Name = ?";
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
        String query = "select * from ImageResource";
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
