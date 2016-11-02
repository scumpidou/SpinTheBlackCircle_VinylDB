package VinDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Vinyl_server extends JFrame{
  /**
   * 
   */
  private static final long serialVersionUID = -5513438685857629543L;
  //elemente
  JPanel Buttons=new JPanel();
  JPanel Output=new JPanel();
  JPanel File=new JPanel();
  static TextArea outputfield=new TextArea();
  //JButton search=new JButton("Eintrag suchen(einfach)");
  JButton search_complex=new JButton("Eintrag suchen");
  JButton create=new JButton("Eintrag anlegen");
  JButton about=new JButton("Über");
  JButton save=new JButton("DB speichern");
  JButton load=new JButton("DB laden");
  JButton exit=new JButton("DB verlassen");
  JButton dump=new JButton("DB Übersicht");
  //JButton keyword=new JButton("Stichwortsuche");
  //JLabel label = new JLabel(new ImageIcon("Vinyl-Special-340x255.jpg"));
  Vinyl_server(){
    /////////////////////////////////////////////////////////////////////////
    ///// STYLING ///////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    this.setTitle("Spin the Black Circle-Vinyl DB");
    this.setSize(700, 600);
    outputfield.setSize(500, 500);
    outputfield.setEditable(false);
    outputfield.setBackground(new Color(0,0,0));
    outputfield.setForeground(new Color(255,255,255));
    Output.add(outputfield);
    Output.setBorder(javax.swing.BorderFactory.createMatteBorder(50, 400, 200, 400, new ImageIcon(getClass().getResource("Vinyl-Special-340x255.jpg"))));
    Output.setBackground(new Color(200,150,100));
    Buttons.setBackground(new Color(120,120,120));
    Buttons.setBorder(BorderFactory.createRaisedBevelBorder());
    exit.setBackground(new Color(255,0,0));
    exit.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    File.setBackground(new Color(120,120,120));
    File.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    /////////////////////////////////////////////////////////////////////////
    //add elements to panels ////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //Buttons.add(search);
    Buttons.add(search_complex);
    Buttons.add(create);
    Buttons.add(about);
    //Buttons.add(keyword);
    File.add(dump);
    //File.add(keyword);
    File.add(save);
    File.add(load);
    File.add(exit);
    this.add(Buttons);
    this.add(Output);
    this.add(File);
    ////////////////////////////////////////////////////////////////////////
    ////// general layout //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    setLayout(new BorderLayout()) ;
    add("North",Buttons) ;
    add("Center",Output) ;
    add("South",File);
    this.setVisible(true);
    outputfield.append("Keine Platte gewählt......");
    /////////////////////////////////////////////////////////////////////////////////
    //add actions////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    exit.addActionListener(new exit_listener());
    //search.addActionListener(new search_listener());
    search_complex.addActionListener(new complex_listener());
    save.addActionListener(new save_listener());
    load.addActionListener(new load_listener());
    about.addActionListener(new about_listener());
    create.addActionListener(new create_listener());
    dump.addActionListener(new dump_listener());
    //keyword.addActionListener(new keyword_listener());
  }
  static void printrecord(Record to_print){
    //Daten auslesen
    String artist=to_print.getArtist();
    String title=to_print.getTitle();
    String style=to_print.getStyle();
    String length=to_print.getLength();
    String position=to_print.getPosition();
    String comments=to_print.getComments();
    //falls nix gefunden
    if(artist==""&&title==""&&style==""&&length==""&&position==""&&comments==""){
      return;
    }
    //output auf konsole
    outputfield.append("\n"+artist);
    outputfield.append("\n");
    outputfield.append(title);
    outputfield.append("\n");
    outputfield.append(to_print.getStyle());
    outputfield.append("\n"+length);
    outputfield.append("\n"+position);
    outputfield.append("\n"+comments);
    //output in zusätzliches Fenster
    JFrame output=new JFrame("Gefundene Platte");
    JPanel panel=new JPanel();
    JPanel Buttons=new JPanel();
    Buttons.setBorder(BorderFactory.createBevelBorder(1));
    Buttons.setBackground(new Color(50,50,50));
    JButton edit=new JButton("Änderungen speichern");
    JButton delete=new JButton("Eintrag löschen !");
    JButton exit=new JButton("Fenster Schließen");
    Buttons.add(edit);
    Buttons.add(delete);
    Buttons.add(exit);
    JTextField artist_field=new JTextField(artist);
    artist_field.setEditable(true);
    artist_field.setBackground(new Color(0,0,0));
    artist_field.setForeground(new Color(250,250,250));
    JTextField style_field=new JTextField(style);
    style_field.setEditable(true);
    style_field.setBackground(new Color(0,0,0));
    style_field.setForeground(new Color(250,250,250));
    JTextField title_field=new JTextField(title);
    title_field.setEditable(true);
    title_field.setBackground(new Color(0,0,0));
    title_field.setForeground(new Color(250,250,250));
    JTextField length_field=new JTextField(length);
    length_field.setEditable(true);
    length_field.setBackground(new Color(0,0,0));
    length_field.setForeground(new Color(250,250,250));
    JTextField position_field=new JTextField(position);
    position_field.setBackground(new Color(0,0,0));
    position_field.setForeground(new Color(250,250,250));
    position_field.setEditable(true);
    JTextField comments_field=new JTextField(comments);
    comments_field.setEditable(true);
    comments_field.setBackground(new Color(0,0,0));
    comments_field.setForeground(new Color(250,250,250));
    Color white=new Color(255,255,255);
    JLabel kuenstler=new JLabel("Künstler:");
    kuenstler.setForeground(new Color(255,255,255));
    panel.add(kuenstler);
    panel.add(artist_field);
    JLabel titel=new JLabel("Titel:");
    titel.setForeground(new Color(255,255,255));
    panel.add(titel);
    panel.add(title_field);
    JLabel stil=new JLabel("Musikstil:");
    stil.setForeground(new Color(255,255,255));
    panel.add(stil);
    panel.add(style_field);
    JLabel format=new JLabel("Format:");
    format.setForeground(white);
    panel.add(format);
    panel.add(length_field);
    JLabel positionier=new JLabel("Position:");
    positionier.setForeground(white);
    panel.add(positionier);
    panel.add(position_field);
    JLabel kommentar=new JLabel("Kommentare:");
    kommentar.setForeground(white);
    panel.add(kommentar);
    panel.add(comments_field);
    panel.setBackground(new Color(51,25,0));
    panel.setForeground(new Color(255,255,255));
    output.setSize(800, 600);
    panel.setLayout(new GridLayout (0,2/*,6,3*/));
    panel.setBorder(BorderFactory.createRaisedBevelBorder());
    output.setLayout(new BorderLayout());
    output.add("North",Buttons);
    output.add("Center",panel);
    Buttons.setBackground(new Color(200,150,100));
    exit.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // Fenster Schließen
        output.dispose();
      }
      
    });
    delete.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // Eintrag Löschen
        new OK_window("delete",to_print,to_print.getID());
        output.dispose();
      }
      
    });
    edit.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String artist_transfer=artist_field.getText();
        String title_transfer=title_field.getText();
        String length_transfer=length_field.getText();
        String style_transfer=style_field.getText();
        String position_transfer=position_field.getText();
        String comments_transfer=comments_field.getText();
        Record transfer=new Record( artist_transfer,
                                    title_transfer,
                                    length_transfer,
                                    style_transfer,
                                    position_transfer,
                                    comments_transfer);
        new OK_window("edit",transfer,to_print.getID());
      }
      
    });
    output.getRootPane().setDefaultButton(exit);
    if(RecordDB.action_flag){output.setVisible(true);}
  }
  static void printstring(String textout){
    outputfield.append(textout);
  }
  //actionlistener:
  class exit_listener implements ActionListener
  { public void actionPerformed(ActionEvent e)  { new OK_window("exit"); }
  } // end class 
  
  class complex_listener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      new complex_search_window();
    }
  }
  class save_listener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      RecordDB.saveDB();
    }
    
  }
  class load_listener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      RecordDB.loadDB();
    }
    
  }
  class about_listener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent arg0) {
      // TODO Auto-generated method stub
      new about_window();
    }
    
  }
  class create_listener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      new create_window();
    }
    
  }
  class dump_listener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      int counter=0;
      Vinyl_server.printstring("\n------------------------------------------------------------------");
      Vinyl_server.printstring("\nUnsortierte Liste ALLER Einträge:");
      Vinyl_server.printstring("\n------------------------------------------------------------------");
      for(Record seek:RecordDB.allRecords){
        Vinyl_server.printstring("\n"+seek.getArtist()+"-"+seek.getTitle());
        counter++;
      }
      Vinyl_server.printstring("\n------------------------------------------------------------------\nAnzahl Einträge:"+counter);
    }
    
  }
  

}
