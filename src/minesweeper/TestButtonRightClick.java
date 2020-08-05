package minesweeper;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class TestButtonRightClick {
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            final JButton button = new JButton("Test Me!");
            button.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent e) {
                  System.out.println("ActionListener invoked");
               }
            });
            button.addMouseListener(new MouseAdapter() {
               @Override
               public void mousePressed(MouseEvent e) {
                  if (e.getButton() == MouseEvent.BUTTON3) {
                     System.out.println("Right Button Pressed");
                     button.setBackground(Color.green);
                  }
               }
            });

            JPanel panel = new JPanel();
            panel.add(button);
            JOptionPane.showMessageDialog(null, panel);
         }
      });
   }
}