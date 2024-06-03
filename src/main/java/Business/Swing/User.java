/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Business.Swing;

import Business.Swing.Component.ProductCard;
import Business.Swing.Component.Subcomponent.CustomScrollBarUI;
import Data.Model.Account;
import Data.Model.Product;
import Data.Repository.Admin.ResourceRepository;
import Data.Repository.User.ProductRepository;
import Utils.ImageUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class User extends javax.swing.JFrame {

    private Account a;
    private ProductRepository productRepository = new ProductRepository();

    public User(Account a) {
        initComponents();
        this.a = a;
        userAvatar.setIcon(new ImageIcon(
                ImageUtils.convertByteToImage(new ResourceRepository().getImageByName("logo"))
                        .getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
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
        productPanel = new javax.swing.JScrollPane();
        ProductData = new javax.swing.JPanel();
        userAvatar = new Business.Swing.Component.Subcomponent.ImageAvatar();
        searchBar = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        filterText = new javax.swing.JLabel();
        filterInput = new javax.swing.JComboBox<>();
        categoryText = new javax.swing.JLabel();
        categoryInput = new javax.swing.JComboBox<>();
        priceText = new javax.swing.JLabel();
        priceInputMin = new javax.swing.JTextField();
        priceInputMax = new javax.swing.JTextField();
        buttonApply = new javax.swing.JButton();
        buttonGetall = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        main_container.setBackground(new java.awt.Color(204, 255, 204));
        main_container.setPreferredSize(new java.awt.Dimension(1024, 768));

        productPanel.setBackground(new java.awt.Color(204, 204, 204));
        productPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        productPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        productPanel.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        productPanel.getVerticalScrollBar().setUnitIncrement(20);
        productPanel.setPreferredSize(new java.awt.Dimension(850, 700));

        ProductData.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout ProductDataLayout = new javax.swing.GroupLayout(ProductData);
        ProductData.setLayout(ProductDataLayout);
        ProductDataLayout.setHorizontalGroup(
            ProductDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        ProductDataLayout.setVerticalGroup(
            ProductDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );

        productPanel.setViewportView(ProductData);

        userAvatar.setPreferredSize(new java.awt.Dimension(80, 80));

        searchBar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchBar.setToolTipText("");
        searchBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        buttonSearch.setBackground(new java.awt.Color(204, 204, 0));
        buttonSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonSearch.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearch.setText("Search");
        buttonSearch.setBorder(null);
        buttonSearch.setFocusPainted(false);
        buttonSearch.setPreferredSize(new java.awt.Dimension(80, 50));
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        filterText.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        filterText.setText("Filter");

        filterInput.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        filterInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Price ascending", "Price descending", "Newest", "Oldest" }));
        filterInput.setBorder(null);

        categoryText.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        categoryText.setText("Category");

        categoryInput.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        categoryInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Food", "Drink", "Dessert", "Snack" }));
        categoryInput.setBorder(null);

        priceText.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        priceText.setText("Price");

        priceInputMin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        priceInputMin.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        priceInputMin.setPreferredSize(new java.awt.Dimension(80, 40));

        priceInputMax.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        priceInputMax.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        priceInputMax.setPreferredSize(new java.awt.Dimension(80, 40));

        buttonApply.setBackground(new java.awt.Color(255, 204, 204));
        buttonApply.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        buttonApply.setForeground(new java.awt.Color(0, 0, 255));
        buttonApply.setText("APPLY");
        buttonApply.setBorder(null);
        buttonApply.setFocusPainted(false);
        buttonApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonApplyActionPerformed(evt);
            }
        });

        buttonGetall.setBackground(new java.awt.Color(255, 255, 51));
        buttonGetall.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        buttonGetall.setForeground(new java.awt.Color(204, 0, 51));
        buttonGetall.setText("GET ALL");
        buttonGetall.setBorder(null);
        buttonGetall.setFocusPainted(false);
        buttonGetall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGetallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_containerLayout = new javax.swing.GroupLayout(main_container);
        main_container.setLayout(main_containerLayout);
        main_containerLayout.setHorizontalGroup(
            main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_containerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_containerLayout.createSequentialGroup()
                        .addComponent(searchBar)
                        .addGap(0, 0, 0)
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(main_containerLayout.createSequentialGroup()
                        .addComponent(priceInputMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(priceInputMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(main_containerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_containerLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categoryText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priceText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filterText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_containerLayout.createSequentialGroup()
                                .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonGetall, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonApply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_containerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(userAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );
        main_containerLayout.setVerticalGroup(
            main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_containerLayout.createSequentialGroup()
                .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_containerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchBar)
                            .addComponent(buttonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(main_containerLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(userAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filterText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categoryText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceInputMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceInputMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(buttonApply, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonGetall, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        if (searchBar.getText().trim().equals("")) {
            return;
        }
        String filter = null;
        switch (filterInput.getSelectedIndex()) {
            case 1:
            case 2:
                filter = filterInput.getSelectedItem().toString().split("ending")[0];
                break;
            case 3:
                filter = "Id DESC";
                break;
            case 4:
                filter = "Id ASC";
        }
        String minPrice = priceInputMin.getText().trim();
        String maxPrice = priceInputMax.getText().trim();
        if (minPrice.equals("") || maxPrice.equals("")) {
            minPrice = maxPrice = null;
        } else if (!minPrice.matches("\\d+[.]?\\d*$") || !maxPrice.matches("\\d+[.]?\\d*$")) {
            JOptionPane.showMessageDialog(this, "Price must be a double number!", "Error", JOptionPane.OK_OPTION);
            priceInputMin.setText("");
            priceInputMax.setText("");
            return;
        }

        priceInputMin.setText("");
        priceInputMax.setText("");
        setProductCard(productRepository.getAllProductSearchByName(searchBar.getText().trim(), -1, filter, minPrice, maxPrice));
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonGetallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGetallActionPerformed
        setProductCard(productRepository.getAllProduct(-1, null, null, null));
        priceInputMin.setText("");
        priceInputMax.setText("");
        searchBar.setText("");
        categoryInput.setSelectedIndex(0);
        filterInput.setSelectedIndex(0);
    }//GEN-LAST:event_buttonGetallActionPerformed

    private void buttonApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonApplyActionPerformed
        String filter = null;
        switch (filterInput.getSelectedIndex()) {
            case 1:
            case 2:
                filter = filterInput.getSelectedItem().toString().split("ending")[0];
                break;
            case 3:
                filter = "Id DESC";
                break;
            case 4:
                filter = "Id ASC";
        }
        String minPrice = priceInputMin.getText().trim();
        String maxPrice = priceInputMax.getText().trim();
        if (minPrice.equals("") || maxPrice.equals("")) {
            minPrice = maxPrice = null;
        } else if (!minPrice.matches("\\d+[.]?\\d*$") || !maxPrice.matches("\\d+[.]?\\d*$")) {
            JOptionPane.showMessageDialog(this, "Price must be a double number!", "Error", JOptionPane.OK_OPTION);
            priceInputMin.setText("");
            priceInputMax.setText("");
            return;
        }

        priceInputMin.setText("");
        priceInputMax.setText("");
        if (categoryInput.getSelectedIndex() == 0) {
            setProductCard(productRepository.getAllProduct(-1, filter, minPrice, maxPrice));
        } else {
            setProductCard(productRepository.getAllProductSearchByCategoryName(categoryInput.getSelectedItem().toString(), -1, filter, minPrice, maxPrice));
        }
    }//GEN-LAST:event_buttonApplyActionPerformed

    public void run() {
        setProductCard(productRepository.getAllProduct(-1, null, null, null));
        this.setVisible(true);
    }

    public void setProductCard(List<Product> list) {
        ProductData.setPreferredSize(new Dimension(850, 700));
        ProductData.removeAll();
        ProductData.repaint();
        ProductData.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 20));
        if (list.size() / 5 > 1) {
            ProductData.setPreferredSize(new Dimension(ProductData.getPreferredSize().width,
                    ProductData.getPreferredSize().height + 300 * (list.size() / 5 - 1)));
        }
        for (int i = 0; i < list.size(); i++) {
            Color color = Color.CYAN;
            switch (i % 5) {
                case 0:
                    color = new Color(255, 255, 153);
                    break;
                case 1:
                    color = new Color(204, 255, 153);
                    break;
                case 2:
                    color = new Color(153, 255, 255);
                    break;
                case 3:
                    color = new Color(255, 204, 204);
                    break;
                case 4:
                    color = new Color(204, 204, 255);
            }
            ProductData.add(new ProductCard(list.get(i), color));
            ProductData.revalidate();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ProductData;
    private javax.swing.JButton buttonApply;
    private javax.swing.JButton buttonGetall;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JComboBox<String> categoryInput;
    private javax.swing.JLabel categoryText;
    private javax.swing.JComboBox<String> filterInput;
    private javax.swing.JLabel filterText;
    private javax.swing.JPanel main_container;
    private javax.swing.JTextField priceInputMax;
    private javax.swing.JTextField priceInputMin;
    private javax.swing.JLabel priceText;
    private javax.swing.JScrollPane productPanel;
    private javax.swing.JTextField searchBar;
    private Business.Swing.Component.Subcomponent.ImageAvatar userAvatar;
    // End of variables declaration//GEN-END:variables
}
