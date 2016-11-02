package VinDB;

import java.io.*;

/**Klasse für einzelne Schallplatte
 * @author christian
 *
 */
public class Record implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 2815732162279081087L;
  private String artist;        //welcher Künstler, welche Band
  private String title;
  private String style;         //musikrichtung
  private String length;        //LP,EP,Single
  private String position;      //wo findet man die Platte in meiner Sammlung
  private String comments;      //freie Bemerkungen
  private String ID;
  /**
   * default constructor
   */
  Record(){
    setArtist("");
    setTitle("");
    setStyle("");
    setLength("");
    setPosition("");
    setComments("");
    setID("");
  }
  Record(String newArtist,String newTitle,String newLength,String newStyle,String newPosition,
      String newComments){
    setArtist(newArtist);
    setTitle(newTitle);
    setStyle(newStyle);
    setLength(newLength);
    setPosition(newPosition);
    setComments(newComments);
    setID(newArtist+newTitle+newLength);
  }
  /**
   * @return the artist
   */
  public String getArtist() {
    return artist;
  }
  /**
   * @param artist the artist to set
   */
  public void setArtist(String artist) {
    this.artist = artist;
  }
  /**
   * @return the style
   */
  public String getStyle() {
    return style;
  }
  /**
   * @param style the style to set
   */
  public void setStyle(String style) {
    this.style = style;
  }
  /**
   * @return the length
   */
  public String getLength() {
    return length;
  }
  /**
   * @param length the length to set
   */
  public void setLength(String length) {
    this.length = length;
  }
  /**
   * @return the position
   */
  public String getPosition() {
    return position;
  }
  /**
   * @param position the position to set
   */
  public void setPosition(String position) {
    this.position = position;
  }
  /**
   * @return the comments
   */
  public String getComments() {
    return comments;
  }
  /**
   * @param comments the comments to set
   */
  public void setComments(String comments) {
    this.comments = comments;
  }
  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }
  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }
  /**
   * @return the iD
   */
  public String getID() {
    return ID;
  }
  /**
   * @param iD the iD to set
   */
  public void setID(String iD) {
    ID = iD;
  }
  /**Einen Eintrag En Bloc verändern
   * @param newArtist
   * @param newTitle
   * @param newStyle
   * @param newLenght
   * @param newPosition
   * @param newComments
   */
  public void alterRecord(String newArtist,String newTitle,String newStyle,String newLenght,String newPosition,
      String newComments){
    this.setArtist(newArtist);
    this.setTitle(newTitle);
    this.setStyle(newStyle);
    this.setLength(newLenght);
    this.setPosition(newPosition);
    this.setComments(newComments);
  }
}
