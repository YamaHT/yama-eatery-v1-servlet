package Utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.commons.codec.binary.Base64;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class ImageUtils {

    public static byte[] compressImageFromWebsite(InputStream inputStream) throws IOException {
        // Convert InputStream to byte[]
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();

        // Compress file
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(buffer.toByteArray());
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(buffer.toByteArray().length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] compressImageFromWinform(byte[] image) {
        if (image == null) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(image);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);
        byte[] buffer = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(buffer);
            outputStream.write(buffer, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        if (data == null) {
            return null;
        }
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
        } catch (Exception e) {
        }

        return outputStream.toByteArray();
    }

    public static Image convertByteToImage(byte[] image) {
        try {
            return ImageIO.read(new ByteArrayInputStream(image));
        } catch (Exception e) {
        }
        return null;
    }

    public static String toBase64(byte[] image) {
        if (image == null) {
            return null;
        }
        return new String(Base64.encodeBase64(image));
    }

}
