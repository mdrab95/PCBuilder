package com.pcbuilder.controller;

import com.pcbuilder.MainApp;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import com.pcbuilder.model.ModelHDD;
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
import java.util.Comparator;
import java.util.ResourceBundle;

public class HDDOverviewController implements Initializable {

    @FXML
    private ListView<ModelHDD> hddListView;
    private final ObservableList<ModelHDD> hddData = FXCollections.observableArrayList();
    private final ObservableList<ModelHDD> finalData = FXCollections.observableArrayList();
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void filterButton (ObservableList<ModelHDD> hddDataList){

    }

    @FXML
    public void initialize (URL location, ResourceBundle resources) {
        hddData.clear();
        ModelDataLoaderAndFilter loader = new ModelDataLoaderAndFilter();
        hddData.addAll(loader.hddDataLoader());
        hddListView.setItems(hddData);
        hddListView.setCellFactory(new Callback<ListView<ModelHDD>, ListCell<ModelHDD>>() {
            @Override
            public ListCell<ModelHDD> call(ListView<ModelHDD> param) {
                ListCell<ModelHDD> cell = new ListCell<ModelHDD>(){
                    @Override
                    protected void updateItem(ModelHDD hddItem, boolean empty){
                        setText(null);
                        setGraphic(null);
                        super.updateItem(hddItem, empty);
                        if (hddItem != null) {
                            try {
                                Image img = new Image(hddItem.getSmallImagePath() + hddItem.getSerialNumber() + ".png",  true);
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
                            String capacity = "";
                            if (hddItem.getCapacity() >= 1000)
                                capacity = String.valueOf(hddItem.getCapacity()/1000) + "TB";
                            else
                                capacity = hddItem.getCapacity() + "GB";
                            String hddDescription = hddItem.getBrand() + " " + hddItem.getName() + " " + capacity
                                    + "\nInterface type: " + hddItem.getHddInterfaceType() + ", form factor: " + hddItem.getFormFactor()
                                    + "\nRotational speed: " + hddItem.getRotationalSpeed() + "rpm, cache size: " + hddItem.getCacheSize() + "MB"
                                    + "\nPrice: " + hddItem.getPrice() + "PLN";
                            setText(hddDescription);
                        }
                    }
                };
                return cell;
            }
        });
    }
}
