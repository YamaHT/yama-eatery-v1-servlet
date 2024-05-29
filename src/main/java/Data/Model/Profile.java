/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

import Utils.ImageUtils;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author ADMIN
 */
public class Profile {

    private int id;
    private byte[] image;
    private Date birthday;
    private String name;
    private String phone;
    private String address;
    private String imgBase64;

    public Profile(int id, byte[] image, Date birthday, String name, String phone, String address) {
        this.id = id;
        this.image = image;
        this.birthday = birthday;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.imgBase64 = ImageUtils.toBase64(image);

    }

    public int getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getImgBase64() {
        return imgBase64;
    }

}
