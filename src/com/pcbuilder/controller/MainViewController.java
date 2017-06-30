package com.pcbuilder.controller;
import javafx.fxml.FXML;

import com.pcbuilder.MainApp;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;

/**
 * Created by Ace on 2017-05-14.
 */
public class MainViewController {

    // Reference to the main application
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {

        this.mainApp = mainApp;
    }


    @FXML
    private void showPCBuilderEz() {mainApp.showPCBuilderEz();}

    @FXML
    private void showCpu(){ mainApp.showCPUOverview();}

    @FXML
    private void showCpuCooler(){ mainApp.showCPUCoolerOverview();}

    @FXML
    private void showGpu() {
        mainApp.showGPUOverview();
    }

    @FXML
    private void showRam() {
        mainApp.showRAMOverview();
    }

    @FXML
    private void showHdd() {mainApp.showHDDOverview();}

    @FXML
    private void showSsd() {
        mainApp.showSSDOverview();
    }

    @FXML
    private void showPsu() {mainApp.showPSUOverview();}

    @FXML
    private void showCase() {mainApp.showCaseOverview();}

    @FXML
    private void showMobo() {mainApp.showMoboOverview();}

}
