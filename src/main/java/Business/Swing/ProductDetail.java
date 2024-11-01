/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Business.Swing;

import Data.Model.Account;
import Data.Model.Order;
import Data.Model.OrderDetail;
import Data.Model.Product;
import Data.Repository.User.OrderRepository;
import Utils.ImageUtils;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ProductDetail extends javax.swing.JFrame {

    private Account account;
    private Product product;

    public ProductDetail(Product product, Account account) {
        this.account = account;
        this.product = product;
        initComponents();
        productImage.requestFocusInWindow();
        productImageText.setIcon(new ImageIcon(ImageUtils.convertByteToImage(product.getImage()).getScaledInstance(400, 400, Image.SCALE_SMOOTH)));
        productInventory.setText(product.getInventory() == 0 ? "Out of stock" : "In stock");
        productCategory.setText(product.getCategory().getName());
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText("$" + product.getPrice());
        productPricex2.setText("<html>\n<p style='text-decoration: line-through'>$" + (product.getPrice() * 2) + "</p>\n</html>");
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_container = new javax.swing.JPanel();
        productImage = new javax.swing.JPanel();
        productImageText = new javax.swing.JLabel();
        productInventory = new javax.swing.JLabel();
        productCategory = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productDescription = new javax.swing.JEditorPane();
        productPrice = new javax.swing.JLabel();
        productPricex2 = new javax.swing.JLabel();
        inputAmount = new javax.swing.JTextField();
        buttonAddToCart = new javax.swing.JButton();
        buttonReturn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Product Detail");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        main_container.setPreferredSize(new java.awt.Dimension(900, 600));

        productImage.setBackground(new java.awt.Color(0, 0, 0));
        productImage.setPreferredSize(new java.awt.Dimension(400, 400));

        productImageText.setBackground(new java.awt.Color(204, 204, 204));
        productImageText.setText("ProductImage");

        javax.swing.GroupLayout productImageLayout = new javax.swing.GroupLayout(productImage);
        productImage.setLayout(productImageLayout);
        productImageLayout.setHorizontalGroup(
            productImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productImageText, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        productImageLayout.setVerticalGroup(
            productImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productImageText, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        productInventory.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        productInventory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productInventory.setText("IN STOCK");
        productInventory.setPreferredSize(new java.awt.Dimension(150, 30));

        productCategory.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        productCategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productCategory.setText("Category");
        productCategory.setPreferredSize(new java.awt.Dimension(150, 30));

        productName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        productName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productName.setText("Product Name");

        jScrollPane1.setBorder(null);
        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        productDescription.setEditable(false);
        productDescription.setBorder(null);
        productDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        productDescription.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaav");
        productDescription.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        productDescription.setEnabled(false);
        productDescription.setFocusable(false);
        productDescription.setOpaque(false);
        jScrollPane1.setViewportView(productDescription);

        productPrice.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        productPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        productPrice.setText("$60");
        productPrice.setPreferredSize(new java.awt.Dimension(150, 50));

        productPricex2.setBackground(new java.awt.Color(204, 204, 204));
        productPricex2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        productPricex2.setForeground(new java.awt.Color(204, 204, 204));
        productPricex2.setText("$120");
        productPricex2.setToolTipText("");
        productPricex2.setPreferredSize(new java.awt.Dimension(150, 30));

        inputAmount.setBackground(new java.awt.Color(161, 169, 175));
        inputAmount.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        inputAmount.setForeground(new java.awt.Color(255, 255, 255));
        inputAmount.setText("1");
        inputAmount.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        buttonAddToCart.setBackground(new java.awt.Color(51, 51, 51));
        buttonAddToCart.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        buttonAddToCart.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddToCart.setText("ADD TO CART");
        buttonAddToCart.setFocusPainted(false);
        buttonAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddToCartActionPerformed(evt);
            }
        });

        buttonReturn.setBackground(new java.awt.Color(102, 204, 255));
        buttonReturn.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        buttonReturn.setForeground(new java.awt.Color(0, 0, 153));
        buttonReturn.setText("RETURN TO SHOP");
        buttonReturn.setFocusPainted(false);
        buttonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_containerLayout = new javax.swing.GroupLayout(main_container);
        main_container.setLayout(main_containerLayout);
        main_containerLayout.setHorizontalGroup(
            main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_containerLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(main_containerLayout.createSequentialGroup()
                        .addComponent(productImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(main_containerLayout.createSequentialGroup()
                                .addComponent(productInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(productCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(main_containerLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(main_containerLayout.createSequentialGroup()
                                        .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(productPricex2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(main_containerLayout.createSequentialGroup()
                                        .addComponent(inputAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(buttonAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
        );
        main_containerLayout.setVerticalGroup(
            main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_containerLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(main_containerLayout.createSequentialGroup()
                        .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productPricex2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(inputAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(buttonReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        this.dispose();
    }//GEN-LAST:event_formWindowLostFocus

    private void buttonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReturnActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonReturnActionPerformed

    private void buttonAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddToCartActionPerformed
        if (product.getInventory() == 0) {
            JOptionPane.showMessageDialog(null, "This product is out of stock", "System error", JOptionPane.OK_OPTION);
            return;
        }

        if (!inputAmount.getText().matches("[+-]?\\d+")) {
            JOptionPane.showMessageDialog(null, "Amount must be a number", "User error", JOptionPane.OK_OPTION);
            return;
        }

        int amount = Integer.parseInt(inputAmount.getText());
        if (amount <= 0) {
            JOptionPane.showMessageDialog(null, "Amount must higher than 0", "User error", JOptionPane.OK_OPTION);
            return;
        }

        amount = Math.min(Math.max(amount, 1), product.getInventory());

        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.getOrderByAccount(account);
        if (order == null) {
            orderRepository.createOrder(account);
            order = orderRepository.getOrderByAccount(account);
        }

        OrderDetail orderDetail = orderRepository.getOrderDetailByOrderAndProduct(order, product);
        if (orderDetail == null) {
            orderRepository.addOrderDetail(order, product, amount);
        } else {
            amount = Math.min(product.getInventory(),
                    orderDetail.getAmount() + amount);
            orderRepository.updateOrderDetail(order, product, amount);
        }
        
        orderRepository.updateOrder(order, orderRepository.getAllOrderDetailByOrder(order));
        this.dispose();
    }//GEN-LAST:event_buttonAddToCartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddToCart;
    private javax.swing.JButton buttonReturn;
    private javax.swing.JTextField inputAmount;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main_container;
    private javax.swing.JLabel productCategory;
    private javax.swing.JEditorPane productDescription;
    private javax.swing.JPanel productImage;
    private javax.swing.JLabel productImageText;
    private javax.swing.JLabel productInventory;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productPrice;
    private javax.swing.JLabel productPricex2;
    // End of variables declaration//GEN-END:variables
}
