package com.dina.studentmaster.view;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private final Image image;
    public BackgroundPanel() {
        var url = getClass().getResource("/images/background.png");
        image = url == null ? null : new ImageIcon(url).getImage();
        setLayout(new BorderLayout(14,14));
    }
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) g.drawImage(image,0,0,getWidth(),getHeight(),this);
    }
}
