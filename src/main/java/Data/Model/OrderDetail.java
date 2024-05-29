/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Model;

/**
 *
 * @author ADMIN
 */
public class OrderDetail {

    private Order order;
    private Product product;
    private int amount;
    private double subtotal;

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public double getSubtotal() {
        return subtotal;
    }

}
