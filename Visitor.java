/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheriffbookingsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lanie
 */
public class Visitor {
    
    public static Visitor visitor;
    private String fName;
    private String lName;
    private String arvTime;
    private String endTime;
    
    private ObservableList<Visitor> visitList = FXCollections.observableArrayList();

    public Visitor() {
    }

    public Visitor(String fName, String lName, String aTime, String eTime) {
        this.fName = fName;
        this.lName = lName;
        
        this.arvTime = aTime;
        this.endTime = eTime;
        

    }

    public static Visitor getVisitor() {
        if (visitor == null) {
            visitor = new Visitor();
        }
        return visitor;
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public String getArrivalTime() {
        return arvTime;
    }

    public String getendTime() {
        return endTime;
    }

    

    
}
