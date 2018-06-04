/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheriffbookingsystem;

import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inmate {
public static Inmate inmate;
private String fName;
private String lName;
private int idNum;
private String height;
private String weight;
private String ethnicity;
private String bookInDate;
private String courtDate;
private String relDate;
private String cell;
private String cellnum;
private String status;
private ObservableList<Inmate> inmateList = FXCollections.observableArrayList();

public Inmate(){}

public Inmate (String fName, String lName, int idNum, String height, String weight,String eth, String bookDate, String courtDate, String relDate, String cell, String cellnum, String status){
 this.fName = fName;
this.lName = lName;
this.idNum = idNum;
this.height = height;
this.weight = weight;
 this.ethnicity = eth;
this.bookInDate = bookDate;
this.courtDate = courtDate;
this.relDate = relDate;
this.cell = cell;
 this.cellnum = cellnum;
 this.status = status;


}
public static Inmate getInmate(){
if (inmate == null) {
 inmate = new Inmate();
}
return inmate;
}

public String getFirstName(){
return fName;
}
 public String getLastName(){
return lName;
}
public int getIdNumber(){
 return idNum;
}
public String getHeight(){
return height;
}
public String getWeight(){
return weight;
}
public String getEthnicity(){
return ethnicity;
}
public String getBookInDate(){
 return bookInDate;
}
 public String getCourtDate(){
return courtDate;
}
public String getReleaseDate(){
return relDate;
}
public String getCell(){
 return cell;
}
public String getCellnum(){
return cellnum;
}
public String getStatus(){
 return status;
}
public ObservableList<Inmate> addToInmateList(Inmate i){
 inmateList.add(i);

return inmateList;
}
 public ObservableList<Inmate> getInmateList() throws IOException, ClassNotFoundException {
return inmateList;
}

//Creating SimpleString Property for Table
 public SimpleStringProperty firstProperty()
{
SimpleStringProperty first = new SimpleStringProperty(this.fName);
return first;
}
public SimpleStringProperty lastProperty()
{
    SimpleStringProperty last = new SimpleStringProperty(this.lName);
return last;
}
public SimpleStringProperty idProperty()
{
SimpleStringProperty id = new SimpleStringProperty(Integer.toString(this.idNum));
return id;
}
public SimpleStringProperty hProperty()
{
SimpleStringProperty h = new SimpleStringProperty(this.height);
return h;
}
public SimpleStringProperty wProperty()
{
SimpleStringProperty w = new SimpleStringProperty(this.weight);
return w;
}
public SimpleStringProperty ethProperty()
{
SimpleStringProperty eth = new SimpleStringProperty(this.ethnicity);
return eth;
}public SimpleStringProperty arvProperty()
{
SimpleStringProperty arvDate = new SimpleStringProperty(this.bookInDate);
return arvDate;
}
public SimpleStringProperty courtProperty()
{
SimpleStringProperty court = new SimpleStringProperty(this.courtDate);
return court;
}
public SimpleStringProperty relProperty()
{
SimpleStringProperty relDate = new SimpleStringProperty(this.relDate);
return relDate;
}
public SimpleStringProperty cellProperty()
{
SimpleStringProperty cell = new SimpleStringProperty(this.cell);
return cell;
}public SimpleStringProperty cellnumProperty()
{
SimpleStringProperty cellnum = new SimpleStringProperty(this.cellnum);
return cellnum;
}
public SimpleStringProperty statusProperty()
{
SimpleStringProperty status = new SimpleStringProperty(this.status);
return status;
}

@Override
public String toString(){
return this.getFirstName() + " " + getLastName() +
"\nID#: " + getIdNumber() +
 "\nH:" + getHeight() + " W:" + getWeight() +
 "\nEthic: " + getEthnicity() +
"\nCell block: " + getCell() +
 "\nCell Number: " + getCellnum() +
 "\nStatus: " + getStatus();
}
}