package VinDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class create_window extends JFrame{
  /**
   * 
   */
  private static final long serialVersionUID = 7804993052150007664L;
  Panel Felder=new Panel();
  JPanel Buttons=new JPanel();
  JButton create=new JButton("anlegen");
  JButton exit=new JButton("Fenster Schließen");
  JTextField artist;
  JTextField title;
  JTextField length;
  JTextField style;
  JTextField position;
  JTextField comments;
  String artist_entered="";
  String title_entered="";
  String length_entered="";
  String style_entered="";
  String position_entered="";
  String comments_entered="";
  //String ID_created="";
  create_window(){
    //genereller style
    this.setSize(600, 700);
    this.getRootPane().setDefaultButton(create);
    this.setTitle("Eintrag anlegen");
    Buttons.setBackground(new Color(150,50,50));
    Buttons.setBorder(javax.swing.BorderFactory.createMatteBorder(50, 400, 200, 400, 
        new ImageIcon(getClass().getResource("Vinyl-Special-340x255.jpg"))));
    Felder.setBackground(new Color(200,200,200));
    //buttons an panel binden
    Buttons.add(create);
    Buttons.add(exit);
    //textfelder an String binden
    artist=new JTextField(artist_entered);
    title=new JTextField(title_entered);
    length=new JTextField(length_entered);
    style=new JTextField(style_entered);
    position=new JTextField(position_entered);
    comments=new JTextField(comments_entered);
    //textfelder stylen
    artist.setBackground(new Color(0,0,0));
    artist.setForeground(new Color(255,255,255));
    title.setBackground(new Color(0,0,0));
    title.setForeground(new Color(255,255,255));
    length.setBackground(new Color(0,0,0));
    length.setForeground(new Color(255,255,255));
    style.setBackground(new Color(0,0,0));
    style.setForeground(new Color(255,255,255));
    position.setBackground(new Color(0,0,0));
    position.setForeground(new Color(255,255,255));
    comments.setBackground(new Color(0,0,0));
    comments.setForeground(new Color(255,255,255));
    //ID wird kreiert, dient zur Überprüfung von Doubletten
    //textfelder an Panel binden
    Felder.add(new JLabel("Künstler:"));
    Felder.add(artist);
    Felder.add(new JLabel("Titel:"));
    Felder.add(title);
    Felder.add(new JLabel("Typ(LP/EP/Single):"));
    Felder.add(length);
    Felder.add(new JLabel("Musikrichtung:"));
    Felder.add(style);
    Felder.add(new JLabel("Position:"));
    Felder.add(position);
    Felder.add(new JLabel("Kommentar:"));
    Felder.add(comments);
    //Layout der panels
    Felder.setLayout(new GridLayout (7,6,6,5));
    setLayout(new BorderLayout()) ;
    add("North",Buttons) ;
    add("Center",Felder);
    //actions
    exit.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        dispose();
      }
      
    });
    create.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        String[] inhalt=new String[6];
        inhalt[0]=artist.getText();
        inhalt[1]=title.getText();
        inhalt[2]=length.getText();
        inhalt[3]=style.getText();
        inhalt[4]=position.getText();
        inhalt[5]=comments.getText();
        String ID=inhalt[0]+inhalt[1]+inhalt[3];
        if(ID==""){
          Vinyl_server.printstring("Zu wenig Informationen um Eintrag\nzu generieren!");
          dispose();
        }
        if(RecordDB.checkID(ID)){
           Vinyl_server.printstring("\nEintrag schon vorhanden");
           dispose();
        }
        else{
          //TODO:hier eigentlicher Eintrag in DB
          Record tmp=new Record(inhalt[0],inhalt[1],inhalt[2],inhalt[3],inhalt[4],inhalt[5]);
          RecordDB.addRecord(tmp);
          dispose();
        }
      }
      
    });
    //Fenster anzeigen
    this.setVisible(true);
  }
}
