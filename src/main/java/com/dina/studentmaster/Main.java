package com.dina.studentmaster;
import com.dina.studentmaster.view.StudentFrame;
import javax.swing.*;
public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}catch(Exception ignored){} new StudentFrame().setVisible(true);});
    }
}
