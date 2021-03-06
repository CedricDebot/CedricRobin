/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.AttitudeOpmerking;
import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Robin Lanneer
 */
public class Attitude extends GridPane{

    private Scene scene;
    
    public Attitude() {
        
        //HoofdGrid
         gridLinesVisibleProperty().set(false);
         ColumnConstraints col0 = new ColumnConstraints();
         col0.setPercentWidth(40);
         
         ColumnConstraints col1 = new ColumnConstraints();
         col1.setPercentWidth(40);
         col1.setHalignment(HPos.CENTER);
         
         ColumnConstraints col2 = new ColumnConstraints();
         col2.setPercentWidth(20);
         col2.setHalignment(HPos.CENTER);
         
         getColumnConstraints().addAll(col0, col1, col2);
            
         RowConstraints row1 = new RowConstraints();
         row1.setPercentHeight(100);
         
         getRowConstraints().addAll(row1);
         
         //OpmerkingPane
         GridPane OpmerkingenPane = new GridPane();
         OpmerkingenPane.gridLinesVisibleProperty().set(false);
         ColumnConstraints col0OpmerkingPane = new ColumnConstraints();
         col0OpmerkingPane.setPercentWidth(100);
         col0OpmerkingPane.setHalignment(HPos.CENTER);
         
         RowConstraints row0Terug = new RowConstraints();
         row0Terug.setPercentHeight(22.5);
         
         RowConstraints row1Label = new RowConstraints();
         row1Label.setPercentHeight(10);
         row1Label.setValignment(VPos.BOTTOM);
         
         RowConstraints row2Text = new RowConstraints();
         row2Text.setPercentHeight(45);
         row2Text.setValignment(VPos.CENTER);
         
         RowConstraints row3Bewaar = new RowConstraints();
         row3Bewaar.setPercentHeight(22.5);
         row3Bewaar.setValignment(VPos.TOP);
         
         OpmerkingenPane.getColumnConstraints().add(col0OpmerkingPane);
         OpmerkingenPane.getRowConstraints().addAll(row0Terug,row1Label,row2Text,row3Bewaar);
         
         Image terugPijl = new Image("images/terug-pijl.png");
         ImageView terugPijlImageView = new ImageView(terugPijl);
         terugPijlImageView.setFitWidth(100);
         terugPijlImageView.setFitHeight(50);
         Button terugBtn = new Button("", terugPijlImageView);
         terugBtn.setId("menuButton");
        
         OpmerkingenPane.add(terugBtn,0 , 0);
         
         terugBtn.setOnAction(e -> {
            Dashboard dashboard = new Dashboard();
            dashboard.setScene(scene);
            scene.setRoot(dashboard);
        });
         
         Label opmerking = new Label("Opmerking");
         opmerking.setId("OpmerkingLabel");
         OpmerkingenPane.add(opmerking,0 , 1);
         
         TextArea opmerkingVeld = new TextArea();
         opmerkingVeld.setId("OpmerkingenVeld");
         OpmerkingenPane.add(opmerkingVeld, 0, 2);
         
         Button bewaarOpmerking = new Button("Bewaar");
         OpmerkingenPane.add(bewaarOpmerking, 0, 3);
         
         //listview
         //standaardOpmerkingen
         AttitudeOpmerking Zenuwachtig = new AttitudeOpmerking("Zenuwachtig", "");
         AttitudeOpmerking Concentratie = new AttitudeOpmerking("Concentratie", "");
         AttitudeOpmerking Schrik = new AttitudeOpmerking("Schrik", "");
         AttitudeOpmerking Asociaal = new AttitudeOpmerking("Asociaal", "");
         AttitudeOpmerking Verkeersgevaarlijk = new AttitudeOpmerking("Verkeersgevaarlijk", "");
         AttitudeOpmerking AgressiefRijgedrag = new AttitudeOpmerking("Agressief rijgedrag", "");
         AttitudeOpmerking Inzet = new AttitudeOpmerking("Inzet", "");
         AttitudeOpmerking Verstrooid = new AttitudeOpmerking("Verstrooid", "");
         AttitudeOpmerking Eigenwijs = new AttitudeOpmerking("Eigenwijs", "");
         
         ArrayList<AttitudeOpmerking> StandaardOpmerkingenList = new ArrayList<>();
         StandaardOpmerkingenList.add(Zenuwachtig);
         StandaardOpmerkingenList.add(Concentratie);
         StandaardOpmerkingenList.add(Schrik);
         StandaardOpmerkingenList.add(Asociaal);
         StandaardOpmerkingenList.add(Verkeersgevaarlijk);
         StandaardOpmerkingenList.add(AgressiefRijgedrag);
         StandaardOpmerkingenList.add(Inzet);
         StandaardOpmerkingenList.add(Verstrooid);
         StandaardOpmerkingenList.add(Eigenwijs);
         
         //Listview
         ListView opmerkingenList = new ListView();
         opmerkingenList.setId("OpmerkingenList");
         ObservableList<String> standaarOpmerkingen = 
                 FXCollections.observableArrayList();
         
         StandaardOpmerkingenList.stream().forEach((Opmerking) -> {
             standaarOpmerkingen.add(Opmerking.getNaam());
        });
         
         opmerkingenList.setItems(standaarOpmerkingen);
         
         opmerkingenList.setOnMouseClicked(event ->{
             
             String selectedOpmerking2 = opmerkingenList.getSelectionModel().getSelectedItem().toString();
             StandaardOpmerkingenList.stream().forEach((opmerkingske) ->{
                 if(opmerkingske.getNaam().equals(selectedOpmerking2)){
                     
                     opmerkingVeld.setText(opmerkingske.getOpmerking());
                 }
             });
         });
         //voegtoe
         Button voegToe = new Button("Voeg Toe");
         TextField nieuw = new TextField();
         HBox nieuwHB = new HBox();
         nieuwHB.setId("nieuwHB");
         nieuwHB.getChildren().addAll(nieuw, voegToe);
         
         voegToe.setId("attitudeVoegToe");
         VBox attitudeList = new VBox();
         attitudeList.setId("attitudeList");
         
         attitudeList.getChildren().addAll(opmerkingenList, nieuwHB);
         
         
         bewaarOpmerking.setOnAction(e ->{
             
             String selectedOpmerking = opmerkingenList.getSelectionModel().getSelectedItem().toString();
             
             StandaardOpmerkingenList.stream().forEach((opmerkingske) ->{
                 if(opmerkingske.getNaam().equals(selectedOpmerking)){
                     opmerkingske.setOpmerking(opmerkingVeld.getText());
                 }
             });
             opmerkingVeld.clear();
         });
         
         //Menu
        Menu menu = new Menu();
        //RIGHT
        VBox right = new VBox();
        //MenuStandaard
        VBox menuStandaard = menu.buildMenuStandaard();
        right.getChildren().add(menuStandaard);
        
        
        //Menu
        VBox menuBalk = menu.buildMenu();
        
        menu.getMenuKnop().setOnAction(e -> {
            menu.setScene(scene);
            right.getChildren().remove(menuStandaard);        
                       
            TranslateTransition tt = new TranslateTransition(Duration.millis(500), menuBalk);
            
            tt.setFromX(100.0 + menuBalk.getLayoutX());
            tt.setByX(-90);
            tt.setCycleCount(1);
            
            tt.play();
            
            right.getChildren().addAll(menuBalk);
        });
        
        menu.getMenuTerug().setOnAction(e -> {            
            TranslateTransition tt = new TranslateTransition(Duration.millis(500), menuBalk);
            tt.setOnFinished(ev -> {
                right.getChildren().removeAll(menuBalk);
                right.getChildren().add(menuStandaard);
            });
            
            tt.setFromX(menuBalk.getLayoutX());
            tt.setByX(120);
            tt.setCycleCount(1);
            
            tt.play();
            tt.onFinishedProperty();
            
        });
        
        
         add(OpmerkingenPane,0,0);
         add(attitudeList, 1, 0);
         add(right, 2 , 0);
         
         voegToe.setOnAction(e ->{
             if(nieuw.getText().equalsIgnoreCase("")){
                 
             }else{
             AttitudeOpmerking nieuweStand = new AttitudeOpmerking(nieuw.getText(), "");
             StandaardOpmerkingenList.add(nieuweStand);
             standaarOpmerkingen.add(nieuweStand.getNaam());
             nieuw.clear();
             opmerkingVeld.clear();
             }
         });
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
