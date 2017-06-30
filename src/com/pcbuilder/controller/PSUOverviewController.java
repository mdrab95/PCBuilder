package com.pcbuilder.controller;

import com.pcbuilder.MainApp;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import com.pcbuilder.model.ModelPSU;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PSUOverviewController implements Initializable {

    @FXML
    private ListView<ModelPSU> psuListView;
    private ObservableList<ModelPSU> psuData = FXCollections.observableArrayList();
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void filterButton (ObservableList<ModelPSU> psuDataList){

    }

    @FXML
    public void initialize (URL location, ResourceBundle resources) {
        psuData.clear();
        ModelDataLoaderAndFilter loader = new ModelDataLoaderAndFilter();
        psuData.addAll(loader.psuDataLoader());
        psuListView.setItems(psuData);
        psuListView.setCellFactory(new Callback<ListView<ModelPSU>, ListCell<ModelPSU>>() {
            @Override
            public ListCell<ModelPSU> call(ListView<ModelPSU> param) {
                ListCell<ModelPSU> cell = new ListCell<ModelPSU>(){
                    @Override
                    protected void updateItem(ModelPSU psuItem, boolean empty){
                        setText(null);
                        setGraphic(null);
                        super.updateItem(psuItem, empty);
                        if (psuItem != null) {
                            try {
                                Image img = new Image(psuItem.getSmallImagePath()+ psuItem.getManufacturerCode() + ".png", true);
                                ImageView imageView = new ImageView(img);
                                mainApp.setImgSize(imageView);
                                setGraphic(imageView);
                            }
                            catch (Exception ex){
                                Image img = new Image("images/no_img.png");
                                ImageView imageView = new ImageView(img);
                                mainApp.setImgSize(imageView);
                                setGraphic(imageView);
                            }

                            String modular = "";
                            if (psuItem.getIsModular() == true)
                                modular = "Modular";

                            String certificate = "";
                            if (psuItem.getCertificate80Plus().length()==0)
                                certificate = "no data";
                            else
                                certificate = psuItem.getCertificate80Plus();

                            setText(psuItem.getBrand() + " " + psuItem.getName() + " " + psuItem.getWattage() + "W " + modular
                                    + "\nCertificate: " + certificate + ", Protection:  " + psuItem.getProtection()
                                    + "\nPrice: " + psuItem.getPrice() + " PLN");
                        }
                    }
                };
                return cell;
            }
        });
    }
}
