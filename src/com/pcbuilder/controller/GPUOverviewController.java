package com.pcbuilder.controller;

import com.pcbuilder.MainApp;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import com.pcbuilder.model.ModelGPU;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GPUOverviewController implements Initializable {

    private String getChipManufacturers(CheckBox cb1, CheckBox cb2) {
        String chipManufacturers = "";
        if (cb1.isSelected()) {
            chipManufacturers += cb1.getText();
        }
        if (cb2.isSelected()) {
            chipManufacturers += cb2.getText();
        }
        return chipManufacturers;
    }

    private FilteredList<ModelGPU> filterChipManufacturer(FilteredList<ModelGPU> list, CheckBox cb1, CheckBox cb2) {
        if (cb1.isSelected() == false && cb2.isSelected() == false){
        }
        else {
            String chipManufacturers = getChipManufacturers(cb1, cb2);
            list.setPredicate(gpu -> {
                if (chipManufacturers.contains(gpu.getChipManufacturer()))
                    return true;
                else
                    return false;
            });
        }
        return list;
    }

    private String getManufacturers(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4) {
        String chipManufacturers = "";
        if (cb1.isSelected()) {
            chipManufacturers += cb1.getText();
        }
        if (cb2.isSelected()) {
            chipManufacturers += cb2.getText();
        }
        if (cb3.isSelected()) {
            chipManufacturers += cb3.getText();
        }
        if (cb4.isSelected()) {
            chipManufacturers += cb4.getText();
        }
        return chipManufacturers;
    }

    private FilteredList<ModelGPU> filterManufacturer(FilteredList<ModelGPU> list, CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4) {
        if (cb1.isSelected() == false && cb2.isSelected() == false && cb3.isSelected() == false && cb4.isSelected() == false){}
        else {
            String manufacturers = getManufacturers(cb1, cb2, cb3, cb4);
            list.setPredicate(gpu -> {
                if (manufacturers.contains(gpu.getManufacturer()))
                    return true;
                else
                    return false;
            });
        }
        return list;
    }

    private String getMemorySizeClass(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4, CheckBox cb5, CheckBox cb6) {
        String memorySizeClass = "";
        if (cb1.isSelected()) {
            memorySizeClass += cb1.getText();
        }
        if (cb2.isSelected()) {
            memorySizeClass += cb2.getText();
        }
        if (cb3.isSelected()) {
            memorySizeClass += cb3.getText();
        }
        if (cb4.isSelected()) {
            memorySizeClass += cb4.getText();
        }
        if (cb5.isSelected()) {
            memorySizeClass += cb5.getText();
        }
        if (cb6.isSelected()) {
            memorySizeClass += cb6.getText();
        }
        return memorySizeClass;
    }

    private FilteredList<ModelGPU> filterMemorySizeClass(FilteredList<ModelGPU> list, CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4, CheckBox cb5, CheckBox cb6) {
        if (cb1.isSelected() == false && cb2.isSelected() == false && cb3.isSelected() == false && cb4.isSelected() == false && cb5.isSelected() == false && cb6.isSelected() == false){}
        else {
            String memorySizeClass = getMemorySizeClass(cb1, cb2, cb3, cb4, cb5, cb6);
            list.setPredicate(gpu -> {
                if (memorySizeClass.contains(gpu.getmemorySizeClass()))
                    return true;
                else
                    return false;
            });
        }
        return list;
    }

    private String getMemoryType(CheckBox cb1, CheckBox cb2, CheckBox cb3) {
        String memoryType = "";
        if (cb1.isSelected()) {
            memoryType += cb1.getText();
        }
        if (cb2.isSelected()) {
            memoryType += cb2.getText();
        }
        if (cb3.isSelected()) {
            memoryType += cb3.getText();
        }
        return memoryType;
    }

    private FilteredList<ModelGPU> filterMemoryType(FilteredList<ModelGPU> list, CheckBox cb1, CheckBox cb2, CheckBox cb3) {
        if (cb1.isSelected() == false && cb2.isSelected() == false && cb3.isSelected() == false){}
        else {
            String memoryType = getMemoryType(cb1, cb2, cb3);
            list.setPredicate(gpu -> {
                if (memoryType.matches(gpu.getMemoryType()))
                    return true;
                else
                    return false;
            });
        }
        return list;
    }

    private FilteredList<ModelGPU> searchBarFilterMethod(FilteredList<ModelGPU> list, String searchedValue)
    {
        list.setPredicate(gpu -> {
            String tradeName = gpu.getChipManufacturer() + " " + gpu.getManufacturer() + " " + gpu.getseries() + " " + gpu.getName()
                    + gpu.getManufacturer() + " " + gpu.getName() + " " + gpu.getChipManufacturer() + " " + gpu.getseries();
            if (tradeName.toUpperCase().contains(searchedValue.toUpperCase()))
                return true;
            else
                return false;
        });
        return list;
    }

    private void searchBarFiltering(){
        filteredData = searchBarFilterMethod(filteredData, searchBar.getText());
    }

    private String memoryConnectors (ModelGPU modelItem){
        String connectors = "";
        if (modelItem.getDviConnectors() > 0) {
            if (modelItem.getDviConnectors() == 1)
                connectors += "DVI ";
            else
                connectors += modelItem.getDviConnectors() + "x DVI";
        }
        if (modelItem.getHdmiConnectors() > 0) {
            if (modelItem.getHdmiConnectors() == 1)
                connectors += "HDMI ";
            else
                connectors += modelItem.getHdmiConnectors() + "x HDMI";
        }
        if (modelItem.getDpConnectors() > 0) {
            if (modelItem.getDpConnectors() == 1)
                connectors += "DP ";
            else
                connectors += modelItem.getDpConnectors() + "x DP";
        }
        if (modelItem.getVgaConnectors() > 0) {
            if (modelItem.getVgaConnectors() == 1)
                connectors += "VGA ";
            else
                connectors += modelItem.getVgaConnectors() + "x VGA";
        }
        return connectors;
    }

    @FXML
    private ListView<ModelGPU> gpuListView;
    private ObservableList<ModelGPU> gpuData = FXCollections.observableArrayList();
    private FilteredList<ModelGPU> filteredData = new FilteredList<ModelGPU>(gpuData, p->true);
    private SortedList<ModelGPU> sortedData = new SortedList<ModelGPU>(filteredData);
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    Button filterButton;
    @FXML
    CheckBox selectNvidia;
    @FXML
    CheckBox selectRadeon;
    @FXML
    CheckBox selectMSI;
    @FXML
    CheckBox selectZotac;
    @FXML
    CheckBox selectPalit;
    @FXML
    CheckBox selectGigabyte;
    @FXML
    CheckBox select1GB;
    @FXML
    CheckBox select2GB;
    @FXML
    CheckBox select3GB;
    @FXML
    CheckBox select4GB;
    @FXML
    CheckBox select6GB;
    @FXML
    CheckBox select8GB;
    @FXML
    CheckBox selectGDDR5X;
    @FXML
    CheckBox selectGDDR5;
    @FXML
    CheckBox selectDDR3;
    @FXML
    TextField searchBar;

    @FXML
    private void filterButtonClick (ActionEvent event){ // do zrobienia - filtrowanie dziala jako ostatnia wykonywana funkcja
        // filtrowanie w pewnych momentach musi sie nachodzic tworzac zestaw produktow
        filteredData.setPredicate(gpu -> true);
        filteredData = filterChipManufacturer(filteredData, selectNvidia, selectRadeon);
        filteredData = filterManufacturer(filteredData, selectMSI, selectZotac, selectPalit, selectGigabyte);
        filteredData = filterMemorySizeClass(filteredData, select1GB, select2GB, select3GB, select4GB, select6GB, select8GB);
        filteredData = filterMemoryType(filteredData, selectGDDR5X, selectGDDR5, selectDDR3);
    }

    @FXML
    public void initialize (URL location, ResourceBundle resources) {
        gpuData.clear();
        ModelDataLoaderAndFilter loader = new ModelDataLoaderAndFilter();
        gpuData.addAll(loader.gpuDataLoader());
        searchBar.textProperty().addListener((obs, oldText, newText) -> {
            searchBarFiltering();
        });
        gpuListView.setItems(sortedData);
        gpuListView.setCellFactory(new Callback<ListView<ModelGPU>, ListCell<ModelGPU>>() {

            @Override
            public ListCell<ModelGPU> call(ListView<ModelGPU> param) {
                ListCell<ModelGPU> cell = new ListCell<ModelGPU>(){
                    @Override
                    protected void updateItem(ModelGPU gpuItem, boolean empty){
                        setText(null);
                        setGraphic(null);
                        super.updateItem(gpuItem, empty);
                        if (gpuItem != null) {
                            try {
                                Image img = new Image(gpuItem.getSmallImagePath() + gpuItem.getManufacturerCode() + ".png", true);
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

                            String speed = "";
                            if (gpuItem.getSpeed() == gpuItem.getBoostSpeed())
                                speed = gpuItem.getSpeed() + "MHz";
                            else
                                speed = gpuItem.getSpeed() + "-" + gpuItem.getBoostSpeed() + "MHz";
                            String videoConnectors = memoryConnectors(gpuItem);

                            setText(gpuItem.getManufacturer() + " " + gpuItem.getseries() + " " +  gpuItem.getName() + " " + gpuItem.getmemorySize() + "GB " + gpuItem.getMemoryType()
                                    + "\nVideo connectors: " + videoConnectors
                                    + "\nCore speed: " + speed + ", Memory speed: " + mainApp.noZeros(gpuItem.getMemorySpeed()) + " GHz"
                                    + "\nPrice: " + gpuItem.getPrice() + " PLN");
                        }
                    }
                };
                return cell;
            }
        });
    }
}
