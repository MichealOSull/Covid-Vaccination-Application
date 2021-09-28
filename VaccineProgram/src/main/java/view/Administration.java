package view;

/**
 * Class for administration tab
 * @author Micheal O' Sullivan
 * 
 */


import controller.ClientController;
import controller.ClientCollectionController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Client;
import models.ClientCollection;
import models.FullVaccine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Administration {
    private TextField clientsCollTxtField;
    private TextField fNameTxtF, lNameTxtF, emailTxtF, phoneTxtF, vac2TxtF;
    private Label codeLabel,clientsListed, clientLabel, vaccineLabel, vaccineList, clientsCollListed;
    private GridPane buttonsPane, buttonsBottomPane, grid, textFieldPad, tabsPane;
    private ListView clientCollListView, clientListView;
    private TabPane tabPane = new TabPane();
    private Tab tabMang;
    private Button buttonAddCCollection, buttonAddClient, buttonAddFullVaccine, buttonRemove, buttonSortByName, buttonAllClients, buttonQuit;
    private HBox hbAddCCollection, hbAddClient, hbAddFullVac, hbEdit, hbMiddle;
    private VBox vBox;
    private Scene scene;
    private int id;
    private List<Client> listClient;
    private ClientController cCont = new ClientController();
    private ClientCollectionController cColCont = new ClientCollectionController();
    private List<ClientCollection> listClientColl;
    private List<ClientCollection> listClientColl2;
    private List<FullVaccine> fullVaccine;

    public Scene administration(Stage primaryStage){
        var spacer = new Region();

        buttonsPane=new GridPane();
        buttonsPane.setHgap(12);
        buttonsPane.setPadding(new Insets(0,0,2,5));

        buttonsBottomPane=new GridPane();
        buttonsBottomPane.setHgap(2);
        buttonsBottomPane.setPadding(new Insets(0,5,2,0));

        grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(7);
        grid.setPadding(new Insets(5, 5, 0, 5));

        textFieldPad=new GridPane();
        textFieldPad.setHgap(5);
        textFieldPad.setPadding(new Insets(5,5,5,5));

        tabsPane=new GridPane();
        tabsPane.setPadding(new Insets(5,5,0,5));

        
        
        /////////////// TABS ///////////////
        tabMang = new Tab("ADMIN");
        tabMang.setClosable(false);

        tabPane.getTabs().add(new Intro());
        tabPane.getTabs().add(tabMang);
        tabPane.getTabs().add(new Searching());



        ////////////// TOP FIELDS ///////////////
        
        clientsCollTxtField = new TextField();
        grid.add(clientsCollTxtField, 1, 1);


        ////////////// MIDDLE ///////////////


        listClientColl=cColCont.getClientCollections();

        clientCollListView = new ListView();
        clientCollListView.setPrefHeight(100);
        clientCollListView.setPrefWidth(200);
        clientCollListView.setStyle("-fx-font-size: 12; ");

  
        codeLabel=new Label("Enter Location Code ");
        clientLabel=new Label("Click Here To Add Client");
        vaccineLabel=new Label("Click Here To Add Vaccine");
        
        
        vaccineList=new Label("Location Codes In Database");
        vaccineList.setFont(Font.font ("Arial Black", 20));


        printClientCollection(listClientColl);
        textFieldPad.add(vaccineList,0,0);
        textFieldPad.add(clientCollListView,0,1);
        

        EventHandler<ActionEvent> selectClientCollection =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        System.out.println(clientCollListView.getSelectionModel() + " selected");
                    }
                };


                clientListView=new ListView();
                clientListView.setPrefHeight(300);
                clientListView.setPrefWidth(650);
                
                clientsListed=new Label("Clients In System");
                clientsListed.setFont(Font.font ("Arial Black", 20));



        ////////////// BUTTONS FOR FIELD ///////////////


        buttonAllClients=new Button("All Clients");
        hbMiddle=new HBox(10);
        
       
        buttonSortByName=new Button("Sort By Name");
        hbMiddle=new HBox(10);
        
        buttonRemove = new Button("Remove Player");
        hbMiddle=new HBox(10);
        
        buttonQuit = new Button("Quit Program");
        hbMiddle=new HBox(10);
        

        hbMiddle.setPadding(new Insets(20, 20, 0, 2));

        
        hbMiddle.getChildren().addAll(clientsListed,buttonAllClients, buttonSortByName, buttonRemove, buttonQuit);
        textFieldPad.add(hbMiddle,0,3);
        textFieldPad.add(clientListView,0,4);


        //////////////BUTTONS FOR FIELD ///////////////
        
        buttonAllClients.setOnAction(e-> {
        	listClient=cCont.findClients();
            clientListView.getItems().clear();
            for(int i=0;i<listClient.size();i++){
            	clientListView.getItems().add(listClient.get(i));
            }

        });


              
        buttonSortByName.setOnAction(e ->{
            clientListView.getItems().clear();
        	listClient=cCont.sortName(listClient);
        	printAllClients(listClient);
            
        	
        });
        
        																													
        buttonRemove.setOnAction(e-> {
                cCont.remClient(id);
                clientListView.getItems().clear();
                clientListView.getItems().add("Client removed.");

        });
        
        
        buttonQuit.setOnAction(e->{
        	primaryStage.close();
        });



        ////////////// BUTTONS TOP ///////////////
        spacer.setPrefWidth(50);
        grid.add(codeLabel,0,1);
        buttonAddCCollection = new Button("Add Code");
        hbAddCCollection = new HBox(3);
        hbAddCCollection.getChildren().add(buttonAddCCollection);
        grid.add(hbAddCCollection, 3, 1);
        
        ClientCollection c=new ClientCollection();

        buttonAddCCollection.setOnAction(e -> {
        	cColCont.addClientCollection(c,clientsCollTxtField.getText());

            clientCollListView.getItems().clear();
            listClientColl2=cColCont.getClientCollections();
            List<ClientCollection> listClientColl=cColCont.sortCode(listClientColl2);
            printClientCollection(listClientColl);
            
          


        });

        grid.add(clientLabel,0,2);
        buttonAddClient = new Button("Add Client");
        hbAddClient = new HBox(3);
        hbAddClient.getChildren().add(buttonAddClient);
        grid.add(hbAddClient, 1, 2);

        grid.add(vaccineLabel,0,3);
        buttonAddFullVaccine = new Button("Add Vaccine");
        hbAddFullVac = new HBox(3);
        hbAddFullVac.getChildren().add(buttonAddFullVaccine);
        grid.add(hbAddFullVac, 1, 3);


        ////////////// BUTTONS FOR POPUP OPTIONS ///////////////

        buttonAddClient.setOnAction(e -> ClientPopUp.displayClientPopUp());
        buttonAddFullVaccine.setOnAction(e -> FullVaccinePopUp.displayFullVaccinePopUp());

        ////////////// SCENE ///////////////

        vBox = new VBox(5,grid,buttonsPane,textFieldPad,buttonsBottomPane);
        vBox.setPadding(new Insets(50, 20, 0, 2));

        tabMang.setContent(vBox);
        scene = new Scene(tabPane, 650, 700);

        return scene;
    }

    public void printClientCollection(List<ClientCollection> listClientColl){

        for(int i=0;i<listClientColl.size();i++){
            System.out.println(listClientColl.get(i));
            clientCollListView.getItems().add(listClientColl.get(i));
        }
    }

    
    public void sortName(List<Client> listClient){
        for(int i=0;i<listClient.size();i++){
            clientListView.getItems().add(listClient.get(i));
        }

    }
    
    public void printAllClients(List<Client> listClient){
        for(int i=0;i<listClient.size();i++){
            clientListView.getItems().add(listClient.get(i));
        }
}
}