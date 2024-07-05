/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class Shipping {

    private int id;
    private String recipientName;
    private String phone;
    private String address;
    private Timestamp deliveryDate;
    private Delivery delivery;

    public Shipping(int id, String recipientName, String phone, String address, Timestamp deliveryDate, Delivery delivery) {
        this.id = id;
        this.recipientName = recipientName;
        this.phone = phone;
        this.address = address;
        this.deliveryDate = deliveryDate;
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public Delivery getDelivery() {
        return delivery;
    }

}
