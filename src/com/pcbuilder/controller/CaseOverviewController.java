package com.pcbuilder.controller;

import com.pcbuilder.MainApp;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import com.pcbuilder.model.ModelCase;
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

public class CaseOverviewController implements Initializable {
    
    @FXML
    private ListView<ModelCase> caseListView;
    private final ObservableList<ModelCase> caseData = FXCollections.observableArrayList();
    private final ObservableList<ModelCase> finalData = FXCollections.observableArrayList();
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void filterButton (ObservableList<ModelCase> caseDataList){

    }


    @FXML
    public void initialize (URL location, ResourceBundle resources) {
        caseData.clear();
        ModelDataLoaderAndFilter loader = new ModelDataLoaderAndFilter();
        caseData.addAll(loader.caseDataLoader());
        caseListView.setItems(caseData);
        caseListView.setCellFactory(new Callback<ListView<ModelCase>, ListCell<ModelCase>>() {

            @Override
            public ListCell<ModelCase> call(ListView<ModelCase> param) {
                ListCell<ModelCase> cell = new ListCell<ModelCase>(){
                    @Override
                    protected void updateItem(ModelCase caseItem, boolean empty){
                        setText(null);
                        setGraphic(null);
                        super.updateItem(caseItem, empty);
                        if (caseItem != null) {
                            try {
                                Image img = new Image(caseItem.getSmallImagePath() + caseItem.getSerialNumber() + ".png", true);
                                ImageView imageView = new ImageView(img);
                                imageView.setFitHeight(100);
                                imageView.setFitWidth(100);
                                setGraphic(imageView);
                            }
                            catch (Exception ex){
                                Image img = new Image("images/no_img.png");
                                ImageView imageView = new ImageView(img);
                                imageView.setFitHeight(100);
                                imageView.setFitWidth(100);
                                setGraphic(imageView);
                            }

                            setText(caseItem.getBrand() + " " + caseItem.getName()
                                    + "\nCompatibility: " + caseItem.getFormFactor()
                                    + "\nType: " + caseItem.getType()
                                    + "\nInstalled fans - front: " +  caseItem.getFrontFanNumber() + "x" + caseItem.getFrontFanSize()
                                    + "mm, top: " + caseItem.getTopFanNumber() + "x" + caseItem.getTopFanSize() + "mm, rear: " + caseItem.getRearFanNumber() + "x"
                                    + caseItem.getRearFanSize() + "mm"
                                    + "\nMax fan number - front: " +  caseItem.getMaxFrontFanNumber() + "x" + caseItem.getFrontFanSize()
                                    + "mm, top: " + caseItem.getMaxTopFanNumber() + "x" + caseItem.getTopFanSize() + "mm, rear: " + caseItem.getMaxRearFanNumber() + "x"
                                    + caseItem.getRearFanSize() + "mm"
                                    + "\nPrice: " + caseItem.getPrice() + " PLN");
                        }
                    }
                };
                return cell;
            }
        });
    }
}
