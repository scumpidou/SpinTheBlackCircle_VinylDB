package VinDB;

import javax.swing.*;

import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class selection_window extends JFrame{

  /**
   * 
   */
  private static final long serialVersionUID = 6747879464180251110L;
  JButton select=new JButton("OK");
  JPanel panel=new JPanel();
  @SuppressWarnings({ "rawtypes", "unchecked" })
  selection_window(){
    this.setSize(800, 300);
    this.getRootPane().setDefaultButton(select);
    String comboBoxListe[]=new String[RecordDB.temp_size];
    int i=0;
    for(Record seek:RecordDB.temp_list){
      comboBoxListe[i]=seek.getArtist()+seek.getTitle()+seek.getLength();
      i++;
    }
    Arrays.sort(comboBoxListe);
    JComboBox dropdown=new JComboBox(comboBoxListe);
    panel.setBorder(javax.swing.BorderFactory.createMatteBorder(50, 400, 200, 400, new ImageIcon(getClass().getResource("Vinyl-Special-340x255.jpg"))));
    JLabel text=new JLabel("["+i+"] Treffer."+"Bitte Genauere Auswahl treffen");
    text.setForeground(new Color(255,255,255));
    panel.add(text);
    panel.add(dropdown);
    select.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String result_selection=(String)dropdown.getSelectedItem();
        Record resulting_record=new Record();
        resulting_record=RecordDB.searchRecord("ID",result_selection);
        RecordDB.action_flag=true;
        Vinyl_server.printrecord(resulting_record);
        dispose();
      }
      
    });
    panel.add(select);
    panel.setBackground(new Color(150,150,150));
    dropdown.setBackground(new Color(0,0,0));
    dropdown.setForeground(new Color(250,250,250));
    dropdown.setSize(400, 40);
    select.setBackground(new Color(150,100,100));
    setLayout(new BorderLayout()) ;
    add("Center",panel) ;
    
    this.setVisible(true);
  }
}
