/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Order {

    private int id;
    private int quantity;
    private double total;
    private Date orderDate;
    private Account account;
    private Status status;
    private Shipping shipping;

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, int quantity, double total, Date orderDate, Account account, Status status, Shipping shipping) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.orderDate = orderDate;
        this.account = account;
        this.status = status;
        this.shipping = shipping;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Account getAccount() {
        return account;
    }

    public Status getStatus() {
        return status;
    }

    public Shipping getShipping() {
        return shipping;
    }

}
