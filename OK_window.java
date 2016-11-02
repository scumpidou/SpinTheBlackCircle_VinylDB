package VinDB;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class OK_window extends JFrame{
  /**
   * 
   */
  private static final long serialVersionUID = 8917613479905470190L;
  JButton OK=new JButton("OK");
  JButton cancel=new JButton("Abbruch");
  JLabel dialog=new JLabel("Wollen Sie das wirklich tun?");
  JPanel panel1=new JPanel();
  JPanel panel2=new JPanel();
  /**
   * @param action defines what to do: save or load (can be <i>load</i> or <i>save</i> or exit
   */
  OK_window(String action){
    this.setSize(400, 400);
    this.getRootPane().setDefaultButton(OK);
    //OK.setBackground(new Color(178,34,34));
    //OK.setForeground(new Color(250,250,250));
    panel1.setBackground(new Color(255,165,79));
    panel1.setBorder(BorderFactory.createBevelBorder(1));
    panel1.add(dialog);
    panel2.setBorder(javax.swing.BorderFactory.createMatteBorder(50, 400, 200, 400, new ImageIcon(getClass().getResource("Vinyl-Special-340x255.jpg"))));
    panel2.setBackground(new Color(139,87,66));
    panel2.add(OK);
    panel2.add(cancel);
    setLayout(new BorderLayout());
    add("North",panel1);
    add("Center",panel2);
    //actions
    cancel.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        dispose();
      }
      
    });
    
    OK.addActionListener(new ActionListener(){

      @SuppressWarnings("unchecked")
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(action=="save"){
          JFileChooser saver=new JFileChooser(RecordDB.DBpath);
          saver.setDialogTitle("Speichern. Wählen Sie eine Datei");
          saver.setOpaque(true);
          saver.setBackground(new Color(139,87,66));
          String input="";
          int rueckgabeWert = saver.showOpenDialog(null);
          /* Abfrage, ob auf "Öffnen" geklickt wurde */
          if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
          {
            input=saver.getSelectedFile().getName();
          }
          if(!input.equals("")){
            RecordDB.DBpath=saver.getCurrentDirectory().toString();
            //RecordDB.Path_flag=true;
            try {
              FileOutputStream save=new FileOutputStream(input);
              ObjectOutputStream backup=new ObjectOutputStream(save);
              backup.writeObject(RecordDB.allRecords);
              backup.close();
              save.close();
            } catch (FileNotFoundException e2) {
              // TODO Auto-generated catch block
              e2.printStackTrace();
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
              }
            //saver.writeObject(tmp);
            //setChanged(false);
            Vinyl_server.printstring("\nDB auf Disk gespeichert");
          }
          dispose();
      }
        if(action=="load"){
          JFileChooser loader=new JFileChooser(RecordDB.DBpath);
          loader.setDialogTitle("Laden. Wählen Sie eine Datei");
          loader.setOpaque(true);
          loader.setBackground(new Color(139,87,66));
          String input="";
          int rueckgabeWert = loader.showOpenDialog(null);
          /* Abfrage, ob auf "Öffnen" geklickt wurde */
          if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
          {
            input=loader.getSelectedFile().getName();
          }
          if(!input.equals("")){
            RecordDB.DBpath=loader.getCurrentDirectory().toString();
            //RecordDB.Path_flag=true;
            try {
              FileInputStream load=new FileInputStream(input);
              ObjectInputStream restore=new ObjectInputStream(load);
              RecordDB.allRecords=(List<Record>) restore.readObject();
              restore.close();
              load.close();
              Vinyl_server.printstring("\nDB geladen");
            } catch (FileNotFoundException e2) {
              // TODO Auto-generated catch block
              e2.printStackTrace();
            } catch (IOException e2) {
              // TODO Auto-generated catch block
              e2.printStackTrace();
            } catch (ClassNotFoundException e2) {
              // TODO Auto-generated catch block
              e2.printStackTrace();
            }
            //setChanged(false);
          }
          dispose();

        }
        if(action=="exit"){
          System.exit(0);
        }
      }
    });
    this.setVisible(true);
    }
  /**für DB ops
   * @param action entweder delete oder edit
   * @param transfer Platte, auf die sich aktion bezieht
   */
  OK_window(String action,Record transfer,String ID_transfer){
    this.setSize(400, 200);
    //OK.setBackground(new Color(178,34,34));
    //OK.setForeground(new Color(250,250,250));
    panel1.setBackground(new Color(255,165,79));
    panel1.setBorder(BorderFactory.createBevelBorder(1));
    panel1.add(dialog);
    panel2.setBackground(new Color(139,87,66));
    panel2.add(OK);
    panel2.add(cancel);
    setLayout(new BorderLayout());
    add("North",panel1);
    add("Center",panel2);
    //actions
    cancel.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        dispose();
      }
      
    });
    
    OK.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(action=="delete"){
          RecordDB.deleteRecord(transfer.getID());
          dispose();
        }
        if(action=="edit"){
          RecordDB.deleteRecord(ID_transfer);
          RecordDB.addRecord(transfer);
          dispose();
        }
      }
    });
    
    this.setVisible(true);
    }
  }

