/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

/**
 *
 * @author ADMIN
 */
public class Shipping {

    private int id;
    private String phone;
    private String address;
    private String recipientName;
    private String deliveryDate;
    private Delivery delivery;

    public Shipping(int id, String phone, String address, String recipientName, String deliveryDate, Delivery delivery) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.recipientName = recipientName;
        this.deliveryDate = deliveryDate;
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public Delivery getDelivery() {
        return delivery;
    }

}
