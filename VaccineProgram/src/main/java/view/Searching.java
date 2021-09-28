package view;

/**
 * Searching and listing tab for clients/vaccines
 * @author Micheal O' Sullivan
 * 
 */

import controller.ClientCollectionController;
import controller.FullVaccineController;
import controller.ClientController;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import models.FullVaccine;
import models.Client;
import models.ClientCollection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Searching extends Tab{
    private VBox vBox;
    private GridPane grid;
    private FlowPane pane;
    private TextField clientSearch, vaccineSearch;
    private Label fullVaccineList,clientListed,searchClient, searchVaccine;
    private ListView fullVaccineListView,searchedList, vaccineList;
    private Button search, search2, updateList;
    private HBox hbButtonVac,hbSearch;
    private FullVaccineController fullVaccineController=new FullVaccineController();
    private ClientController clientController=new ClientController();
    private List<ClientCollection> listClientColl;
    private List<FullVaccine> listFullVaccine;
    private ClientCollectionController clientCollectionController=new ClientCollectionController();
    private List<Client> listClient;
    private List<FullVaccine> fullVaccine;

    public Searching(){
        setClosable(false);
        setText("LIST/SEARCH");

        pane = new FlowPane();
        grid=new GridPane();
        
        fullVaccineListView= new ListView();
        hbButtonVac=new HBox();
        vBox = new VBox();
        hbSearch=new HBox();


        fullVaccineListView.setPrefHeight(100);
        fullVaccineListView.setPrefWidth(650);
        fullVaccineList=new Label("Vaccines In System: ");
        fullVaccineList.setFont(Font.font ("Arial Black", 20));

        listFullVaccine=fullVaccineController.findFullVaccines();
        listClientColl=clientCollectionController.getClientCollections();
        
        
        ////////////// TOP(VACCINE) LISTVIEW ///////////////

        updateList=new Button("Update Vaccine list");
        updateList.setOnAction(e->{
            listFullVaccine=fullVaccineController.findFullVaccines();

            fullVaccineListView.getItems().clear();
            for(int i=0;i<listFullVaccine.size();i++){
                fullVaccineListView.getItems().add(listFullVaccine.get(i));
            }
        });

        for(int i=0;i<listFullVaccine.size();i++){
            fullVaccineListView.getItems().add(listFullVaccine.get(i));
        }

        fullVaccineListView.setStyle("-fx-font-size: 12;");


        grid.add(fullVaccineListView,0,1);
        hbButtonVac.getChildren().addAll(fullVaccineList,updateList);
        grid.add(hbButtonVac,0,0);
        
        
        ////////////// CLIENT LISTVIEW ///////////////

        searchClient=new Label("Search Client By ID: ");
        searchClient.setFont(Font.font ("Arial Black", 20));

        search=new Button("Search");
        search2=new Button("List All Vaccines+Clients");
        clientSearch=new TextField();

        searchedList=new ListView();

        searchedList.setPrefHeight(150);
        searchedList.setPrefWidth(650);

        searchedList.setStyle("-fx-font-size: 12;");
        
        search.setOnAction(e->{

            searchedList.getItems().clear();

            listClient=clientController.findClientByID(clientSearch.getText());

            if(listClient.size()==0){
                searchedList.getItems().add("Not found");
            }else{
                fullVaccine=fullVaccineController.findFullVaccineByCode(listClient.get(0).getClientCollectionCode().toString());

                for(int i=0;i<listClient.size();i++){
                    searchedList.getItems().add(listClient.get(i));
                    searchedList.getItems().add("Reciving Vaccine:");
                    searchedList.getItems().add(fullVaccine.get(0));
                }
            }
        });
        
        
        
        

        hbSearch.getChildren().addAll(searchClient,clientSearch,search, search2);
        hbSearch.setPadding(new Insets(30, 30, 0, 5));
        grid.add(hbSearch,0,3);
        grid.add(searchedList,0,4);


        vBox.getChildren().addAll(grid);
        vBox.setPadding(new Insets(50, 30, 0, 5));
        pane.getChildren().add(vBox);

        setContent(pane);
        
       
    }

}
