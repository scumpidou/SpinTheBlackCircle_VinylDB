package VinDB;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**Enthält die Platteneinträge als Liste
 * @author christian
 *
 */
public class RecordDB implements Serializable{
  /**
   * 
   */
  //GLOBALE VARIABLEN
  private static final long serialVersionUID = 3023365851668462379L;
  public static List<Record> allRecords=new ArrayList<Record>();
  public static List<Record> temp_list=new ArrayList<Record>();
  public static Record temp_result=new Record();
  public static boolean action_flag=false;
  public static boolean OK_flag=false;
  public static int temp_size;
  private static boolean changed;
  public static boolean Path_flag=false;
  public static String DBpath;
  /**standardkonstruktor
   * 
   */
  RecordDB(){
    allRecords=new ArrayList<Record>();
    setChanged(true);
  }
  /**konstruktor mit gefüllter Liste
   * @param transfer
   */
  RecordDB(List<Record> transfer){
    setAllRecords(transfer);
    setChanged(true);
  }
  /**konstruktor mit einem Eintrag
   * @param firstrecord
   */
  RecordDB(Record firstrecord) {
    // TODO Auto-generated constructor stub
    allRecords=new ArrayList<Record>();
    addRecord(firstrecord);
  }
  /**
   * @return the allRecords List
   */
  public static List<Record> getAllRecords() {
    return allRecords;
  }
  /**
   * @param allRecords the allRecords List to set
   */
  public static void setAllRecords(List<Record> newallRecords) {
    allRecords = newallRecords;
    setChanged(true);
  }
  /**
   * @param newRecord to be added
   */
  public static void addRecord(Record newRecord){
    boolean already_there=false;
    for(Record seek:allRecords){
      if(seek.getID().equals(newRecord.getID())){
        already_there=true;
        Vinyl_server.printstring("\nPlatte schon in der Datenbank!");
      }
    }
    if(!already_there){
      allRecords.add(newRecord);
      Vinyl_server.printstring("\nfolgende Platte wurde eingetragen:");
      Vinyl_server.printrecord(newRecord);
      setChanged(true);
    }
  }
  /**
   * @param to_delete record to be deleted
   */
  public static void deleteRecord(String id_to_delete){
    int index=0;
    for(Record seek:allRecords){
      if(seek.getID()==id_to_delete){
        index=allRecords.indexOf(seek);
      }
    }
    allRecords.remove(index);
    setChanged(true);
  }
  public static void saveDB(){
    //speichern der DB wird in OK_window erledigt
    new OK_window("save");
  }
  public static void loadDB(){
  //Laden wird in OK_window erledigt
    new OK_window("load");
  }
  /**
   * @return the changed
   */
  public static boolean isChanged() {
    return changed;
  }
  /**
   * @param changed the changed to set
   */
  public static void setChanged(boolean newchanged) {
    changed = newchanged;
  }
  /**durchsucht alle Records in der DB nach dem gesuchten String mit <b>fore-each</b>-schleife
   * @param searchtyp was soll gesucht werden
   * @param searchString welcher wert 
   * @return ganzen Record, der die gesuchte Info enthält
   */
  public static Record searchRecord(String searchtyp,String searchString){
    action_flag=true;
    Record returnobject=new Record();
    int count_hits=0;
    List<Record> list_results=new ArrayList<Record>();
    String tmp="";
    searchString=searchString.toLowerCase();
    switch(searchtyp){
    case("artist"):
      for(Record k : allRecords) {
        tmp=k.getArtist();
        tmp=tmp.toLowerCase();
        
        if(tmp.equals(searchString)){
          returnobject=k;
          list_results.add(k);
          count_hits++;
        }
      }
    break;
    case("title"):
      for(Record k : allRecords) {
        tmp=k.getTitle();
        tmp=tmp.toLowerCase();
        if(tmp.equals(searchString)){
          returnobject=k;
          list_results.add(k);
          count_hits++;
        }
      }
    break;
    case("style"):
      for(Record k : allRecords) {
        tmp=k.getStyle();
        tmp=tmp.toLowerCase();
        if(tmp.equals(searchString)){
          returnobject=k;
          list_results.add(k);
          count_hits++;
        }
      }
    break;
    case("length"):
      for(Record k : allRecords) {
        tmp=k.getLength();
        tmp=tmp.toLowerCase();
        if(tmp.equals(searchString)){
          returnobject=k;
          list_results.add(k);
          count_hits++;
        }
      }
    break;
    
    case("position"):
      for(Record k : allRecords) {
        tmp=k.getPosition();
        tmp=tmp.toLowerCase();
        if(tmp.equals(searchString)){
          returnobject=k;
          list_results.add(k);
          count_hits++;
        }
      }
    break;
    case("comment"):
      //TODO: Suche nach Teil der Kommentare
      /*
      for(Record k : allRecords) {
        if(k.getStyle().equals(searchString)){
          returnobject=k;
        }
      }
      */
    break;
    case("ID"):
      for(Record k:allRecords){
        tmp=k.getID();
        tmp=tmp.toLowerCase();
        if(searchString.equals(tmp)){
          returnobject=k;
          count_hits=1;
        }
      }
    }
    //falls kein Treffer:
    if (!(count_hits>0)){
      Vinyl_server.printstring("\nNicht gefunden!");
    }
    action_flag=true;
    //falls mehr als 1 Treffer
    if (count_hits>1){
      //TODO: Auswahl aus Liste der Treffer
      Vinyl_server.printstring("\nmehrere Einträge");
      temp_list=list_results;
      temp_size=count_hits;
      action_flag=false;
      new selection_window();
    }
    //genau 1 Treffer
    return returnobject;
    
  }
  public static String search_artist_title(String search_artist,String search_title){
    String result="";
    for(Record seek:allRecords){
      if(seek.getArtist()==search_artist&&seek.getTitle()==search_title){
        result=seek.getID();
      }
    }
    return result;
  }
  public static RecordDB searchSeveralRecords(String Type1,String Value1,String Type2,String Value2){
    
    return null;
  }
  public static boolean checkID(String check_ID){
    boolean flag=false;
    for(Record check:allRecords){
      if(check.getID()==check_ID){
        flag=true;
      }
    }
    return flag;
  }
  public static Record select_record(List<Record> auswahlmenge){
    Record result=new Record();
    
    return result;
  }
  public static void keyword_search(String keyword){
    //ArrayList<String> results=new ArrayList<String>();
    temp_list=new ArrayList<Record>();
    temp_size=0;
    int count_hits=0;
    keyword=keyword.toLowerCase();
    for(Record seeek:RecordDB.allRecords){
      if( seeek.getID().toLowerCase().contains(keyword)
        | seeek.getLength().toLowerCase().contains(keyword)
        | seeek.getPosition().toLowerCase().contains(keyword)
        | seeek.getStyle().toLowerCase().contains(keyword)
        | seeek.getComments().toLowerCase().contains(keyword)
      ){         
        RecordDB.temp_list.add(seeek);
        temp_size++;
        count_hits++;
      }
    }
    if(count_hits>0){
      new selection_window();
    }
    else{
      Vinyl_server.printstring("\nKein Eintrag gefunden!");
    }
  }
  public static Record search_artist_title_length(String artist_search,String title_search,String length_search){
    Record result=new Record();
    for(Record seek:allRecords){
      if(seek.getArtist().contains(artist_search)&&seek.getTitle().contains(title_search)&&seek.getLength().contains(length_search)){
       result=seek; 
      }
    }
    return result;
    
  }
  //FUER GENAUE SUCHE :
  public static ArrayList<Record> keyword1_search(String keyword){
    ArrayList<Record> results=new ArrayList<Record>();
    keyword=keyword.toLowerCase();
    for(Record seeek:RecordDB.allRecords){
      if( seeek.getID().toLowerCase().contains(keyword)
        | seeek.getLength().toLowerCase().contains(keyword)
        | seeek.getPosition().toLowerCase().contains(keyword)
        | seeek.getStyle().toLowerCase().contains(keyword)
        | seeek.getComments().toLowerCase().contains(keyword)
      ){         
        results.add(seeek);
      }
    }
    return results;
  }
  public static ArrayList<Record> keyword3_search(String keyword,String keyword2,String keyword3){
    ArrayList<Record> results=new ArrayList<Record>();
    ArrayList<Record> result2=new ArrayList<Record>();
    ArrayList<Record> result3=new ArrayList<Record>();
    ArrayList<Record> result_z=new ArrayList<Record>();
    ArrayList<Record> result=new ArrayList<Record>();
    keyword=keyword.toLowerCase();
    keyword2=keyword2.toLowerCase();
    keyword3=keyword3.toLowerCase();
    //erstes keyword suchen und in menge eintragen
    for(Record seeek:RecordDB.allRecords){
      if( seeek.getID().toLowerCase().contains(keyword)
        | seeek.getLength().toLowerCase().contains(keyword)
        | seeek.getPosition().toLowerCase().contains(keyword)
        | seeek.getStyle().toLowerCase().contains(keyword)
        | seeek.getComments().toLowerCase().contains(keyword)
      ){         
        results.add(seeek);
      }
    }
    //zweites keyword suchen und in zweite menge einragen
    for(Record seek2:RecordDB.allRecords){
      if( seek2.getID().toLowerCase().contains(keyword2)
          | seek2.getLength().toLowerCase().contains(keyword2)
          | seek2.getPosition().toLowerCase().contains(keyword2)
          | seek2.getStyle().toLowerCase().contains(keyword2)
          | seek2.getComments().toLowerCase().contains(keyword2)
        ){         
          result2.add(seek2);
        }
      
    }
    for(Record seek3:RecordDB.allRecords){
      if( seek3.getID().toLowerCase().contains(keyword3)
          | seek3.getLength().toLowerCase().contains(keyword3)
          | seek3.getPosition().toLowerCase().contains(keyword3)
          | seek3.getStyle().toLowerCase().contains(keyword3)
          | seek3.getComments().toLowerCase().contains(keyword3)
        ){         
          result3.add(seek3);
        }
      
    }
    //schnittmenge bilden und als resultat ausgeben
    for(Record seek_x:results){
      for(Record seek_y:result2){
        if(seek_x.getID()==seek_y.getID()){
          result_z.add(seek_x);
          
        }
      }
    }
    for(Record seek_x:result_z){
      for(Record seek_y:result3){
        if(seek_x.getID()==seek_y.getID()){
          result.add(seek_x);
          
        }
      }
    }
    
    return result;
  }
  public static ArrayList<Record> keyword2_search(String keyword,String keyword2){
    ArrayList<Record> results=new ArrayList<Record>();
    ArrayList<Record> result2=new ArrayList<Record>();
    ArrayList<Record> result=new ArrayList<Record>();
    keyword=keyword.toLowerCase();
    keyword2=keyword2.toLowerCase();
    //erstes keyword suchen und in menge eintragen
    for(Record seeek:RecordDB.allRecords){
      if( seeek.getID().toLowerCase().contains(keyword)
        | seeek.getLength().toLowerCase().contains(keyword)
        | seeek.getPosition().toLowerCase().contains(keyword)
        | seeek.getStyle().toLowerCase().contains(keyword)
        | seeek.getComments().toLowerCase().contains(keyword)
      ){         
        results.add(seeek);
      }
    }
    //zweites keyword suchen und in zweite menge einragen
    for(Record seek2:RecordDB.allRecords){
      if( seek2.getID().toLowerCase().contains(keyword2)
          | seek2.getLength().toLowerCase().contains(keyword2)
          | seek2.getPosition().toLowerCase().contains(keyword2)
          | seek2.getStyle().toLowerCase().contains(keyword2)
          | seek2.getComments().toLowerCase().contains(keyword2)
        ){         
          result2.add(seek2);
        }
      
    }
    //schnittmenge bilden und als resultat ausgeben
    for(Record seek_x:results){
      for(Record seek_y:result2){
        if(seek_x.getID()==seek_y.getID()){
          result.add(seek_x);
          
        }
      }
    }
    
    return result;
  }
}

