/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Business.Swing.Component;

import Data.Model.OrderDetail;
import Utils.ImageUtils;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class ProductInCart extends javax.swing.JPanel {

    private OrderDetail orderDetail;

    public ProductInCart(OrderDetail orderDetail) {
        initComponents();
        this.orderDetail = orderDetail;
        OrderDetailAmount.setText(String.valueOf(orderDetail.getAmount()));
        OrderDetailPrice.setText("$" + orderDetail.getSubtotal());
        ProductImageLabel.setIcon(new ImageIcon(ImageUtils.convertByteToImage(orderDetail.getProduct().getImage()).getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        ProductName.setText(orderDetail.getProduct().getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProductImagePanel = new javax.swing.JPanel();
        ProductImageLabel = new javax.swing.JLabel();
        ProductName = new javax.swing.JLabel();
        ProductAmountDecrease = new javax.swing.JButton();
        OrderDetailAmount = new javax.swing.JLabel();
        ProductAmountIncrease = new javax.swing.JButton();
        OrderDetailPrice = new javax.swing.JLabel();
        Button_Delete = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 50));

        ProductImagePanel.setBackground(new java.awt.Color(255, 255, 255));
        ProductImagePanel.setPreferredSize(new java.awt.Dimension(50, 50));

        ProductImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProductImageLabel.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout ProductImagePanelLayout = new javax.swing.GroupLayout(ProductImagePanel);
        ProductImagePanel.setLayout(ProductImagePanelLayout);
        ProductImagePanelLayout.setHorizontalGroup(
            ProductImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(ProductImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ProductImagePanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(ProductImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)))
        );
        ProductImagePanelLayout.setVerticalGroup(
            ProductImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(ProductImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ProductImagePanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(ProductImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        ProductName.setBackground(new java.awt.Color(153, 153, 153));
        ProductName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ProductName.setText("Khoai tay chien");
        ProductName.setPreferredSize(new java.awt.Dimension(200, 50));

        ProductAmountDecrease.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ProductAmountDecrease.setText("-");
        ProductAmountDecrease.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ProductAmountDecrease.setBorderPainted(false);
        ProductAmountDecrease.setContentAreaFilled(false);
        ProductAmountDecrease.setFocusPainted(false);
        ProductAmountDecrease.setPreferredSize(new java.awt.Dimension(25, 50));

        OrderDetailAmount.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        OrderDetailAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OrderDetailAmount.setText("1");
        OrderDetailAmount.setPreferredSize(new java.awt.Dimension(50, 50));

        ProductAmountIncrease.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ProductAmountIncrease.setText("+");
        ProductAmountIncrease.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ProductAmountIncrease.setBorderPainted(false);
        ProductAmountIncrease.setContentAreaFilled(false);
        ProductAmountIncrease.setFocusPainted(false);
        ProductAmountIncrease.setPreferredSize(new java.awt.Dimension(25, 50));

        OrderDetailPrice.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        OrderDetailPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OrderDetailPrice.setText("$100");
        OrderDetailPrice.setPreferredSize(new java.awt.Dimension(80, 50));

        Button_Delete.setFont(new java.awt.Font("Harlow Solid Italic", 0, 48)); // NOI18N
        Button_Delete.setForeground(new java.awt.Color(255, 0, 0));
        Button_Delete.setText("x");
        Button_Delete.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Button_Delete.setBorderPainted(false);
        Button_Delete.setContentAreaFilled(false);
        Button_Delete.setFocusPainted(false);
        Button_Delete.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(ProductImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ProductAmountDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(OrderDetailAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ProductAmountIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(OrderDetailPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(Button_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProductImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(ProductAmountDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Button_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(OrderDetailAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ProductAmountIncrease, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(OrderDetailPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Delete;
    private javax.swing.JLabel OrderDetailAmount;
    private javax.swing.JLabel OrderDetailPrice;
    private javax.swing.JButton ProductAmountDecrease;
    private javax.swing.JButton ProductAmountIncrease;
    private javax.swing.JLabel ProductImageLabel;
    private javax.swing.JPanel ProductImagePanel;
    private javax.swing.JLabel ProductName;
    // End of variables declaration//GEN-END:variables
}
