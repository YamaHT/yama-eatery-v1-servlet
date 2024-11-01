/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Business.Swing;

import Data.Model.Account;
import Data.Repository.Admin.ResourceRepository;
import Data.Repository.User.AccountRepository;
import Utils.ImageUtils;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ADMIN
 */
public class Profile extends javax.swing.JFrame {

    byte[] image = null;
    Account account;
    AccountRepository accountRepo = new AccountRepository();

    public Profile(Account acc) {
        initComponents();
        this.account = acc;
        if (account.getProfile().getImage() == null) {
            profile_image.setIcon(new ImageIcon(
                    ImageUtils.convertByteToImage(new ResourceRepository().getImageByName("user"))
                            .getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        } else {
            profile_image.setIcon(new ImageIcon(
                    ImageUtils.convertByteToImage(account.getProfile().getImage())
                            .getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        }
        profile_image_camera_icon.setIcon(new ImageIcon(
                ImageUtils.convertByteToImage(new ResourceRepository().getImageByName("camera"))
                        .getScaledInstance(73, 48, Image.SCALE_SMOOTH)));
        profile_form_input_fullname.setText(account.getProfile().getName());
        profile_form_input_phone.setText(account.getProfile().getPhone());
        profile_form_input_address.setText(account.getProfile().getAddress());
        profile_form_email_button.setText(account.getEmail());
        profile_form_birthday_button.setText(new SimpleDateFormat("dd-MM-yyyy").format(account.getProfile().getBirthday()));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new User(account);
            }

        });
        profile_image.requestFocusInWindow();
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
        profile_image = new Business.Swing.Component.Subcomponent.ImageAvatar();
        profile_image_camera = new javax.swing.JPanel();
        profile_image_camera_icon = new javax.swing.JLabel();
        profile_form_container = new javax.swing.JPanel();
        profile_form = new javax.swing.JPanel();
        profile_form_input_fullname_label = new javax.swing.JLabel();
        profile_form_input_fullname = new javax.swing.JTextField();
        profile_form_input_phone_label = new javax.swing.JLabel();
        profile_form_input_phone = new javax.swing.JTextField();
        profile_form_input_address_label = new javax.swing.JLabel();
        profile_form_input_address = new javax.swing.JTextField();
        profile_form_email_label = new javax.swing.JLabel();
        profile_form_email_button = new javax.swing.JButton();
        profile_form_birthday_label = new javax.swing.JLabel();
        profile_form_button_changePass = new javax.swing.JButton();
        profile_form_button_save = new javax.swing.JButton();
        profile_form_birthday_button = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Profile");
        setResizable(false);

        main_container.setBackground(new java.awt.Color(240, 240, 240));
        main_container.setPreferredSize(new java.awt.Dimension(1024, 768));

        profile_image.setBackground(new java.awt.Color(204, 204, 204));
        profile_image.setOpaque(true);
        profile_image.setPreferredSize(new java.awt.Dimension(200, 200));

        profile_image_camera.setBackground(new java.awt.Color(255, 255, 255));
        profile_image_camera.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        profile_image_camera.setPreferredSize(new java.awt.Dimension(75, 50));
        profile_image_camera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profile_image_cameraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout profile_image_cameraLayout = new javax.swing.GroupLayout(profile_image_camera);
        profile_image_camera.setLayout(profile_image_cameraLayout);
        profile_image_cameraLayout.setHorizontalGroup(
            profile_image_cameraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profile_image_cameraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(profile_image_camera_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        profile_image_cameraLayout.setVerticalGroup(
            profile_image_cameraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profile_image_cameraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(profile_image_camera_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        profile_image.add(profile_image_camera);
        profile_image_camera.setBounds(220, 250, 75, 50);

        profile_form_container.setBackground(new java.awt.Color(240, 240, 240));
        profile_form_container.setPreferredSize(new java.awt.Dimension(500, 768));

        profile_form.setBackground(new java.awt.Color(102, 255, 204));
        profile_form.setPreferredSize(new java.awt.Dimension(450, 600));

        profile_form_input_fullname_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        profile_form_input_fullname_label.setText("Fullname");
        profile_form_input_fullname_label.setPreferredSize(new java.awt.Dimension(400, 50));

        profile_form_input_fullname.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        profile_form_input_fullname.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        profile_form_input_fullname.setPreferredSize(new java.awt.Dimension(80, 40));

        profile_form_input_phone_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        profile_form_input_phone_label.setText("Phone");
        profile_form_input_phone_label.setPreferredSize(new java.awt.Dimension(400, 50));

        profile_form_input_phone.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        profile_form_input_phone.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        profile_form_input_phone.setPreferredSize(new java.awt.Dimension(80, 40));

        profile_form_input_address_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        profile_form_input_address_label.setText("Address");
        profile_form_input_address_label.setPreferredSize(new java.awt.Dimension(400, 50));

        profile_form_input_address.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        profile_form_input_address.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        profile_form_input_address.setPreferredSize(new java.awt.Dimension(80, 40));

        profile_form_email_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        profile_form_email_label.setText("Email:");
        profile_form_email_label.setPreferredSize(new java.awt.Dimension(70, 50));

        profile_form_email_button.setBackground(new java.awt.Color(0, 204, 204));
        profile_form_email_button.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        profile_form_email_button.setText("DuyLPCE181153@fpt.edu.vn");
        profile_form_email_button.setBorder(null);
        profile_form_email_button.setContentAreaFilled(false);
        profile_form_email_button.setFocusPainted(false);
        profile_form_email_button.setPreferredSize(new java.awt.Dimension(330, 50));
        profile_form_email_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_form_email_buttonActionPerformed(evt);
            }
        });

        profile_form_birthday_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        profile_form_birthday_label.setText("Birthday:");
        profile_form_birthday_label.setPreferredSize(new java.awt.Dimension(70, 50));

        profile_form_button_changePass.setBackground(new java.awt.Color(255, 204, 0));
        profile_form_button_changePass.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        profile_form_button_changePass.setText("Change Password");
        profile_form_button_changePass.setBorder(null);
        profile_form_button_changePass.setBorderPainted(false);
        profile_form_button_changePass.setFocusPainted(false);
        profile_form_button_changePass.setPreferredSize(new java.awt.Dimension(350, 50));
        profile_form_button_changePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_form_button_changePassActionPerformed(evt);
            }
        });

        profile_form_button_save.setBackground(new java.awt.Color(0, 204, 204));
        profile_form_button_save.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 36)); // NOI18N
        profile_form_button_save.setText("SAVE");
        profile_form_button_save.setBorder(null);
        profile_form_button_save.setBorderPainted(false);
        profile_form_button_save.setFocusPainted(false);
        profile_form_button_save.setPreferredSize(new java.awt.Dimension(450, 50));
        profile_form_button_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_form_button_saveActionPerformed(evt);
            }
        });

        profile_form_birthday_button.setBackground(new java.awt.Color(102, 255, 204));
        profile_form_birthday_button.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        profile_form_birthday_button.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        profile_form_birthday_button.setText("30-10-2004");
        profile_form_birthday_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout profile_formLayout = new javax.swing.GroupLayout(profile_form);
        profile_form.setLayout(profile_formLayout);
        profile_formLayout.setHorizontalGroup(
            profile_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profile_formLayout.createSequentialGroup()
                .addGroup(profile_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profile_formLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(profile_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(profile_formLayout.createSequentialGroup()
                                .addComponent(profile_form_birthday_label, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(profile_form_birthday_button))
                            .addGroup(profile_formLayout.createSequentialGroup()
                                .addComponent(profile_form_email_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(profile_form_email_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(profile_form_input_address_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profile_form_input_address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profile_form_input_phone_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profile_form_input_phone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profile_form_input_fullname_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profile_form_input_fullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(profile_formLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(profile_form_button_changePass, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(profile_form_button_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        profile_formLayout.setVerticalGroup(
            profile_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profile_formLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(profile_form_input_fullname_label, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(profile_form_input_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(profile_form_input_phone_label, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(profile_form_input_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(profile_form_input_address_label, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(profile_form_input_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(profile_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profile_form_email_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profile_form_email_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(profile_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profile_form_birthday_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profile_form_birthday_button))
                .addGap(15, 15, 15)
                .addComponent(profile_form_button_changePass, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(profile_form_button_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout profile_form_containerLayout = new javax.swing.GroupLayout(profile_form_container);
        profile_form_container.setLayout(profile_form_containerLayout);
        profile_form_containerLayout.setHorizontalGroup(
            profile_form_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profile_form_containerLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(profile_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        profile_form_containerLayout.setVerticalGroup(
            profile_form_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profile_form_containerLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(profile_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout main_containerLayout = new javax.swing.GroupLayout(main_container);
        main_container.setLayout(main_containerLayout);
        main_containerLayout.setHorizontalGroup(
            main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_containerLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(profile_image, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(profile_form_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        main_containerLayout.setVerticalGroup(
            main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_containerLayout.createSequentialGroup()
                .addGroup(main_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profile_form_container, 762, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(main_containerLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(profile_image, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void profile_image_cameraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profile_image_cameraMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                image = Files.readAllBytes(file.toPath());
                profile_image.setIcon(
                        new ImageIcon(ImageUtils.convertByteToImage(image).getScaledInstance(300, 300, Image.SCALE_SMOOTH))
                );
                this.repaint();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_profile_image_cameraMouseClicked

    private void profile_form_button_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_form_button_saveActionPerformed
        String name = profile_form_input_fullname.getText().trim();
        String phone = profile_form_input_phone.getText().trim();
        String address = profile_form_input_address.getText().trim();
        String birthday = profile_form_birthday_button.getText();
        if (name.equals("")
                || phone.equals("")
                || address.equals("")
                || birthday.equals("")) {
            JOptionPane.showMessageDialog(this, "All field must be fulfilled", "User error", JOptionPane.OK_OPTION);
            return;
        }

        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Phone must be 10 digits", "User error", JOptionPane.OK_OPTION);
            return;
        }

        if (!birthday.matches("\\d{2}-\\d{2}-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Must be in date form (dd-MM-yyyy)!", "User error", JOptionPane.OK_OPTION);
            return;
        }
        try {
            if (new SimpleDateFormat("dd-MM-yyyy").parse(birthday).after(new Date())) {
                throw new Exception();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Date is invalid!", "User error", JOptionPane.OK_OPTION);
            return;
        }
        accountRepo.updateProfile(account, ImageUtils.compressImageFromWinform(image), birthday, name, phone, address);
        account = accountRepo.getAccountById(account.getId());
        this.dispose();
        new Profile(account);
    }//GEN-LAST:event_profile_form_button_saveActionPerformed

    private void profile_form_email_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_form_email_buttonActionPerformed
        try {
            URI uri = new URI("http://localhost:8080/Product/Add");
            Desktop dt = Desktop.getDesktop();
            dt.browse(uri);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_profile_form_email_buttonActionPerformed

    private void profile_form_button_changePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_form_button_changePassActionPerformed
        try {
            URI uri = new URI("http://localhost:8080/auth/changePassword");
            Desktop dt = Desktop.getDesktop();
            dt.browse(uri);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_profile_form_button_changePassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel main_container;
    private javax.swing.JPanel profile_form;
    private javax.swing.JTextField profile_form_birthday_button;
    private javax.swing.JLabel profile_form_birthday_label;
    private javax.swing.JButton profile_form_button_changePass;
    private javax.swing.JButton profile_form_button_save;
    private javax.swing.JPanel profile_form_container;
    private javax.swing.JButton profile_form_email_button;
    private javax.swing.JLabel profile_form_email_label;
    private javax.swing.JTextField profile_form_input_address;
    private javax.swing.JLabel profile_form_input_address_label;
    private javax.swing.JTextField profile_form_input_fullname;
    private javax.swing.JLabel profile_form_input_fullname_label;
    private javax.swing.JTextField profile_form_input_phone;
    private javax.swing.JLabel profile_form_input_phone_label;
    private Business.Swing.Component.Subcomponent.ImageAvatar profile_image;
    private javax.swing.JPanel profile_image_camera;
    private javax.swing.JLabel profile_image_camera_icon;
    // End of variables declaration//GEN-END:variables
}
