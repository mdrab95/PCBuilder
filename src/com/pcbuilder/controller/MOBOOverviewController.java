package com.pcbuilder.controller;

import com.pcbuilder.MainApp;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import com.pcbuilder.model.ModelMOBO;
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

/**
 * Created by Ace on 26.05.2017.
 */
public class MOBOOverviewController implements Initializable{
    @FXML
    private ListView<ModelMOBO> moboListView;
    private final ObservableList<ModelMOBO> moboData = FXCollections.observableArrayList();
    private final ObservableList<ModelMOBO> finalData = FXCollections.observableArrayList();
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void filterButton (ObservableList<ModelMOBO> moboDataList){

    }

    @FXML
    public void initialize (URL location, ResourceBundle resources) {
        moboData.clear();
        ModelDataLoaderAndFilter loader = new ModelDataLoaderAndFilter();
        moboData.addAll(loader.moboDataLoader());
        moboListView.setItems(moboData);
        moboListView.setCellFactory(new Callback<ListView<ModelMOBO>, ListCell<ModelMOBO>>() {
            @Override
            public ListCell<ModelMOBO> call(ListView<ModelMOBO> param) {
                ListCell<ModelMOBO> cell = new ListCell<ModelMOBO>(){
                    @Override
                    protected void updateItem(ModelMOBO moboItem, boolean empty){
                        setText(null);
                        setGraphic(null);
                        super.updateItem(moboItem, empty);
                        if (moboItem != null) {
                            try {
                                Image img = new Image(moboItem.getSmallImagePath() + moboItem.getSerialNumber() + ".png", true);
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

                            setText(moboItem.getBrand() + " " + moboItem.getChipset() + " " + moboItem.getName()
                                    + "\n" + "Socket: " + moboItem.getSocket() + ", form factor: " + moboItem.getFormFactor()
                                    + "\nRAM standard: " + moboItem.getRamStandard() + ", Max RAM speed: " + moboItem.getMaxRamSpeed() + " MHz"
                                    + "\nMax RAM capacity: " + moboItem.getMaxRam() + "GB, in " + moboItem.getRamSlots() + " slots."
                                    + "\nConnectors: " + moboItem.getConnectors()
                                    + "\nPrice: " + moboItem.getPrice() + " PLN");
                        }
                    }
                };
                return cell;
            }
        });
    }
}

