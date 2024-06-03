/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Swing.Component.Subcomponent;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author ADMIN
 */
public class ImageAvatar extends JComponent {

    private Icon icon;
    private int borderSize;

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (icon != null) {
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height);
            int x = width / 2 - diameter / 2;
            int y = height / 2 - diameter / 2;
            Dimension size = getAutoSize(icon, diameter);
            BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2_img = img.createGraphics();
            g2_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2_img.fillOval(0, 0, diameter, diameter);
            Composite composite = g2_img.getComposite();
            g2_img.setComposite(AlphaComposite.SrcIn);
            g2_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2_img.drawImage(toImage(icon), 0, 0, size.width, size.height, null);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, x, y, null);
        }
    }

    private Dimension getAutoSize(Icon image, int size) {
        int w = size;
        int h = size;
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        return new Dimension(width, height);
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
}
