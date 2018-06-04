/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheriffbookingsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lanie
 */
public class Visit {
   //public static Inmate inmate;
   //inmate name and id number
    private final SimpleStringProperty inFirstName;
    private final SimpleStringProperty inLastName;
   //int idNum = inmate.getIdNumber();
   
    //public static Visitor visitor;
    //Visitor Names
    private final SimpleStringProperty vtFirstName;
    private final SimpleStringProperty vtLastName; 
    private final SimpleStringProperty vDt;
    
    
    
    
    private ObservableList<Inmate> visitList = FXCollections.observableArrayList();

    public Visit(String imFirstName, String imLastName, String vFirstName, String vLastName, String Dt) {
        this.inFirstName =  new SimpleStringProperty(imFirstName);
        this.inLastName =  new SimpleStringProperty(imLastName);
        this.vtFirstName = new SimpleStringProperty (vFirstName);
        this.vtLastName =  new SimpleStringProperty (vLastName);
        this.vDt =  new SimpleStringProperty (Dt);
    }
    
    public String getInFirstName() {
        return inFirstName.get();
    }

    public void setInFirstName(String imFirstName) {
        inFirstName.set(imFirstName);
    }

    public String getInLastName() {
        return inLastName.get();
    }

    public void setInLastName(String imLastName) {
        inLastName.set(imLastName);
    }
    public String getVtFirstName() {
        return vtFirstName.get();
    }

    public void setvtFirstName(String vFirstName) {
        vtFirstName.set(vFirstName);
    }

    public String getLastName() {
        return vtLastName.get();
    }

    public void setLastName(String vLastName) {
        vtLastName.set(vLastName);
    }
    
    public void setVDt(String Dt){
        vDt.set(Dt);
    }
    public String getVDt(){
        return vDt.get();
    }
    

    

   

    

    
    
   
    
}


