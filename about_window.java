package VinDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class about_window extends JFrame{
  /**
   * 
   */
  private static final long serialVersionUID = -4987414322927715949L;
  JPanel panel=new JPanel();
  JLabel label=new JLabel("(c) by C.Schuch (2016)");
  JLabel titel=new JLabel("Spin the Black Circle Vinyl DataBase");
  JLabel version=new JLabel("Version 1.3");
  JButton close=new JButton("Fenster schlieﬂen");
  about_window(){
    this.setSize(400,400);
    this.getRootPane().setDefaultButton(close);
    Font boldFont = new Font(label.getFont().getFontName(), Font.BOLD, /*label.getFont().getSize()*/16);
    label.setFont(boldFont);
    titel.setFont(boldFont);
    panel.add(titel);
    panel.add(label);
    panel.add(version);
    panel.add(close);
    panel.setBackground(new Color(0,0,0));
    panel.setForeground(new Color(250,250,0));
    label.setForeground(new Color(250,250,0));
    panel.setBorder(javax.swing.BorderFactory.createMatteBorder(50, 400, 200, 400, 
        new ImageIcon(getClass().getResource("Vinyl-Special-340x255.jpg"))));
    setLayout(new BorderLayout());
    add("Center",panel);
    close.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        dispose();
      }
      
    });
    this.setVisible(true);
  }

}
