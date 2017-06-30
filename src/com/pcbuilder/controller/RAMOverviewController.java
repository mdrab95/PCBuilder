package com.pcbuilder.controller;

import com.pcbuilder.MainApp;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import com.pcbuilder.model.ModelRAM;
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

public class RAMOverviewController implements Initializable {

    @FXML
    private ListView<ModelRAM> ramListView;
    private final ObservableList<ModelRAM> ramData = FXCollections.observableArrayList();
    private final ObservableList<ModelRAM> finalData = FXCollections.observableArrayList();
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void filterButton (ObservableList<ModelRAM> ramDataList){

    }

    @FXML
    public void initialize (URL location, ResourceBundle resources) {
        ramData.clear();
        ModelDataLoaderAndFilter loader = new ModelDataLoaderAndFilter();
        ramData.addAll(loader.ramDataLoader());
        ramListView.setItems(ramData);
        ramListView.setCellFactory(new Callback<ListView<ModelRAM>, ListCell<ModelRAM>>() {
            @Override
            public ListCell<ModelRAM> call(ListView<ModelRAM> param) {
                ListCell<ModelRAM> cell = new ListCell<ModelRAM>(){
                    @Override
                    protected void updateItem(ModelRAM ramItem, boolean empty){
                        setText(null);
                        setGraphic(null);
                        super.updateItem(ramItem, empty);
                        if (ramItem != null) {
                            try {
                                String ramSerial = ramItem.getSerialNumber().replaceAll("/", "-");
                                Image img = new Image(ramItem.getSmallImagePath() + ramSerial + ".png", true);
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
                            String ramDescription = ramItem.getBrand() + " " + ramItem.getName() + " " + ramItem.getMemorySize() + "GB (" + ramItem.getNumberOfModules() + "x" + ramItem.getSingleModuleSize() + "GB) " + ramItem.getStandard() + " " + ramItem.getMemoryClock() + "MHz (" + ramItem.getSerialNumber() + ")"
                                    + "\nNumber of modules: " + ramItem.getNumberOfModules() + ", module size: " + ramItem.getSingleModuleSize() + "GB"
                                    + "\nCAS Latency: " + ramItem.getCasLatency()
                                    + "\nPrice: " + ramItem.getPrice() + " PLN";
                            setText(ramDescription);
                        }
                    }
                };
                return cell;
            }
        });
    }
}
