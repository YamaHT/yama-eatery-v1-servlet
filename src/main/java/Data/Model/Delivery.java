/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

/**
 *
 * @author ADMIN
 */
public class Delivery {

    private int id;
    private String type;
    private double price;
    private int time;

    public Delivery(int id, String type, double price, int time) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getTime() {
        return time;
    }
}
