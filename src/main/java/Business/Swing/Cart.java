/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Business.Swing;

import Data.Model.OrderDetail;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import Business.Swing.Component.ProductInCart;
import Business.Swing.Component.Subcomponent.CustomScrollBarUI;
import Data.Model.Account;
import Data.Model.Order;
import Data.Repository.User.OrderRepository;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Cart extends javax.swing.JFrame {

    private OrderRepository orderRepo = new OrderRepository();
    private Order order;
    private Account account;

    public Cart(Account account) {
        initComponents();
        this.account = account;
        this.order = orderRepo.getOrderByAccount(account);
        if (order == null) {
            int orderId = orderRepo.createOrder(account);
            order = new Order(orderId, 0, 0, null, account, null, null);
        }
        List<OrderDetail> list = orderRepo.getOrderDetailByOrder(order);
        setProductCart(list);
        checkout_quantity_value.setText(String.valueOf(order.getQuantity()));
        checkout_total_value.setText("$" + order.getTotal());

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new User(account);
            }

        });
        this.setVisible(true);
    }

    public void setProductCart(List<OrderDetail> list) {
        cart_product_data.setPreferredSize(new Dimension(600, 500));
        cart_product_data.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 20));
        if (list.size() / 5 > 1) {
            cart_product_data.setPreferredSize(new Dimension(cart_product_data.getPreferredSize().width,
                    cart_product_data.getPreferredSize().height + 70 * (list.size() / 5 - 1)));
        }
        for (OrderDetail orderDetail : list) {
            ProductInCart productInCart = new ProductInCart(orderDetail);
            JLabel productAmount = (JLabel) productInCart.getComponent(3);
            JLabel productTotal = (JLabel) productInCart.getComponent(5);

            JButton productAmountDescrease = (JButton) productInCart.getComponent(2);
            productAmountDescrease.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    productAmount.setText((Integer.parseInt(productAmount.getText()) - 1) + "");
                    int amount = Integer.parseInt(productAmount.getText());
                    if (amount == 0) {
                        orderRepo.deleteOrderDetail(orderDetail.getOrder().getId(), orderDetail.getProduct().getId());
                        Container parent = productInCart.getParent();
                        parent.remove(productInCart);
                        parent.revalidate();
                        parent.repaint();
                        list.remove(orderDetail);
                    } else {
                        double subtotal = amount * orderDetail.getProduct().getPrice();
                        productTotal.setText("$" + subtotal);
                        orderRepo.updateOrderDetail(orderDetail.getOrder(), orderDetail.getProduct(), amount);
                        orderDetail.setAmount(amount);
                        orderDetail.setSubtotal(subtotal);
                    }
                    orderRepo.updateOrder(order, list);
                    checkout_quantity_value.setText((Integer.parseInt(checkout_quantity_value.getText()) - 1) + "");
                    checkout_total_value.setText("$" + (Double.parseDouble(checkout_total_value.getText().substring(1)) - orderDetail.getProduct().getPrice()));
                }
            });

            JButton productAmountIncrease = (JButton) productInCart.getComponent(4);
            productAmountIncrease.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    productAmount.setText((Integer.parseInt(productAmount.getText()) + 1) + "");
                    int amount = Integer.parseInt(productAmount.getText());
                    double subtotal = amount * orderDetail.getProduct().getPrice();
                    productTotal.setText("$" + subtotal);
                    orderRepo.updateOrderDetail(orderDetail.getOrder(), orderDetail.getProduct(), amount);
                    orderRepo.updateOrder(order, list);
                    orderDetail.setAmount(amount);
                    orderDetail.setSubtotal(subtotal);
                    checkout_quantity_value.setText((Integer.parseInt(checkout_quantity_value.getText()) + 1) + "");
                    checkout_total_value.setText("$" + (Double.parseDouble(checkout_total_value.getText().substring(1)) + orderDetail.getProduct().getPrice()));
                }
            });

            JButton productButtonDelete = (JButton) productInCart.getComponent(6);
            productButtonDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    list.remove(orderDetail);
                    orderRepo.deleteOrderDetail(orderDetail.getOrder().getId(), orderDetail.getProduct().getId());
                    orderRepo.updateOrder(order, list);
                    checkout_quantity_value.setText((Integer.parseInt(checkout_quantity_value.getText()) - orderDetail.getAmount()) + "");
                    checkout_total_value.setText("$" + (Double.parseDouble(checkout_total_value.getText().substring(1)) - orderDetail.getSubtotal()));
                    Container parent = productInCart.getParent();
                    parent.remove(productInCart);
                    parent.repaint();
                    parent.revalidate();
                }
            });

            cart_product_data.add(productInCart);
            cart_product_data.revalidate();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cart_container = new javax.swing.JPanel();
        cart_button_return = new javax.swing.JButton();
        cart_title = new javax.swing.JLabel();
        cart_product = new javax.swing.JScrollPane();
        cart_product_data = new javax.swing.JPanel();
        checkout_container = new javax.swing.JPanel();
        checkout_title = new javax.swing.JLabel();
        checkout_input_name_label = new javax.swing.JLabel();
        checkout_input_name = new javax.swing.JTextField();
        checkout_input_phone_label = new javax.swing.JLabel();
        checkout_input_phone = new javax.swing.JTextField();
        checkout_input_address_label = new javax.swing.JLabel();
        checkout_input_address = new javax.swing.JTextField();
        checkout_input_delivery_label = new javax.swing.JLabel();
        checkout_input_delivery = new javax.swing.JComboBox<>();
        checkout_line = new javax.swing.JPanel();
        checkout_quantity_label = new javax.swing.JLabel();
        checkout_quantity_value = new javax.swing.JLabel();
        checkout_total_label = new javax.swing.JLabel();
        checkout_total_value = new javax.swing.JLabel();
        checkout_button_checkout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        cart_container.setPreferredSize(new java.awt.Dimension(700, 768));

        cart_button_return.setBackground(new java.awt.Color(0, 0, 204));
        cart_button_return.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cart_button_return.setForeground(new java.awt.Color(255, 255, 255));
        cart_button_return.setText("RETURN TO SHOP");
        cart_button_return.setBorder(null);
        cart_button_return.setFocusPainted(false);
        cart_button_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cart_button_returnActionPerformed(evt);
            }
        });

        cart_title.setFont(new java.awt.Font("Eras Demi ITC", 1, 36)); // NOI18N
        cart_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cart_title.setText("LIST PRODUCT IN CART");
        cart_title.setPreferredSize(new java.awt.Dimension(700, 50));

        cart_product.setBorder(null);
        cart_product.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cart_product.setPreferredSize(new java.awt.Dimension(600, 500));
        cart_product.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        cart_product.getVerticalScrollBar().setUnitIncrement(20);

        javax.swing.GroupLayout cart_product_dataLayout = new javax.swing.GroupLayout(cart_product_data);
        cart_product_data.setLayout(cart_product_dataLayout);
        cart_product_dataLayout.setHorizontalGroup(
            cart_product_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        cart_product_dataLayout.setVerticalGroup(
            cart_product_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        cart_product.setViewportView(cart_product_data);

        javax.swing.GroupLayout cart_containerLayout = new javax.swing.GroupLayout(cart_container);
        cart_container.setLayout(cart_containerLayout);
        cart_containerLayout.setHorizontalGroup(
            cart_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart_containerLayout.createSequentialGroup()
                .addGroup(cart_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cart_containerLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(cart_button_return, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cart_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cart_containerLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(cart_product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cart_containerLayout.setVerticalGroup(
            cart_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart_containerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cart_button_return, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(cart_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(cart_product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        checkout_container.setBackground(new java.awt.Color(51, 255, 204));
        checkout_container.setPreferredSize(new java.awt.Dimension(300, 768));

        checkout_title.setFont(new java.awt.Font("Unispace", 1, 36)); // NOI18N
        checkout_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkout_title.setText("CHECKOUT");
        checkout_title.setPreferredSize(new java.awt.Dimension(300, 50));

        checkout_input_name_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        checkout_input_name_label.setText("   Fullname");

        checkout_input_name.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        checkout_input_name.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 5));
        checkout_input_name.setPreferredSize(new java.awt.Dimension(280, 50));

        checkout_input_phone_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        checkout_input_phone_label.setText("   Phone");
        checkout_input_phone_label.setPreferredSize(new java.awt.Dimension(300, 50));

        checkout_input_phone.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        checkout_input_phone.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 5));
        checkout_input_phone.setPreferredSize(new java.awt.Dimension(280, 50));

        checkout_input_address_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        checkout_input_address_label.setText("   Address");
        checkout_input_address_label.setPreferredSize(new java.awt.Dimension(300, 50));

        checkout_input_address.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        checkout_input_address.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 5));
        checkout_input_address.setPreferredSize(new java.awt.Dimension(280, 50));

        checkout_input_delivery_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        checkout_input_delivery_label.setText("   Delivery");
        checkout_input_delivery_label.setPreferredSize(new java.awt.Dimension(300, 50));

        checkout_input_delivery.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        checkout_input_delivery.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Free", "Save", "Standard", "Fast" }));
        checkout_input_delivery.setBorder(null);
        checkout_input_delivery.setMinimumSize(new java.awt.Dimension(280, 40));
        checkout_input_delivery.setPreferredSize(new java.awt.Dimension(280, 40));

        checkout_line.setBackground(new java.awt.Color(0, 0, 255));
        checkout_line.setPreferredSize(new java.awt.Dimension(250, 3));

        javax.swing.GroupLayout checkout_lineLayout = new javax.swing.GroupLayout(checkout_line);
        checkout_line.setLayout(checkout_lineLayout);
        checkout_lineLayout.setHorizontalGroup(
            checkout_lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        checkout_lineLayout.setVerticalGroup(
            checkout_lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        checkout_quantity_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        checkout_quantity_label.setText("   Amount:");
        checkout_quantity_label.setPreferredSize(new java.awt.Dimension(140, 50));

        checkout_quantity_value.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        checkout_quantity_value.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkout_quantity_value.setText("7");
        checkout_quantity_value.setPreferredSize(new java.awt.Dimension(140, 50));

        checkout_total_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        checkout_total_label.setText("   Price:");
        checkout_total_label.setPreferredSize(new java.awt.Dimension(140, 50));

        checkout_total_value.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        checkout_total_value.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkout_total_value.setText("$1890");
        checkout_total_value.setPreferredSize(new java.awt.Dimension(140, 50));

        checkout_button_checkout.setBackground(new java.awt.Color(0, 204, 0));
        checkout_button_checkout.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        checkout_button_checkout.setForeground(new java.awt.Color(204, 255, 255));
        checkout_button_checkout.setText("CHECKOUT");
        checkout_button_checkout.setBorder(null);
        checkout_button_checkout.setBorderPainted(false);
        checkout_button_checkout.setFocusPainted(false);
        checkout_button_checkout.setPreferredSize(new java.awt.Dimension(280, 80));
        checkout_button_checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkout_button_checkoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout checkout_containerLayout = new javax.swing.GroupLayout(checkout_container);
        checkout_container.setLayout(checkout_containerLayout);
        checkout_containerLayout.setHorizontalGroup(
            checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkout_containerLayout.createSequentialGroup()
                .addGroup(checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(checkout_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkout_input_name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(checkout_containerLayout.createSequentialGroup()
                .addGroup(checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkout_input_phone_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkout_input_address_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(checkout_containerLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkout_input_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkout_input_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkout_button_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(checkout_containerLayout.createSequentialGroup()
                                                .addComponent(checkout_quantity_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(checkout_quantity_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(checkout_containerLayout.createSequentialGroup()
                                                .addComponent(checkout_total_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(checkout_total_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, checkout_containerLayout.createSequentialGroup()
                                            .addGap(15, 15, 15)
                                            .addComponent(checkout_line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, checkout_containerLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(checkout_input_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(checkout_input_delivery_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(checkout_containerLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(checkout_input_delivery, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        checkout_containerLayout.setVerticalGroup(
            checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkout_containerLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(checkout_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(checkout_input_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(checkout_input_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(checkout_input_phone_label, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(checkout_input_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(checkout_input_address_label, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(checkout_input_address, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkout_input_delivery_label, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(checkout_input_delivery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(checkout_line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkout_quantity_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkout_quantity_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(checkout_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkout_total_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkout_total_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(checkout_button_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cart_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(checkout_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cart_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkout_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cart_button_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cart_button_returnActionPerformed
        this.dispose();
        new User(account);
    }//GEN-LAST:event_cart_button_returnActionPerformed

    private void checkout_button_checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkout_button_checkoutActionPerformed
        if (checkout_quantity_value.getText().equals(0)) {
            JOptionPane.showMessageDialog(this, "No items in cart to checkout. Please add more!", "User error", JOptionPane.OK_OPTION);
            return;
        }

        String name = checkout_input_name.getText().trim();
        String phone = checkout_input_phone.getText().trim();
        String address = checkout_input_address.getText().trim();
        int deliveryId = checkout_input_delivery.getSelectedIndex();
        if (name.equals("")
                || phone.equals("")
                || address.equals("")
                || deliveryId == 0) {
            JOptionPane.showMessageDialog(this, "All field must be fulfilled", "User error", JOptionPane.OK_OPTION);
            return;
        }

        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Phone must be 10 digits", "User error", JOptionPane.OK_OPTION);
            return;
        }
        int shippingId = orderRepo.addShipping(name, phone, address, deliveryId);
        orderRepo.checkout(order, shippingId);
        this.dispose();
    }//GEN-LAST:event_checkout_button_checkoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cart_button_return;
    private javax.swing.JPanel cart_container;
    private javax.swing.JScrollPane cart_product;
    private javax.swing.JPanel cart_product_data;
    private javax.swing.JLabel cart_title;
    private javax.swing.JButton checkout_button_checkout;
    private javax.swing.JPanel checkout_container;
    private javax.swing.JTextField checkout_input_address;
    private javax.swing.JLabel checkout_input_address_label;
    private javax.swing.JComboBox<String> checkout_input_delivery;
    private javax.swing.JLabel checkout_input_delivery_label;
    private javax.swing.JTextField checkout_input_name;
    private javax.swing.JLabel checkout_input_name_label;
    private javax.swing.JTextField checkout_input_phone;
    private javax.swing.JLabel checkout_input_phone_label;
    private javax.swing.JPanel checkout_line;
    private javax.swing.JLabel checkout_quantity_label;
    private javax.swing.JLabel checkout_quantity_value;
    private javax.swing.JLabel checkout_title;
    private javax.swing.JLabel checkout_total_label;
    private javax.swing.JLabel checkout_total_value;
    // End of variables declaration//GEN-END:variables

}
