/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

import Utils.ImageUtils;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author ADMIN
 */
public class ImageResource {

    private String name;
    private byte[] image;
    private String imgBase64;

    public ImageResource(String name, byte[] image) {
        this.name = name;
        this.image = image;
        this.imgBase64 = ImageUtils.toBase64(image);
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public String getImgBase64() {
        return imgBase64;
    }

}
