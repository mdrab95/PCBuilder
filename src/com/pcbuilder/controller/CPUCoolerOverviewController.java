package com.pcbuilder.controller;

import com.pcbuilder.MainApp;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import com.pcbuilder.model.ModelCPUCooler;
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

public class CPUCoolerOverviewController implements Initializable {

    @FXML
    private ListView<ModelCPUCooler> cpuCoolerListView;
    private final ObservableList<ModelCPUCooler> cpuCoolerData = FXCollections.observableArrayList();
    private final ObservableList<ModelCPUCooler> finalData = FXCollections.observableArrayList();
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void filterButton (ObservableList<ModelCPUCooler> cpuCoolerDataList){

    }


    @FXML
    public void initialize (URL location, ResourceBundle resources) {
        cpuCoolerData.clear();
        ModelDataLoaderAndFilter loader = new ModelDataLoaderAndFilter();
        cpuCoolerData.addAll(loader.cpuCoolerDataLoader());
        cpuCoolerListView.setItems(cpuCoolerData);
        cpuCoolerListView.setCellFactory(new Callback<ListView<ModelCPUCooler>, ListCell<ModelCPUCooler>>() {
            @Override
            public ListCell<ModelCPUCooler> call(ListView<ModelCPUCooler> param) {
                ListCell<ModelCPUCooler> cell = new ListCell<ModelCPUCooler>(){
                    @Override
                    protected void updateItem(ModelCPUCooler cpuCoolerItem, boolean empty){
                        setText(null);
                        setGraphic(null);
                        super.updateItem(cpuCoolerItem, empty);
                        if (cpuCoolerItem != null) {
                            try {
                                Image img = new Image(cpuCoolerItem.getSmallImagePath() + cpuCoolerItem.getManufacturerCode() + ".png", true);
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
                            String dimensions = mainApp.noZeros(cpuCoolerItem.getWidth()) + "x" + mainApp.noZeros(cpuCoolerItem.getHeight()) + "x" + mainApp.noZeros(cpuCoolerItem.getDepth()) + "mm";
                            String fans = "";
                            if (cpuCoolerItem.getNumberOfFans() >1)
                                fans += cpuCoolerItem.getNumberOfFans() + "x ";
                            fans += mainApp.noZeros(cpuCoolerItem.getFanSizeX()) + "x" + mainApp.noZeros(cpuCoolerItem.getFanSizeY()) + "x" + mainApp.noZeros(cpuCoolerItem.getFanSizeHeight()) + "mm";
                            String noise = "";
                            if (cpuCoolerItem.getMinFanNoise() == cpuCoolerItem.getMaxFanNoise())
                                noise = cpuCoolerItem.getMaxFanNoise()+ "dBA";
                            else
                                noise = cpuCoolerItem.getMinFanNoise() + "-" + cpuCoolerItem.getMaxFanNoise() + "dBA";
                            String fanSpeed = "";
                            if (cpuCoolerItem.getMinFanSpeed() == cpuCoolerItem.getMaxFanSpeed())
                                fanSpeed = cpuCoolerItem.getMaxFanSpeed() + "rpm";
                            else
                                fanSpeed = cpuCoolerItem.getMinFanSpeed() + "-" + cpuCoolerItem.getMaxFanSpeed() + "rpm";

                            setText(cpuCoolerItem.getBrand() + " " + cpuCoolerItem.getName() + " (" + cpuCoolerItem.getManufacturerCode() + ")"
                                    + "\nDesign: " + cpuCoolerItem.getDesign() + ", Dimensions (WxHxD): " + dimensions + ", Fan(s): " + fans
                                    + "\nWeight: " + cpuCoolerItem.getWeight() + "g, " + fanSpeed +  ", " + cpuCoolerItem.getAirFlow() + "m³/h, " + noise + ", Connector: "  + cpuCoolerItem.getConnectorType()
                                    + "\nPrice: " + cpuCoolerItem.getPrice() + " PLN");
                        }
                    }
                };
                return cell;
            }
        });
    }
}
