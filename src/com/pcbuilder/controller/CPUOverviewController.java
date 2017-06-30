package com.pcbuilder.controller;

import com.pcbuilder.MainApp;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import com.pcbuilder.model.ModelCPU;
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

public class CPUOverviewController implements Initializable {

    @FXML
    private ListView<ModelCPU> cpuListView;
    private final ObservableList<ModelCPU> cpuData = FXCollections.observableArrayList();
    private final ObservableList<ModelCPU> finalData = FXCollections.observableArrayList();
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void filterButton (ObservableList<ModelCPU> cpuDataList){

    }


    @FXML
    public void initialize (URL location, ResourceBundle resources) {
        cpuData.clear();
        ModelDataLoaderAndFilter loader = new ModelDataLoaderAndFilter();
        cpuData.addAll(loader.cpuDataLoader());
        cpuListView.setItems(cpuData);
        cpuListView.setCellFactory(new Callback<ListView<ModelCPU>, ListCell<ModelCPU>>() {
            @Override
            public ListCell<ModelCPU> call(ListView<ModelCPU> param) {
                ListCell<ModelCPU> cell = new ListCell<ModelCPU>(){
                    @Override
                    protected void updateItem(ModelCPU cpuItem, boolean empty){
                        setText(null);
                        setGraphic(null);
                        super.updateItem(cpuItem, empty);
                        if (cpuItem != null) {
                            try {
                                Image img = new Image(cpuItem.getSmallImagePath(), true);
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
                            String howItIsPacked = cpuItem.getPackageType();
                            String spaceInName = " ";
                            if (cpuItem.getFamily().contains("Core"))
                                spaceInName = "-";
                            if (cpuItem.getBoxCooler() == false)
                                howItIsPacked += " without cooler";
                            String speed = "";
                            if (cpuItem.getSpeed() == cpuItem.getBoostSpeed())
                                speed = cpuItem.getSpeed() + "GHz";
                            else
                                speed = cpuItem.getSpeed() + "-" + cpuItem.getBoostSpeed() + "GHz";
                            String integratedGraphic = "no";
                            if (cpuItem.getHasIntegratedGraphic() == true)
                                integratedGraphic = "yes";

                            setText(cpuItem.getBrand() + " " + cpuItem.getFamily() + spaceInName +  cpuItem.getName() + " (" + howItIsPacked + ")"
                                    + "\n" + cpuItem.getNumberOfCores() + "C/" + cpuItem.getNumberOfThreads() + "T, " + speed + ", " + mainApp.noZeros(cpuItem.getCacheL3()) + " MB" + ", "
                                    + cpuItem.getTdp() + "W TDP"
                                    + "\nIntegrated graphic card: " + integratedGraphic
                                    + "\nPrice: " + cpuItem.getPrice() + " PLN");
                        }
                    }
                };
                return cell;
            }
        });
    }
}
