  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheriffbookingsystem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author Lanie
 */
public class SheriffBookingSystem extends Application {
    
    Scene logInScene, scene;
    
    //Password
    final String correctPass = "LoveTheBlue";
    
    //Maps for inmates and visitor
    Map<Integer, Inmate> inmateMap = new HashMap<Integer, Inmate>();
     Map<Integer, Inmate> inmatesMap = new HashMap<Integer, Inmate>();
    Map<Integer, Visitor> visitorMap = new HashMap<Integer, Visitor>();
    Map<Integer, Visit> visitMap = new HashMap<Integer,Visit>();
    
    
    ArrayList<Inmate> inmateList = new ArrayList<>();
    
            
            
    ObservableList<Inmate> data = FXCollections.observableArrayList(inmateList);
    
     ArrayList<Visit> visitList = new ArrayList<>();
    
    ObservableList<Visit> visoData = FXCollections.observableArrayList(visitList);
    
    
            
    //Date object
    DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
    Date date = new Date();
    Calendar now = Calendar.getInstance();
    
    
    private TableView<Inmate> inmateTable = new TableView<Inmate>();
    
    private TableView<Visit> visoTable = new TableView<Visit>();

    @Override
    public void start(Stage primaryStage) {


/************************************************Password**************************/
HBox hB = new HBox();
//password textfield that blocks out the Charactors 
PasswordField passCode = new PasswordField();
passCode.setMaxSize(500, 50);

//Label for password field
Label passLbl = new Label();
passLbl.setText("Sheriff Department Password");
passLbl.setFont(Font.font(24));

//Login button/handler for lock Screen
Button logInBtn = new Button();
logInBtn.setText("Log In");
logInBtn.setOnAction(new EventHandler<ActionEvent>(){
@Override
public void handle(ActionEvent e){
    try{
    
        if (passCode.getText().equals(correctPass)){
         primaryStage.setScene(scene);
        }
        else if (!passCode.getText().equals(correctPass)){
        passCode.clear();
        passCode.setPromptText("Error:Wrong Password");
        }
    }
    catch(Exception b)
    {
        System.out.println("Log-in failed");
    }
}   

});



VBox vB = new VBox();
vB.getChildren().addAll(passLbl, passCode, logInBtn);

GridPane gPane = new GridPane();
gPane.getChildren().add(vB);
gPane.setAlignment(Pos.CENTER);
gPane.getStyleClass().add("gPane");


logInScene = new Scene(gPane, 900, 700);
logInScene.getStylesheets().add("BookingSystem.css");


primaryStage.setTitle("Sheriff Department");
primaryStage.setScene(logInScene);
primaryStage.show();

/******************************Overall Layout*************************************************/

    Locale.setDefault(Locale.US);
// load image
    Image image = new Image("policeBadge.jpg");

// simple display of ImageView and image 
         ImageView background = new ImageView();
        background.setImage(image);
        
        background.setOpacity(.40);
        Text topBanner = new Text("Gwinnett Sheriff's Office");
        topBanner.setFont(Font.font(64));

VBox inmateMenu = new VBox();
Tab tabbooking = new Tab("Booking");
tabbooking.setContent(inmateMenu);
inmateMenu.getChildren().add(inmateMenu());
tabbooking.setClosable(false);

VBox statusMenu = new VBox();
Tab tabstatus = new Tab("Status");
tabstatus.setContent(statusMenu);
statusMenu.getChildren().add(statusMenu());
tabstatus.setClosable(false);

VBox visitorMenu = new VBox();
Tab tabvist = new Tab("Visting");
tabvist.setContent(visitorMenu);
visitorMenu.getChildren().add(visitMenu());
tabvist.setClosable(false);

//all the tabs
TabPane tabmenus = new TabPane();
tabmenus.getTabs().addAll(tabbooking, tabstatus, tabvist);

// put the tabs into a HBOX
HBox hboxtab = new HBox();
hboxtab.getChildren().addAll(tabmenus);
hboxtab.setAlignment(Pos.CENTER);

// put the title into a HBOX
 HBox hboxtitle = new HBox();
hboxtitle.getChildren().addAll(topBanner);
hboxtitle.setAlignment(Pos.CENTER);

 // center in the middle of the border pane

BorderPane layout = new BorderPane();
layout.setTop(hboxtitle);
layout.setCenter(hboxtab);
StackPane root = new StackPane();
root.getChildren().addAll(background, layout);
scene = new Scene(root, 1200, 800);


}
//Inmate menu
public VBox inmateMenu(){

VBox textfields = new VBox();
VBox labelfields = new VBox(10);
VBox tableForBooking = new VBox();

Label fName = new Label("First Name:");
 fName.setFont(Font.font(15));
TextField tfFName = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[0-9\\s]")) {
 super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text) {
if (!text.matches("[0-9\\s]")) {
 super.replaceSelection(text);
}}};
 tfFName.setPromptText("First Name");

Label lName = new Label("Last Name:");
 lName.setFont(Font.font(15));
TextField tfLName = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[0-9\\s]")) {
 super.replaceText(start, end, text);
}}
 @Override public void replaceSelection(String text) {
 if (!text.matches("[0-9\\s]")) {
super.replaceSelection(text);
}}};
tfLName.setPromptText("Last Name");

 Label idNum = new Label("Id Number:");
idNum.setFont(Font.font(15));

TextField tfID = new TextField(){
 @Override public void replaceText(int start, int end, String text) {
 if (!text.matches("[a-zA-z\\s]")) {
super.replaceText(start, end, text);
 }}
@Override public void replaceSelection(String text) {
if (!text.matches("[a-zA-z\\s]")) {
super.replaceSelection(text);
}}};
 tfID.setPromptText("ID Number");

Label height = new Label("Height:");
 height.setFont(Font.font(15));
 TextField tfHeight = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[a-zA-z\\s]")) {
 super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text) {
if (!text.matches("[a-zA-z\\s]")) {
super.replaceSelection(text);
}}};
tfHeight.setPromptText("Height");

 Label weight = new Label("Weight:");
 weight.setFont(Font.font(15));
TextField tfWeight = new TextField(){
 @Override public void replaceText(int start, int end, String text) {
if (!text.matches("[a-zA-z\\s]")) {
 super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text) {
if (!text.matches("[a-zA-z\\s]")) {
super.replaceSelection(text);
}}};
tfWeight.setPromptText("Weight");

 Label ethic = new Label("Ethicity:");
ChoiceBox ethicbox = new ChoiceBox(FXCollections.observableArrayList(
 "White", "Black", "Asian", "Hispanic","Other"));
ethic.setFont(Font.font(15));

Label bookInDateLab = new Label("Book-In Date:");
  bookInDateLab.setFont(Font.font(15));
  TextField tfBookInDate = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[a-zA-z\\s]")) {
super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text) {
if (!text.matches("[a-zA-z\\s]")) {
 super.replaceSelection(text);
}}};
tfBookInDate.setPromptText("Book-In Date");
 tfBookInDate.setText(dateFormat.format(date));

Label courtDates = new Label("Court Date:");
courtDates.setFont(Font.font(15));
TextField tfCourtDates = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[a-zA-z\\s]")) {
super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text) {
if (!text.matches("[a-zA-z\\s]")) {
super.replaceSelection(text);
}}};
 tfCourtDates.setPromptText("Court Date");
tfCourtDates.setText(Integer.toString(now.get(Calendar.MONTH) + 2) + "/" + Integer.toString(now.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(now.get(Calendar.YEAR)));

Label relDate = new Label("Release Date:");
relDate.setFont(Font.font(15));
 TextField tfRelDate = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[a-zA-z\\s]")) {
 super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text) {
 if (!text.matches("[a-zA-z\\s]")) {
 super.replaceSelection(text);
}}};
 tfRelDate.setPromptText("Release Date");
 tfRelDate.setText(Integer.toString(now.get(Calendar.MONTH) + 2) + "/" + Integer.toString(now.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(now.get(Calendar.YEAR) + 5));

Label cellblock = new Label("Cell Block:");
ChoiceBox cellblockbox = new ChoiceBox(FXCollections.observableArrayList(
"Minimum", "Maximum", "Isolation", "Hospital"));
 cellblock.setFont(Font.font(15));

 Label cellnumlabel = new Label("Cell Numbers:");
 ChoiceBox cellnum = new ChoiceBox(FXCollections.observableArrayList(
 "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
 "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
 "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
 "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
 "51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
 "61", "62", "63", "64", "65", "66", "67", "68", "69", "70",
 "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
 "81", "82", "83", "84", "85", "86", "87", "88", "89", "90",
 "91", "92", "93", "94", "95", "96", "97", "98", "99", "100",
 "101", "102", "103", "104", "105", "106", "107", "108", "109", "110",
 "111", "112", "113", "114", "115", "116", "117", "118", "119", "120",
 "121", "122", "123", "124", "125", "126", "127", "128", "129", "130",
 "131", "132", "133", "134", "135", "136", "137", "138", "139", "140",
 "141", "142", "143", "144", "145", "146", "147", "148", "149", "150",
 "151", "152", "153", "154", "155", "156", "157", "158", "159", "160",
"161", "162", "163", "164", "165", "166", "167", "168", "169", "170",
"171", "172", "173", "174", "175", "176", "177", "178", "179", "180",
"181", "182", "183", "184", "185", "186", "187", "188", "189", "190",
"191", "192", "193", "194", "195", "196", "197", "198", "199", "200"));
 cellnumlabel.setFont(Font.font(15));



 Label statuslabel = new Label("Status:");
statuslabel.setFont(Font.font(15));
TextField tfstatus = new TextField();
tfstatus.setPromptText("Status");

Button showallbtn = new Button("Show All Inmates");
        showallbtn.setOnAction(w -> {
           TableView inmateTable = new TableView();
            Stage tabelstage = new Stage();

            tabelstage.setTitle("Inmate List");
            tabelstage.setWidth(999);
            tabelstage.setHeight(500);

            final Label label = new Label("Inmate List");
            label.setFont(new Font("Arial", 20));
            
            inmateList.add(new Inmate("Jay","Lan",1001,"6'0","185","Black","1/12/2017","11/1/2017","12/23/2017","Minimum","3","Bond"));
            inmateList.add(new Inmate("Jim","Brown",1002,"5'0","145","White","2/15/2017","8/1/2017","12/29/2017","Minimum","4","Bond"));
            inmateList.add(new Inmate("Daniel","Gus",1003,"6'6","285","Other","1/22/2017","11/1/2017","12/3/2017","Minimum","8","No-Bond"));
            inmateList.add(new Inmate("George","Jones",1006,"5'9","175","Hispanic","3/12/2017","5/21/2017","12/23/2017","Minimum","5","Bond"));
            inmateList.add(new Inmate("Gerald","James",1007,"6'2","185","Black","6/30/2017","10/1/2017","11/23/2017","Minimum","3","No-Bond"));
            

            data = FXCollections.observableArrayList(inmateList);
            //prisiontable.setEditable(true);

            //First Name column 
            TableColumn<Inmate, String> fnCol = new TableColumn<Inmate, String>("First Name");
            fnCol.setMinWidth(100);
            fnCol.setCellValueFactory(new PropertyValueFactory<Inmate, String>("first"));

            //Last Name column 
            TableColumn<Inmate, String> lnCol = new TableColumn<>("Last Name");
            lnCol.setMinWidth(100);
            lnCol.setCellValueFactory(new PropertyValueFactory<>("last"));

            //ID Number column 
            TableColumn<Inmate, String> idCol = new TableColumn<>("Id Number");
            idCol.setMinWidth(100);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

            //Height column 
            TableColumn<Inmate, String> hCol = new TableColumn<>("Height");
            hCol.setMinWidth(50);
            hCol.setCellValueFactory(new PropertyValueFactory<>("h"));

            //Weight column 
            TableColumn<Inmate, String> wCol = new TableColumn<>("Weight");
            wCol.setMinWidth(75);
            wCol.setCellValueFactory(new PropertyValueFactory<>("w"));

            //Ethnic column 
            TableColumn<Inmate, String> ethicCol = new TableColumn<>("Ethnic");
            ethicCol.setMinWidth(100);
            ethicCol.setCellValueFactory(new PropertyValueFactory<>("eth"));

            //Arrival column 
            TableColumn<Inmate, String> arvCol = new TableColumn<>("Arrival Date");
            arvCol.setMinWidth(100);
            arvCol.setCellValueFactory(new PropertyValueFactory<>("arv"));

            //Court column 
            TableColumn<Inmate, String> courtCol = new TableColumn<>("Court Date");
            courtCol.setMinWidth(100);
            courtCol.setCellValueFactory(new PropertyValueFactory<>("court"));

            //Release column 
            TableColumn<Inmate, String> relCol = new TableColumn<>("Release Date");
            relCol.setMinWidth(100);
            relCol.setCellValueFactory(new PropertyValueFactory<>("rel"));

            //Status column 
            TableColumn<Inmate, String> statusCol = new TableColumn<>("Status");
            statusCol.setMinWidth(100);
            statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

            //Cell column 
            TableColumn<Inmate, String> cellCol = new TableColumn<>("Cell");
            cellCol.setMinWidth(100);
            cellCol.setCellValueFactory(new PropertyValueFactory<>("cell"));

            //Cellnum column 
            TableColumn<Inmate, String> cellnumCol = new TableColumn<>("Cell Num");
            cellnumCol.setMinWidth(100);
            cellnumCol.setCellValueFactory(new PropertyValueFactory<>("cellnum"));

            Iterator<Map.Entry<Integer, Inmate>> iterator = inmateMap.entrySet().iterator();
            Map.Entry<Integer, Inmate> InmateMap = iterator.next();

            inmateTable = new TableView();
            inmateTable.setItems(data);

            inmateTable.getColumns().addAll(fnCol, lnCol, idCol, hCol, wCol, arvCol, courtCol, relCol, statusCol, ethicCol, cellCol, cellnumCol);

            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(label, inmateTable);

            Scene tabelscene = new Scene(vbox, 500, 500);
            tabelstage.setScene(tabelscene);
            tabelstage.show();

        });




labelfields.getChildren().addAll(fName, lName, idNum, height, weight, ethic, bookInDateLab, courtDates, relDate, cellblock, cellnumlabel, statuslabel);
textfields.getChildren().addAll(tfFName, tfLName, tfID, tfHeight, tfWeight, ethicbox, tfBookInDate, tfCourtDates, tfRelDate,cellblockbox, cellnum, tfstatus);


Label fnError = new Label("Please Enter a First Name");
Label lnError = new Label("Please Enter a Last Name");
Label idError = new Label("Please Enter a ID");
Label hError = new Label("Please Enter a Height");
Label wError = new Label("Please Enter a Weight");
Label bookingError = new Label("Please Enter Arrest Date");
Label courtError = new Label("Please Enter Court Date");
Label relError = new Label("Please Enter Release Date");
Label statusError = new Label("Please Enter a Bond Status");

fnError.setVisible(false);
lnError.setVisible(false);
 idError.setVisible(false);
 hError.setVisible(false);
 wError.setVisible(false);
bookingError.setVisible(false);
 courtError.setVisible(false);
relError.setVisible(false);
 statusError.setVisible(false);

VBox errorbox = new VBox(fnError, lnError, idError, hError, wError, bookingError, courtError, relError, statusError);

Button addBtn = new Button("Add");
 addBtn.setOnAction(e-> {
 fnError.setVisible(false);
 lnError.setVisible(false);
 idError.setVisible(false);
 hError.setVisible(false);
 wError.setVisible(false);
 bookingError.setVisible(false);
 courtError.setVisible(false);
 relError.setVisible(false);
 statusError.setVisible(false);

if(tfFName.getText().trim().isEmpty())
 {
fnError.setVisible(true);
}
if(tfLName.getText().trim().isEmpty())
 {
 lnError.setVisible(true);
}
if(tfID.getText().trim().isEmpty())
{
idError.setVisible(true);
}
if(tfHeight.getText().trim().isEmpty())
{
 hError.setVisible(true);
}
if(tfWeight.getText().trim().isEmpty())
 {
 wError.setVisible(true);
}
if(tfBookInDate.getText().trim().isEmpty())
{
bookingError.setVisible(true);
}
if(tfCourtDates.getText().trim().isEmpty())
{
courtError.setVisible(true);
}
if(tfRelDate.getText().trim().isEmpty())
{
relError.setVisible(true);
}
 if(tfstatus.getText().trim().isEmpty())
{
statusError.setVisible(true);
 }

 String fn = tfFName.getText();
 String ln = tfLName.getText();
 int id = 0;
 id = Integer.parseInt(tfID.getText());
 String h = tfHeight.getText();
 String w = tfWeight.getText();
 String eth = ethicbox.getSelectionModel().getSelectedItem().toString();
 String arrive = tfBookInDate.getText();
 String court = tfCourtDates.getText();
 String release = tfRelDate.getText();
 String cell = cellblockbox.getSelectionModel().getSelectedItem().toString();
 String cellnumstring = cellnum.getSelectionModel().getSelectedItem().toString();
 String status = tfstatus.getText();

if(fn != null &&
 ln != null &&
 id != 0 &&
 h != null &&
 w != null &&
 arrive != null &&
 court != null &&
 release != null &&
 status != null)
 {
     //add new inmate to map
Inmate inmate = new Inmate(fn,ln,id,h,w,eth,arrive,court,release,cell,cellnumstring,status);
 inmateList.add(inmate);
inmateMap.put(id, inmate );



 tfFName.clear();
 tfLName.clear();
 tfID.clear();
 tfHeight.clear();
 tfWeight.clear();
 tfstatus.clear();
 ethicbox.getSelectionModel().clearSelection();
 cellblockbox.getSelectionModel().clearSelection();
 cellnum.getSelectionModel().clearSelection();
 
 
                        
                        
            
}
});
 
 //edit button
 Button editBtn = new Button("Edit");
 Button saveBtn = new Button("Save Changes");
 Button canBtn = new Button("Cancel Changes");
 saveBtn.setVisible(false);
 canBtn.setVisible(false);
 editBtn.setOnAction(e->{
 fnError.setVisible(false);
 lnError.setVisible(false);
 idError.setVisible(false);
 hError.setVisible(false);
 wError.setVisible(false);
 bookingError.setVisible(false);
 courtError.setVisible(false);
 relError.setVisible(false);
 statusError.setVisible(false);

 //Save Button
 saveBtn.setVisible(true);
 canBtn.setVisible(true);
 if(tfID.getText().trim().isEmpty())
{

    idError.setVisible(true);
}
else
 {
 if(tfFName.getText().trim().isEmpty())
{
 fnError.setVisible(true);
}
if(tfLName.getText().trim().isEmpty())
{
lnError.setVisible(true);
}
 if(tfID.getText().trim().isEmpty())
 {
 idError.setVisible(true);
 }
 if(tfHeight.getText().trim().isEmpty())
 {
 hError.setVisible(true);
}
if(tfWeight.getText().trim().isEmpty())
 {
wError.setVisible(true);
}
 if(tfBookInDate.getText().trim().isEmpty())
 {
bookingError.setVisible(true);
}
if(tfCourtDates.getText().trim().isEmpty())
{
courtError.setVisible(true);
}
if(tfRelDate.getText().trim().isEmpty())
 {
relError.setVisible(true);
}
 if(tfstatus.getText().trim().isEmpty())
 {
statusError.setVisible(true);
}
Inmate temppris = inmateMap.get(Integer.parseInt(tfID.getText()));

tfFName.setText(temppris.getFirstName());
 tfLName.setText(temppris.getLastName());
tfID.setText(Integer.toString(temppris.getIdNumber()));
tfHeight.setText(temppris.getHeight());
 tfWeight.setText(temppris.getWeight());
 ethicbox.setValue(temppris.getEthnicity());
tfBookInDate.setText(temppris.getBookInDate());
 tfCourtDates.setText(temppris.getCourtDate());
tfRelDate.setText(temppris.getReleaseDate());
cellblockbox.setValue(temppris.getCell());
cellnum.setValue(temppris.getCellnum());
tfstatus.setText(temppris.getStatus());

saveBtn.setOnAction(q-> {
saveBtn.setVisible(false);
 canBtn.setVisible(false);
 fnError.setVisible(false);
 lnError.setVisible(false);
 idError.setVisible(false);
 hError.setVisible(false);
 wError.setVisible(false);
 bookingError.setVisible(false);
 courtError.setVisible(false);
 relError.setVisible(false);
 statusError.setVisible(false);
 String fn = tfFName.getText();
 String ln = tfLName.getText();
 int id = 0;
 id = Integer.parseInt(tfID.getText());
 String h = tfHeight.getText();
 String w = tfWeight.getText();
 String eth = ethicbox.getSelectionModel().getSelectedItem().toString();
 String arrive = tfBookInDate.getText();
 String court = tfCourtDates.getText();
 String release = tfRelDate.getText();
 String cell = cellblockbox.getSelectionModel().getSelectedItem().toString();
 String cellnumstring = cellnum.getSelectionModel().getSelectedItem().toString();
 String status = tfstatus.getText();

if(fn != null &&
 ln != null &&
 id != 0 &&
 h != null &&
 w != null &&
 arrive != null &&
 court != null &&
 release != null &&
 status != null)
 {
 Inmate inmate = new Inmate(fn,ln,id,h,w,eth,arrive,court,release,cell,cellnumstring,status);
 inmateMap.put(id, inmate );

tfFName.clear();
tfLName.clear();
tfID.clear();
 tfHeight.clear();
tfWeight.clear();
tfstatus.clear();
ethicbox.getSelectionModel().clearSelection();
cellblockbox.getSelectionModel().clearSelection();
cellnum.getSelectionModel().clearSelection();
}
});

 canBtn.setOnAction(q->{
 fnError.setVisible(false);
 lnError.setVisible(false);
 idError.setVisible(false);
 hError.setVisible(false);
 wError.setVisible(false);
 bookingError.setVisible(false);
 courtError.setVisible(false);
 relError.setVisible(false);
 statusError.setVisible(false);
 saveBtn.setVisible(false);
 canBtn.setVisible(false);
 tfFName.clear();
 tfLName.clear();
 tfID.clear();
 tfHeight.clear();
 tfWeight.clear();
 tfstatus.clear();
ethicbox.getSelectionModel().clearSelection();
cellblockbox.getSelectionModel().clearSelection();
cellnum.getSelectionModel().clearSelection();

 });

}});

Button removeBtn = new Button("Remove");
removeBtn.setOnAction(e->{
 fnError.setVisible(false);
lnError.setVisible(false);
idError.setVisible(false);
hError.setVisible(false);
 wError.setVisible(false);
bookingError.setVisible(false);
courtError.setVisible(false);
relError.setVisible(false);
statusError.setVisible(false);


Label removeidNum = new Label("Id Number: ");
removeidNum.setFont(Font.font(15));
TextField removetfID = new TextField(){
    @Override public void replaceText(int start, int end, String text) {
        if (!text.matches("[a-zA-z\\s]")) {
        super.replaceText(start, end, text);
    }}


    @Override public void replaceSelection(String text) {
        if (!text.matches("[a-zA-z\\s]")) {
        super.replaceSelection(text);
    }}};

removetfID.setPromptText("ID Number");
Label removed = new Label("Removed");
removed.setVisible(false);

 Button newremoveBtn = new Button("Remove");
   newremoveBtn.setOnAction(q-> {
   int id = 0;
   id = Integer.parseInt(removetfID.getText());
   inmateMap.remove(id);
   removed.setVisible(true);
 });
 
 
 
Stage newStage = new Stage();
 VBox labelbox = new VBox(removeidNum);
 VBox tfbox = new VBox(removetfID);
 labelbox.setAlignment(Pos.CENTER);
 tfbox.setAlignment(Pos.CENTER);
 
 HBox root = new HBox(labelbox, tfbox);
 root.setAlignment(Pos.CENTER);

 VBox temp = new VBox(root, newremoveBtn, removed);
 temp.setAlignment(Pos.CENTER);
 
 Scene stageScene = new Scene(temp, 300, 300);
 newStage.setScene(stageScene);
 newStage.show();
});

HBox hbox = new HBox();
HBox hbox2 = new HBox();
VBox hbox3 = new VBox();

hbox.getChildren().addAll(addBtn, removeBtn, editBtn, showallbtn, saveBtn, canBtn);
hbox2.getChildren().addAll(labelfields, textfields,tableForBooking);
hbox3.getChildren().addAll(hbox2, hbox, errorbox);
 return hbox3;
}
/********************************************************************************************/

public VBox statusMenu(){
    Label orlabel = new Label("  OR  ");
    VBox idbox = new VBox();
    Label noerror = new Label("\tPlease Make a Selection");
    noerror.setVisible(false);
    ToggleGroup group1 = new ToggleGroup();
    RadioButton inmateRadio = new RadioButton("Inmate");
    RadioButton vistitorRadio = new RadioButton("Visit");
    inmateRadio.setToggleGroup(group1);
    vistitorRadio.setToggleGroup(group1);

    HBox idfield = new HBox();
    idfield.getChildren().addAll(inmateRadio, orlabel, vistitorRadio, noerror);

    TextField tfidfield = new TextField();
         tfidfield.setPromptText("ID #");
    Button statusBtn = new Button("Current Status");
    Label statusfield = new Label();
        statusBtn.setOnAction(e->{
            int id = -1;
        if(inmateRadio.isSelected())
        {
        id = Integer.parseInt(tfidfield.getText());
        Inmate temp = inmateMap.get(id);
        String stemp = "";
        if (temp == null)
        {
        stemp = "Inmate not found";
        statusfield.setText(stemp);
        }
        else
         {
        stemp = temp.toString();
         statusfield.setText(stemp);
        }
        statusfield.disabledProperty();
        statusfield.setFont(Font.font(15));

        }
        else if(vistitorRadio.isSelected())
        {
         id = Integer.parseInt(tfidfield.getText());
          Visitor temp = visitorMap.get(id);
        String stemp = "";
        if (temp == null)
         {
         stemp = "No Visitor Data Available";
         statusfield.setText(stemp);
        }
        else
        {
        stemp = temp.toString();
        statusfield.setText(stemp);
        }
        statusfield.disabledProperty();
        statusfield.setFont(Font.font(15));

        }
        else{
        noerror.setVisible(true);
         }
     });



        final Label label = new Label("Inmate List");
        label.setFont(new Font("Arial", 20));




HBox stats = new HBox(statusBtn);
idbox.getChildren().addAll(idfield, tfidfield, stats, statusfield);

return idbox;

}
/***********************************Visit Set Up********************************************/
public VBox visitMenu(){
VBox textfields = new VBox();
 VBox labelfields = new VBox(10);

Label fName = new Label("Visitor First Name:");
fName.setFont(Font.font(15));
TextField tfFName = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[0-9\\s]")) {
super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text){
if (!text.matches("[0-9\\s]")) {
super.replaceSelection(text);
}}};
tfFName.setPromptText("Visitor First Name");

Label lName = new Label("Visitor Last Name:");
lName.setFont(Font.font(15));
TextField tfLName = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[0-9\\s]")) {
super.replaceText(start, end, text);
 }}
@Override public void replaceSelection(String text) {
if (!text.matches("[0-9\\s]")) {
super.replaceSelection(text);
}}};
 tfLName.setPromptText("Visitor Last Name");
 
 Label infName = new Label(" Inmate First Name:");
infName.setFont(Font.font(15));
TextField tfINFName = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[0-9\\s]")) {
super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text){
if (!text.matches("[0-9\\s]")) {
super.replaceSelection(text);
}}};
tfINFName.setPromptText("Inmate First Name");

Label inlName = new Label("Inmate Last Name:");
inlName.setFont(Font.font(15));
TextField tfINLName = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[0-9\\s]")) {
super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text){
if (!text.matches("[0-9\\s]")) {
super.replaceSelection(text);
}}};
tfINLName.setPromptText("Inmate Name");

Label vTime = new Label("Visit Time:");
vTime.setFont(Font.font(15));
TextField tfvTime = new TextField(){
@Override public void replaceText(int start, int end, String text) {
if (!text.matches("[a-zA-z\\s]")) {
 super.replaceText(start, end, text);
}}
@Override public void replaceSelection(String text) {
if (!text.matches("[a-zA-z\\s]")) {
super.replaceSelection(text);
}}};
 tfvTime.setPromptText("Visit");
tfvTime.setText(Integer.toString(now.get(Calendar.HOUR)) + ":" + Integer.toString(now.get(Calendar.MINUTE)));


labelfields.getChildren().addAll(fName, lName,infName,inlName, vTime);
 textfields.getChildren().addAll(tfFName, tfLName, tfINFName,tfINLName, tfvTime);

 Label fnError = new Label("Please Enter a Visitor First Name");
  Label lnError = new Label("Please Enter a Visitor Last Name");
 Label infnError = new Label("Please Enter a Inmate first Name");
 Label inlnError = new Label("Please Enter a Inmate Last Name");
 Label vTError = new Label("Please Enter a Visit Time");
 

 fnError.setVisible(false);
 lnError.setVisible(false);
 infnError.setVisible(false);
 inlnError.setVisible(false);
 vTError.setVisible(false);
 
 
 Button showallVisitbtn = new Button("Show All Visits");
        showallVisitbtn.setOnAction(w -> {
           TableView visitTable = new TableView();
            Stage vTableStage = new Stage();

            vTableStage.setTitle("Visits List");
            vTableStage.setWidth(999);
            vTableStage.setHeight(500);

            final Label label = new Label("Visit List");
            label.setFont(new Font("Arial", 20));
            
            visitList.add(new Visit("Jim","Jones","Jay","Lan","1/23/2017"));
            visitList.add(new Visit("Ashley","Jones","Jay","Lan","1/23/2017"));
            visitList.add(new Visit("Martha","Oswald","Jimmy","Low","1/23/2017"));
            visitList.add(new Visit("Mike","Freeway","Jay","Lan","1/23/2017"));

            visoData = FXCollections.observableArrayList(visitList);
            inmateTable.setEditable(true);

            //First Name column 
            TableColumn<Visit, String> vfnCol = new TableColumn<Visit, String>("First Name");
            vfnCol.setMinWidth(100);
            vfnCol.setCellValueFactory(new PropertyValueFactory<Visit, String>("inFirstName"));

            //Last Name column 
            TableColumn<Visit, String> vlnCol = new TableColumn<>("Last Name");
            vlnCol.setMinWidth(100);
            vlnCol.setCellValueFactory(new PropertyValueFactory<>("inLastName"));

            //Inmate First Nmae column 
            TableColumn<Visit, String> vVFNCol = new TableColumn<>("Visitor first name");
            vVFNCol.setMinWidth(50);
            vVFNCol.setCellValueFactory(new PropertyValueFactory<>("vtFirstName"));

            //Inmate Last Name column 
            TableColumn<Visit, String> vVLNCol = new TableColumn<>("Visitor Last name");
            vVLNCol.setMinWidth(75);
            vVLNCol.setCellValueFactory(new PropertyValueFactory<>("vtLastName"));

            
            //Visit Time column 
            TableColumn<Visit, String> vTCol = new TableColumn<>("Visit Date");
            vTCol.setMinWidth(100);
            vTCol.setCellValueFactory(new PropertyValueFactory<>("vDt"));

            
            

            

            visitTable = new TableView();
            visitTable.setItems(visoData);

            visitTable.getColumns().addAll(vfnCol, vlnCol, vVFNCol, vVLNCol, vTCol);

            final VBox vboxV = new VBox();
            vboxV.setSpacing(5);
            vboxV.setPadding(new Insets(10, 0, 0, 10));
            vboxV.getChildren().addAll(label, visitTable);

            Scene vTableScene = new Scene(vboxV, 500, 500);
            vTableStage.setScene(vTableScene);
            vTableStage.show();

        });



VBox errorbox = new VBox(fnError, lnError,infnError, inlnError,vTError );
       


         final Button addBtn1 = new Button("Add");
        addBtn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                visoData.add(new Visit(
                        tfINFName.getText(),
                        tfLName.getText(),
                        tfFName.getText(),
                        tfLName.getText(),
                        tfvTime.getText()));
                       
                        
                tfINFName.clear();
                tfINLName.clear();
                tfFName.clear();
                tfLName.clear();
                tfvTime.clear();
               
            }
            });

        Button removeBtn = new Button("Remove");
            removeBtn.setOnAction(e->{
            fnError.setVisible(false);
            lnError.setVisible(false);
            vTError.setVisible(false);
            infnError.setVisible(false);
            inlnError.setVisible(false);
 
 
            Label removeidNum = new Label("Id Number: ");
                removeidNum.setFont(Font.font(15));
            TextField removetfID = new TextField(){
            @Override public void replaceText(int start, int end, String text) {
                if (!text.matches("[a-zA-z\\s]")) {
                super.replaceText(start, end, text);
            }}
            @Override public void replaceSelection(String text) {
                if (!text.matches("[a-zA-z\\s]")) {
                super.replaceSelection(text);
            }}};
            removetfID.setPromptText("ID Number");
            Label removed = new Label("Removed");
                removed.setVisible(false);

        Button newremoveBtn = new Button("Remove");
            newremoveBtn.setOnAction(q->{
             int id = 0;
            id = Integer.parseInt(removetfID.getText());
            visitorMap.remove(id);
            removed.setVisible(true);
            });
            
            
    Stage newStage = new Stage();
    VBox labelbox = new VBox(removeidNum);
    VBox tfbox = new VBox(removetfID);
         labelbox.setAlignment(Pos.CENTER);
         tfbox.setAlignment(Pos.CENTER);
    HBox root = new HBox(labelbox, tfbox);
         root.setAlignment(Pos.CENTER);
    VBox temp = new VBox(root, newremoveBtn, removed);
         temp.setAlignment(Pos.CENTER);
    Scene stageScene = new Scene(temp, 300, 300);
        newStage.setScene(stageScene);
        newStage.show();
 });


HBox hb = new HBox();
HBox hb2 = new HBox();
VBox hb3 = new VBox();

hb.getChildren().addAll(addBtn1, removeBtn,showallVisitbtn);
hb2.getChildren().addAll(labelfields, textfields);
hb3.getChildren().addAll(hb2, hb, errorbox);
return hb3;
}


}
    

  
    

