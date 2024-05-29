/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

import Utils.ImageUtils;

/**
 *
 * @author ADMIN
 */
public class Product {

    private int id;
    private String name;
    private byte[] image;
    private double price;
    private String description;
    private int inventory;
    private boolean available;
    private Category category;
    private String imgBase64;

    public Product(int id, String name, byte[] image, double price, String description, int inventory, boolean available, Category category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.inventory = inventory;
        this.available = available;
        this.category = category;
        this.imgBase64 = ImageUtils.toBase64(image);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getInventory() {
        return inventory;
    }

    public boolean isAvailable() {
        return available;
    }

    public Category getCategory() {
        return category;
    }

    public String getImgBase64() {
        return imgBase64;
    }

}
