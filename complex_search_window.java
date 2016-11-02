package VinDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class complex_search_window extends JFrame{
  /**
   * 
   */
  private static final long serialVersionUID = -1650020670039323548L;
  //suche mit kombination von Begriffen
  Panel Felder=new Panel();
  JPanel Buttons=new JPanel();
  JButton search=new JButton("Suche starten");
  JButton exit=new JButton("Fenster Schlieﬂen");
  JTextField artist;
  JTextField title;
  JTextField length;
  String first="";
  String second="";
  String third="";
  complex_search_window(){
    this.setSize(600, 500);
    this.getRootPane().setDefaultButton(search);
    this.setTitle("Eintragsuche(bis zu 3 Suchbegriffe)");
    Buttons.setBackground(new Color(150,50,50));
    Felder.setBackground(new Color(200,200,200));
    Buttons.setBorder(javax.swing.BorderFactory.createMatteBorder(50, 400, 200, 400, 
        new ImageIcon(getClass().getResource("Vinyl-Special-340x255.jpg"))));
    artist=new JTextField(first);
    title=new JTextField(second);
    length=new JTextField(third);
    artist.setBackground(new Color(0,0,0));
    artist.setForeground(new Color(255,255,255));
    title.setBackground(new Color(0,0,0));
    title.setForeground(new Color(255,255,255));
    length.setBackground(new Color(0,0,0));
    length.setForeground(new Color(255,255,255));
    Felder.add(new JLabel("Erster Suchbegriff:"));
    Felder.add(artist);
    Felder.add(new JLabel("(Zweiter Suchbegriff:)"));
    Felder.add(title);
    Felder.add(new JLabel("(Dritter Suchbegriff:)"));
    Felder.add(length);
    Felder.setLayout(new GridLayout (0,2,6,3));
    Buttons.add(new JLabel("(Geben Sie bis zu 3 unterschiedliche Suchbegriffe ein):"));
    Buttons.add(search);
    Buttons.add(exit);
    setLayout(new BorderLayout()) ;
    add("North",Buttons) ;
    add("Center",Felder);
    exit.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        dispose();
      }
      
    });
    search.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Record result=new Record();
        ArrayList<Record> results=new ArrayList<Record>();
        String search_first=artist.getText();
        String search_second=title.getText();
        String search_third=length.getText();
        //Record temp=new Record();
        if(!search_first.equals("")&&!search_second.equals("")&&!search_third.equals("")){
          results=RecordDB.keyword3_search(search_first,search_second,search_third);
          RecordDB.temp_list=results;
          RecordDB.temp_size=RecordDB.temp_list.size();
          new selection_window();
          Vinyl_server.printrecord(result);
        }
        if(!search_first.equals("")&&!search_second.equals("")&&search_third.equals("")){
          results=RecordDB.keyword2_search(search_first,search_second);
          RecordDB.temp_list=results;
          RecordDB.temp_size=RecordDB.temp_list.size();
          new selection_window();
          //Vinyl_server.printrecord(result);
        }
        if(!search_first.equals("")&&search_second.equals("")&&search_third.equals("")){
          RecordDB.temp_list=new ArrayList<Record>();
          RecordDB.keyword_search(search_first);
          //Vinyl_server.printrecord(result);
        }
        dispose();
      }
      
    });
    this.setVisible(true);
  }
  

}
