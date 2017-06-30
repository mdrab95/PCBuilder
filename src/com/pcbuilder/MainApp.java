package com.pcbuilder;

import java.io.IOException;
import com.pcbuilder.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane mainView;

    public MainApp(){
        //...
    }

    public static String noZeros(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PC Builder");
        this.primaryStage.resizableProperty().setValue(false);
        initMainView();
        showPCBuilderEz();
    }


    /**
     * Initializes the root layout.
     */
    public void initMainView() {
        try {
            // Load main view from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainView.fxml"));
            mainView = (BorderPane) loader.load();

            // Show the scene containing the main view.
            Scene scene = new Scene(mainView);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            MainViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the pcbuilder-ez mode overview inside the root layout.
     */
    public void showPCBuilderEz() {
        try {
            // Load pcbuilderez overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PCBuilderEz.fxml"));
            AnchorPane pcBuilderEz = (AnchorPane)loader.load();

            // Set pcbuilderez overview into the center of root layout.
            mainView.setCenter(pcBuilderEz);

            // Give the controller access to the main app.
            PCBuilderEzController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the cpu overview inside the root layout.
     */
    public void showCPUOverview() {
        try {
            // Load cpu overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CPUView.fxml"));
            AnchorPane cpuOverview = (AnchorPane)loader.load();

            // Set cpu overview into the center of root layout.
            mainView.setCenter(cpuOverview);

            // Give the controller access to the main app.
            CPUOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the cpu cooler overview inside the root layout.
     */
    public void showCPUCoolerOverview() {
        try {
            // Load cpucooler overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CPUCoolerView.fxml"));
            AnchorPane cpuCoolerOverview = (AnchorPane)loader.load();

            // Set cpucooler overview into the center of root layout.
            mainView.setCenter(cpuCoolerOverview);

            // Give the controller access to the main app.
            CPUCoolerOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the gpu overview inside the root layout.
     */
    public void showGPUOverview() {
        try {
            // Load gpu overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/GPUView.fxml"));
            AnchorPane gpuOverview = (AnchorPane)loader.load();

            // Set gpu overview into the center of root layout.
            mainView.setCenter(gpuOverview);

            // Give the controller access to the main app.
            GPUOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the ram overview inside the root layout.
     */
    public void showRAMOverview() {
        try {
            // Load ram overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RAMView.fxml"));
            AnchorPane ramOverview = (AnchorPane)loader.load();

            // Set ram overview into the center of root layout.
            mainView.setCenter(ramOverview);

            // Give the controller access to the main app.
            RAMOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the hdd overview inside the root layout.
     */
    public void showHDDOverview() {
        try {
            // Load hdd overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/HDDView.fxml"));
            AnchorPane hddOverview = (AnchorPane)loader.load();

            // Set hdd overview into the center of root layout.
            mainView.setCenter(hddOverview);

            // Give the controller access to the main app.
            HDDOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the ssd overview inside the root layout.
     */
    public void showSSDOverview() {
        try {
            // Load ssd overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SSDView.fxml"));
            AnchorPane ssdOverview = (AnchorPane)loader.load();

            // Set ssd overview into the center of root layout.
            mainView.setCenter(ssdOverview);

            // Give the controller access to the main app.
            SSDOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the psu overview inside the root layout.
     */
    public void showPSUOverview() {
        try {
            // Load psu overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PSUView.fxml"));
            AnchorPane psuOverview = (AnchorPane) loader.load();

            // Set psu overview into the center of root layout.
            mainView.setCenter(psuOverview);

            // Give the controller access to the main app.
            PSUOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the mobo overview inside the root layout.
     */
    public void showMoboOverview() {
        try {
            // Load mobo overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MOBOView.fxml"));
            AnchorPane moboOverview = (AnchorPane) loader.load();

            // Set mobo overview into the center of root layout.
            mainView.setCenter(moboOverview);

            // Give the controller access to the main app.
            MOBOOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the case overview inside the root layout.
     */
    public void showCaseOverview() {
        try {
            // Load case overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CaseView.fxml"));
            AnchorPane caseOverview = (AnchorPane)loader.load();

            // Set case overview into the center of root layout.
            mainView.setCenter(caseOverview);

            // Give the controller access to the main app.
            CaseOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Set image size
     * @param imageView imageView
     */
    public void setImgSize(ImageView imageView) {
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
    }

    /**
     * main method
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }
}