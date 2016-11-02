package VinDB;

public class VinylDB_Start {

  @SuppressWarnings({ "unused" })
  public static void main(String[] args) {
    // TODO Auto-generated method stub, just test methods
    /*String[] songs={"Lucifer","I wouldnt want to be like you"};
    String[] musicians={"Alan Parson"};
    Record test=new Record("Alan Parsons Project","Lucifer","Pop","Single",songs,
        "Singles linke kiste unter A bei Pop","",musicians);
    RecordDB.addRecord(test);*/
    Vinyl_server running=new Vinyl_server();
    RecordDB.DBpath = System.getProperty("user.home");
    }
  }

