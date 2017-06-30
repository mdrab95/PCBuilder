package com.pcbuilder.controller;

import com.pcbuilder.MainApp;
import com.pcbuilder.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.image.Image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Ace on 19.06.2017.
 */
public class PCBuilderEzController implements Initializable {
    private MainApp mainApp;

    /**
     * Set main app
     * @param mainApp main application
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();

    //region GENERAL
    //--------------------------------------
    @FXML
    private Button startButton;
    @FXML
    private Label maxLoadLabel;
    @FXML
    private Label maxLoadCounter;
    @FXML
    private Label recommendedPsuLabel;
    @FXML
    private Label recommendedPsuCounter;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private Label totalPriceCounter;
    @FXML
    private Button saveBuildButton;
    private int maxLoad = 0;
    private double recommendedPsu = 0;
    private double totalPrice = 0;
    private DecimalFormat df = new DecimalFormat();
    private DecimalFormat df2 = new DecimalFormat("0.00");
    @FXML
    private Label labelSaved;
    private boolean lastAdded = false;
    //--------------------------------------
    //endregion


    //region CPU
    //--------------------------------------

    @FXML
    private ChoiceBox choiceCpu;
    @FXML
    private ImageView cpuImageView;
    @FXML
    private Label cpuDesc;

    @FXML
    private Button addCpu;

    @FXML
    private Button backCpu;

    @FXML
    private Label cpuHeaderLabel;

    private ModelCPU selectedCpu;
    private Image cpuImg;
    //--------------------------------------
    //endregion

    //region CPU Cooler
    //--------------------------------------
    @FXML
    private ChoiceBox choiceCpuCooler;
    @FXML
    private ImageView cpuCoolerImageView;
    @FXML
    private Label cpuCoolerHeaderLabel;
    @FXML
    private Label cpuCoolerDesc;
    @FXML
    private Button addCpuCooler;
    @FXML
    private Button backCpuCooler;

    private ModelCPUCooler selectedCpuCooler;
    private Image cpuCoolerImg;
    //--------------------------------------
    //endregion

    //region Motherboard
    //--------------------------------------
    @FXML
    private ChoiceBox choiceMobo;
    @FXML
    private ImageView moboImageView;
    @FXML
    private Label moboHeaderLabel;
    @FXML
    private Label moboDesc;
    @FXML
    private Button addMobo;
    @FXML
    private Button backMobo;

    private ModelMOBO selectedMobo;
    private Image moboImg;
    //--------------------------------------
    //endregion

    //region GPU
    //--------------------------------------
    @FXML
    private ChoiceBox choiceGpu;
    @FXML
    private ImageView gpuImageView;
    @FXML
    private Label gpuHeaderLabel;
    @FXML
    private Label gpuDesc;
    @FXML
    private Button addGpu;
    @FXML
    private Button backGpu;

    private ModelGPU selectedGpu;
    private Image gpuImg;
    //--------------------------------------
    //endregion

    //region RAM
    //--------------------------------------
    @FXML
    private ChoiceBox choiceRam;
    @FXML
    private ImageView ramImageView;
    @FXML
    private Label ramHeaderLabel;
    @FXML
    private Label ramDesc;
    @FXML
    private Button addRam;
    @FXML
    private Button backRam;

    private ModelRAM selectedRam;
    private Image ramImg;
    //--------------------------------------
    //endregion

    //region SSD
    //--------------------------------------
    @FXML
    private ChoiceBox choiceSsd;
    @FXML
    private ImageView ssdImageView;
    @FXML
    private Label ssdHeaderLabel;
    @FXML
    private Label ssdDesc;
    @FXML
    private Button addSsd;
    @FXML
    private Button backSsd;

    private ModelSSD selectedSsd;
    private Image ssdImg;
    //--------------------------------------
    //endregion

    //region HDD
    //--------------------------------------
    @FXML
    private ChoiceBox choiceHdd;
    @FXML
    private ImageView hddImageView;
    @FXML
    private Label hddHeaderLabel;
    @FXML
    private Label hddDesc;
    @FXML
    private Button addHdd;
    @FXML
    private Button backHdd;

    private ModelHDD selectedHdd;
    private Image hddImg;
    //--------------------------------------
    //endregion

    //region PSU
    //--------------------------------------
    @FXML
    private ChoiceBox choicePsu;
    @FXML
    private ImageView psuImageView;
    @FXML
    private Label psuHeaderLabel;
    @FXML
    private Label psuDesc;
    @FXML
    private Button addPsu;
    @FXML
    private Button backPsu;

    private ModelPSU selectedPsu;
    private Image psuImg;
    //--------------------------------------
    //endregion

    //region Case
    //--------------------------------------
    @FXML
    private ChoiceBox choiceCase;
    @FXML
    private ImageView caseImageView;
    @FXML
    private Label caseHeaderLabel;
    @FXML
    private Label caseDesc;
    @FXML
    private Button addCase;
    @FXML
    private Button backCase;

    private ModelCase selectedCase;
    private Image caseImg;
    //--------------------------------------
    //endregion

    private final ObservableList<ModelCPU> cpuList = FXCollections.observableArrayList();
    private final ObservableList<ModelGPU> gpuList = FXCollections.observableArrayList();
    private final ObservableList<ModelCPUCooler> cpuCoolerList = FXCollections.observableArrayList();
    private final ObservableList<ModelMOBO> moboList = FXCollections.observableArrayList();
    private final ObservableList<ModelSSD> ssdList = FXCollections.observableArrayList();
    private final ObservableList<ModelHDD> hddList = FXCollections.observableArrayList();
    private final ObservableList<ModelRAM> ramList = FXCollections.observableArrayList();
    private final ObservableList<ModelPSU> psuList = FXCollections.observableArrayList();
    private final ObservableList<ModelCase> caseList = FXCollections.observableArrayList();
    ObservableList cpuCoolerNames = FXCollections.observableArrayList();
    ObservableList moboNames = FXCollections.observableArrayList();
    ObservableList gpuNames = FXCollections.observableArrayList();
    ObservableList cpuNames = FXCollections.observableArrayList();
    ObservableList ramNames = FXCollections.observableArrayList();
    ObservableList ssdNames = FXCollections.observableArrayList();
    ObservableList hddNames = FXCollections.observableArrayList();
    ObservableList psuNames = FXCollections.observableArrayList();
    ObservableList caseNames = FXCollections.observableArrayList();

    /**
     * Create info about memory connectors in gpu
     * @param modelItem ModelGPU item
     * @return string - info about memory connectors in gpu
     */
    private String memoryConnectors(ModelGPU modelItem) {
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
    public void initialize(URL location, ResourceBundle resources) {
        df.setMaximumFractionDigits(0);
        //region Data Loader
        // --------------------------------------
        cpuList.addAll(dlaf.cpuDataLoader());
        cpuCoolerList.addAll(dlaf.cpuCoolerDataLoader());
        moboList.addAll(dlaf.moboDataLoader());
        gpuList.addAll(dlaf.gpuDataLoader());
        ramList.addAll(dlaf.ramDataLoader());
        ssdList.addAll(dlaf.ssdDataLoader());
        hddList.addAll(dlaf.hddDataLoader());
        psuList.addAll(dlaf.psuDataLoader());
        caseList.addAll(dlaf.caseDataLoader());



        //--------------------------------------
        //endregion

        //region CPU init
        // --------------------------------------
        for (int i = 0; i < cpuList.size(); i++) {
            String cpuName = cpuList.get(i).getBrand() + " " + cpuList.get(i).getFamily() + " " + cpuList.get(i).getName();
            cpuNames.add(cpuName);
        }
        choiceCpu.setItems(cpuNames);
        choiceCpu.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (cpuNames.size() > 0) {
                            System.out.println(newValue);
                            for (int i = 0; i < cpuList.size(); i++) {
                                String cpuItemName = cpuList.get(i).getBrand() + " " + cpuList.get(i).getFamily() + " " + cpuList.get(i).getName();
                                if (newValue.matches(cpuItemName)) {
                                    selectedCpu = cpuList.get(i);
                                }
                            }
                            cpuImg = new Image(selectedCpu.getSmallImagePath());
                            setImg(cpuImg, cpuImageView);

                            String howItIsPacked = selectedCpu.getPackageType();
                            String spaceInName = " ";
                            if (selectedCpu.getFamily().contains("Core"))
                                spaceInName = "-";
                            if (selectedCpu.getBoxCooler() == false)
                                howItIsPacked += " without cooler";
                            String speed = "";
                            if (selectedCpu.getSpeed() == selectedCpu.getBoostSpeed())
                                speed = selectedCpu.getSpeed() + "GHz";
                            else
                                speed = selectedCpu.getSpeed() + "-" + selectedCpu.getBoostSpeed() + "GHz";
                            String integratedGraphic = "no";
                            if (selectedCpu.getHasIntegratedGraphic() == true)
                                integratedGraphic = "yes";

                            String cpuDescription = (selectedCpu.getBrand() + " " + selectedCpu.getFamily() + spaceInName + selectedCpu.getName() + " (" + howItIsPacked + ")"
                                    + "\n" + selectedCpu.getNumberOfCores() + "C/" + selectedCpu.getNumberOfThreads() + "T, " + speed + ", " + mainApp.noZeros(selectedCpu.getCacheL3()) + " MB" + ", "
                                    + selectedCpu.getTdp() + "W TDP"
                                    + "\nIntegrated graphic card: " + integratedGraphic
                                    + "\nPrice: " + selectedCpu.getPrice() + " PLN");
                            cpuDesc.setText(cpuDescription);
                        }
                    }
                });
        setNoImg(cpuImg, cpuImageView);
        //--------------------------------------
        //endregion

        //region CPUCooler init
        // --------------------------------------
        choiceCpuCooler.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (cpuCoolerNames.size() > 0) {
                            System.out.println(newValue);
                            if (newValue.equals("BOX Cooler")) {
                                if (selectedCpu.getBoxCooler() == false) {
                                    selectedCpuCooler = null;
                                    cpuCoolerImg = new Image("images/error.png");
                                    setImg(cpuCoolerImg, cpuCoolerImageView);
                                    cpuCoolerDesc.setText("There is no box cooler included!");
                                } else {
                                    cpuCoolerImg = new Image(selectedCpu.getSmallImagePath());
                                    setImg(cpuCoolerImg, cpuCoolerImageView);
                                    cpuCoolerDesc.setText("BOX Cooler");
                                    selectedCpuCooler = null;
                                }
                            } else {
                                for (int i = 0; i < cpuCoolerList.size(); i++) {
                                    String cpuCoolerItemName = cpuCoolerList.get(i).getBrand() + " " + cpuCoolerList.get(i).getName();
                                    if (newValue.matches(cpuCoolerItemName)) {
                                        selectedCpuCooler = cpuCoolerList.get(i);
                                    }
                                }
                                cpuCoolerImg = new Image(selectedCpuCooler.getSmallImagePath() + selectedCpuCooler.getManufacturerCode() + ".png", true);
                                setImg(cpuCoolerImg, cpuCoolerImageView);

                                String dimensions = mainApp.noZeros(selectedCpuCooler.getWidth()) + "x" + mainApp.noZeros(selectedCpuCooler.getHeight()) + "x" + mainApp.noZeros(selectedCpuCooler.getDepth()) + "mm";
                                String fans = "";
                                if (selectedCpuCooler.getNumberOfFans() > 1)
                                    fans += selectedCpuCooler.getNumberOfFans() + "x ";
                                fans += mainApp.noZeros(selectedCpuCooler.getFanSizeX()) + "x" + mainApp.noZeros(selectedCpuCooler.getFanSizeY()) + "x" + mainApp.noZeros(selectedCpuCooler.getFanSizeHeight()) + "mm";
                                String noise = "";
                                if (selectedCpuCooler.getMinFanNoise() == selectedCpuCooler.getMaxFanNoise())
                                    noise = selectedCpuCooler.getMaxFanNoise() + "dBA";
                                else
                                    noise = selectedCpuCooler.getMinFanNoise() + "-" + selectedCpuCooler.getMaxFanNoise() + "dBA";
                                String fanSpeed = "";
                                if (selectedCpuCooler.getMinFanSpeed() == selectedCpuCooler.getMaxFanSpeed())
                                    fanSpeed = selectedCpuCooler.getMaxFanSpeed() + "rpm";
                                else
                                    fanSpeed = selectedCpuCooler.getMinFanSpeed() + "-" + selectedCpuCooler.getMaxFanSpeed() + "rpm";
                                String cpuCoolerDescription = selectedCpuCooler.getBrand() + " " + selectedCpuCooler.getName() + " (" + selectedCpuCooler.getManufacturerCode() + ")"
                                        + "\nDesign: " + selectedCpuCooler.getDesign() + ", Dimensions (WxHxD): " + dimensions + ", Fan(s): " + fans
                                        + "\nWeight: " + selectedCpuCooler.getWeight() + "g, " + fanSpeed + ", " + selectedCpuCooler.getAirFlow() + "m³/h, " + noise + ", Connector: " + selectedCpuCooler.getConnectorType()
                                        + "\nPrice: " + selectedCpuCooler.getPrice() + " PLN";
                                cpuCoolerDesc.setText(cpuCoolerDescription);
                            }
                        }
                    }
                });
        setNoImg(cpuCoolerImg, cpuCoolerImageView);
        //--------------------------------------
        //endregion

        //region MOBO init
        // --------------------------------------

        choiceMobo.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (moboNames.size() > 0) {
                            System.out.println(newValue);
                            for (int i = 0; i < moboList.size(); i++) {
                                String selectedMoboName = moboList.get(i).getBrand() + " " + moboList.get(i).getChipset() + " " + moboList.get(i).getName();
                                if (newValue.matches(selectedMoboName)) {
                                    selectedMobo = moboList.get(i);
                                }
                            }
                            try {
                                moboImg = new Image(selectedMobo.getSmallImagePath() + selectedMobo.getSerialNumber() + ".png", true);
                                setImg(moboImg, moboImageView);
                            } catch (Exception ex) {
                                System.out.println("Cant load mobo img!");
                            }

                            String moboDescription = selectedMobo.getBrand() + " " + selectedMobo.getChipset() + " " + selectedMobo.getName()
                                    + "\n" + "Socket: " + selectedMobo.getSocket() + ", form factor: " + selectedMobo.getFormFactor()
                                    + "\nRAM standard: " + selectedMobo.getRamStandard() + ", Max RAM speed: " + selectedMobo.getMaxRamSpeed() + " MHz"
                                    + "\nMax RAM capacity: " + selectedMobo.getMaxRam() + "GB, in " + selectedMobo.getRamSlots() + " slots."
                                    + "\nPrice: " + selectedMobo.getPrice() + " PLN";
                            moboDesc.setText(moboDescription);
                        }
                    }
                });

        setNoImg(moboImg, moboImageView);
        //--------------------------------------
        //endregion

        //region GPU init
        // --------------------------------------

        choiceGpu.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (gpuNames.size() > 0) {
                            System.out.println(newValue);
                            if (newValue.equals("CPU Integrated graphic card")) {
                                if (selectedCpu.getHasIntegratedGraphic() == false) {
                                    selectedGpu = null;
                                    gpuImg = new Image("images/error.png");
                                    setImg(gpuImg, gpuImageView);
                                    gpuDesc.setText("There is no integrated graphic card!");
                                } else {
                                    gpuImg = new Image(selectedCpu.getSmallImagePath());
                                    setImg(gpuImg, gpuImageView);
                                    gpuDesc.setText("Integrated graphic card");
                                    selectedGpu = null;
                                }
                            } else {
                                for (int i = 0; i < gpuList.size(); i++) {
                                    String selectedGpuName = gpuList.get(i).getChipManufacturer() + " " + gpuList.get(i).getseries() + " " + gpuList.get(i).getName();
                                    if (newValue.matches(selectedGpuName)) {
                                        selectedGpu = gpuList.get(i);
                                    }
                                }
                                try {
                                    gpuImg = new Image(selectedGpu.getSmallImagePath() + selectedGpu.getManufacturerCode() + ".png", true);
                                    setImg(gpuImg, gpuImageView);
                                } catch (Exception ex) {
                                    System.out.println("Can't load GPU img!");
                                }

                                String speed = "";
                                if (selectedGpu.getSpeed() == selectedGpu.getBoostSpeed())
                                    speed = selectedGpu.getSpeed() + "MHz";
                                else
                                    speed = selectedGpu.getSpeed() + "-" + selectedGpu.getBoostSpeed() + "MHz";
                                String videoConnectors = memoryConnectors(selectedGpu);

                                String gpuDescription = selectedGpu.getManufacturer() + " " + selectedGpu.getseries() + " " + selectedGpu.getName() + " " + selectedGpu.getmemorySize() + "GB " + selectedGpu.getMemoryType()
                                        + "\nVideo connectors: " + videoConnectors
                                        + "\nCore speed: " + speed + ", Memory speed: " + mainApp.noZeros(selectedGpu.getMemorySpeed()) + " GHz"
                                        + "\nPrice: " + selectedGpu.getPrice() + " PLN";
                                gpuDesc.setText(gpuDescription);
                            }
                        }
                    }
                });

        setNoImg(gpuImg, gpuImageView);
        //--------------------------------------
        //endregion

        //region RAM init
        // --------------------------------------

        choiceRam.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (ramNames.size() > 0) {
                            System.out.println(newValue);
                            for (int i = 0; i < ramList.size(); i++) {
                                String selectedRamName = ramList.get(i).getBrand() + " " + ramList.get(i).getName() + " " + ramList.get(i).getMemorySize() + "GB (" + ramList.get(i).getNumberOfModules() + "x" + ramList.get(i).getSingleModuleSize() + "GB) " + ramList.get(i).getStandard() + " " + ramList.get(i).getMemoryClock() + "MHz (" + ramList.get(i).getSerialNumber() + ")";
                                if (newValue.equals(selectedRamName)) {
                                    selectedRam = ramList.get(i);
                                }
                            }
                            try {
                                String modifiedSerial = selectedRam.getSerialNumber().replaceAll("/", "-");
                                ramImg = new Image(selectedRam.getSmallImagePath() + modifiedSerial + ".png", true);
                                setImg(ramImg, ramImageView);
                            } catch (Exception ex) {
                                System.out.println("Can't load RAM img!");
                            }


                            String ramDescription = selectedRam.getBrand() + " " + selectedRam.getName() + " " + selectedRam.getStandard() + " " + selectedRam.getMemorySize() + "GB " + selectedRam.getMemoryClock() + "MHz"
                                    + "\nNumber of modules: " + selectedRam.getNumberOfModules() + ", module size: " + selectedRam.getSingleModuleSize() + "GB"
                                    + "\nCAS Latency: " + selectedRam.getCasLatency()
                                    + "\nPrice: " + selectedRam.getPrice() + " PLN";
                            ramDesc.setText(ramDescription);
                        }
                    }
                });

        setNoImg(ramImg, ramImageView);
        //--------------------------------------
        //endregion

        //region SSD init
        // --------------------------------------

        choiceSsd.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (ssdNames.size() > 0) {
                            System.out.println(newValue);
                            if (newValue.equals("Select nothing")) {
                                setNoImg(ssdImg, ssdImageView);
                                ssdDesc.setText("No SSD selected.");
                                selectedSsd = null;
                            } else {
                                    for (int i = 0; i < ssdList.size(); i++) {
                                        String selectedSsdName = ssdList.get(i).getBrand() + " " + ssdList.get(i).getName() + " " + ssdList.get(i).getCapacity() + "GB (" + ssdList.get(i).getSerialNumber() + ")";
                                        if (newValue.equals(selectedSsdName)) {
                                            selectedSsd = ssdList.get(i);
                                        }
                                    }
                                    try {
                                        ssdImg = new Image(selectedSsd.getSmallImagePath() + selectedSsd.getSerialNumber() + ".png", true);
                                        setImg(ssdImg, ssdImageView);
                                    } catch (Exception ex) {
                                        System.out.println("Can't load SSD img!");
                                    }


                                    String ssdDescription = selectedSsd.getBrand() + " " + selectedSsd.getName() + " " + selectedSsd.getCapacity() + " GB"
                                            + "\n" + "Form factor: " + selectedSsd.getFormFactor() + ", interface type: " + selectedSsd.getInterfaceType() + ", memory type: " + selectedSsd.getMemoryType()
                                            + "\nRead: " + selectedSsd.getReadSpeed() + "MB/s, write: " + selectedSsd.getWriteSpeed() + "MB/s " + ", TBW: " + selectedSsd.getTbw() + "TB"
                                            + "\nPrice: " + selectedSsd.getPrice() + " PLN";
                                    ssdDesc.setText(ssdDescription);
                                }
                            }
                    }
                });

        setNoImg(ssdImg, ssdImageView);
        //--------------------------------------
        //endregion

        //region HDD init
        // --------------------------------------

        choiceHdd.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (hddNames.size() > 0) {
                            System.out.println(newValue);
                            if (newValue.equals("Select nothing")) {
                                setNoImg(hddImg, hddImageView);
                                hddDesc.setText("No HDD selected.");
                                selectedHdd = null;
                            } else {
                                for (int i = 0; i < hddList.size(); i++) {
                                    String capacity = "";
                                    if (hddList.get(i).getCapacity() >= 1000)
                                        capacity = String.valueOf(hddList.get(i).getCapacity() / 1000) + "TB";
                                    else
                                        capacity = hddList.get(i).getCapacity() + "GB";
                                    String selectedHddName = hddList.get(i).getBrand() + " " + hddList.get(i).getName() + " " + capacity;
                                    if (newValue.matches(selectedHddName)) {
                                        selectedHdd = hddList.get(i);
                                    }
                                }
                                try {
                                    hddImg = new Image(selectedHdd.getSmallImagePath() + selectedHdd.getSerialNumber() + ".png", true);
                                    setImg(hddImg, hddImageView);
                                } catch (Exception ex) {
                                    System.out.println("Can't load HDD img!");
                                }

                                String capacity = "";
                                if (selectedHdd.getCapacity() >= 1000)
                                    capacity = String.valueOf(selectedHdd.getCapacity() / 1000) + "TB";
                                else
                                    capacity = selectedHdd.getCapacity() + "GB";

                                String hddDescription = selectedHdd.getBrand() + " " + selectedHdd.getName() + " " + capacity
                                        + "\nInterface type: " + selectedHdd.getHddInterfaceType() + ", form factor: " + selectedHdd.getFormFactor()
                                        + "\nRotational speed: " + selectedHdd.getRotationalSpeed() + "rpm, cache size: " + selectedHdd.getCacheSize() + "MB"
                                        + "\nPrice: " + selectedHdd.getPrice() + "PLN";
                                hddDesc.setText(hddDescription);
                            }
                        }
                    }
                });

        setNoImg(hddImg, hddImageView);
        //--------------------------------------
        //endregion

        //region PSU init
        // --------------------------------------

        choicePsu.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (psuNames.size() > 0) {
                            System.out.println(newValue);
                            for (int i = 0; i < psuList.size(); i++) {
                                String selectedPsuName = psuList.get(i).getBrand() + " " + psuList.get(i).getName() + " " + psuList.get(i).getWattage() + "W";
                                if (newValue.matches(selectedPsuName)) {
                                    selectedPsu = psuList.get(i);
                                }
                            }
                            try {
                                psuImg = new Image(selectedPsu.getSmallImagePath() + selectedPsu.getManufacturerCode() + ".png", true);
                                setImg(psuImg, psuImageView);
                            } catch (Exception ex) {
                                System.out.println("Can't load PSU img!");
                            }

                            String modular = "";
                            if (selectedPsu.getIsModular() == true)
                                modular = "Modular";

                            String certificate = "";
                            if (selectedPsu.getCertificate80Plus().length() == 0)
                                certificate = "no data";
                            else
                                certificate = selectedPsu.getCertificate80Plus();

                            String psuDescription = selectedPsu.getBrand() + " " + selectedPsu.getName() + " " + selectedPsu.getWattage() + "W " + modular
                                    + "\nCertificate: " + certificate + ", Protection:  " + selectedPsu.getProtection()
                                    + "\nPrice: " + selectedPsu.getPrice() + " PLN";
                            psuDesc.setText(psuDescription);
                        }
                    }
                });

        setNoImg(psuImg, psuImageView);
        //--------------------------------------
        //endregion

        //region Case init
        // --------------------------------------

        choiceCase.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (caseNames.size() > 0) {
                            System.out.println(newValue);
                            for (int i = 0; i < caseList.size(); i++) {
                                String selectedCaseName = caseList.get(i).getBrand() + " " + caseList.get(i).getName();
                                if (newValue.matches(selectedCaseName)) {
                                    selectedCase = caseList.get(i);
                                }
                            }
                            try {
                                caseImg = new Image(selectedCase.getSmallImagePath() + selectedCase.getSerialNumber() + ".png", true);
                                setImg(caseImg, caseImageView);
                            } catch (Exception ex) {
                                System.out.println("Can't load Case img!");
                            }


                            String caseDescription = selectedCase.getBrand() + " " + selectedCase.getName()
                                    + "\nCompatibility: " + selectedCase.getFormFactor()
                                    + "\nType: " + selectedCase.getType()
                                    + "\nInstalled fans - front: " + selectedCase.getFrontFanNumber() + "x" + selectedCase.getFrontFanSize()
                                    + "mm, top: " + selectedCase.getTopFanNumber() + "x" + selectedCase.getTopFanSize() + "mm, rear: " + selectedCase.getRearFanNumber() + "x"
                                    + selectedCase.getRearFanSize() + "mm"
                                    + "\nMax fan number - front: " + selectedCase.getMaxFrontFanNumber() + "x" + selectedCase.getFrontFanSize()
                                    + "mm, top: " + selectedCase.getMaxTopFanNumber() + "x" + selectedCase.getTopFanSize() + "mm, rear: " + selectedCase.getMaxRearFanNumber() + "x"
                                    + selectedCase.getRearFanSize() + "mm"
                                    + "\nPrice: " + selectedCase.getPrice() + " PLN";
                            caseDesc.setText(caseDescription);
                        }
                    }
                });

        setNoImg(caseImg, caseImageView);
        //--------------------------------------
        //endregion


    }

    /**
     * Set image 'no_img.png'
     * @param image image
     * @param imageView image view
     */
    private void setNoImg(Image image, ImageView imageView) {
        image = new Image("images/no_img.png");
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setImage(image);
    }

    /**
     * Set image
     * @param image image
     * @param imageView image view
     */
    private void setImg(Image image, ImageView imageView) {
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setImage(image);
    }

    /**
     * Start button action
     */
    @FXML
    private void startButtonAcction() {
        System.out.println("start button clicked");
        addCpu.getStyleClass().add("pcbuilder-enabled");
        cpuDesc.getStyleClass().add("pcbuilder-enabled");
        cpuHeaderLabel.getStyleClass().add("pcbuilder-enabled");
        cpuImageView.getStyleClass().add("pcbuilder-enabled");
        choiceCpu.getStyleClass().add("pcbuilder-enabled");
        startButton.getStyleClass().add("pcbuilder-disabled");
        maxLoadLabel.getStyleClass().add("pcbuilder-enabled");
        maxLoadCounter.getStyleClass().add("pcbuilder-enabled");
        recommendedPsuLabel.getStyleClass().add("pcbuilder-enabled");
        recommendedPsuCounter.getStyleClass().add("pcbuilder-enabled");
        totalPriceLabel.getStyleClass().add("pcbuilder-enabled");
        totalPriceCounter.getStyleClass().add("pcbuilder-enabled");
    }

    /**
     * Last add button action
     */
    @FXML
    private void lastButtonAction() {
        if (selectedCase != null) {
            choiceCase.getStyleClass().remove("pcbuilder-enabled");
            addCase.getStyleClass().remove("pcbuilder-enabled");
            caseHeaderLabel.setText("Your Case:");
            labelsAddValues(0, selectedCase.getPrice());
            saveBuildButton.getStyleClass().add("pcbuilder-enabled");
            lastAdded = true;
        }
    }

    /**
     * Back cpu button action.
     * @param buttonName button name which will be displayed on the console
     * @param currentAdd current item add button
     * @param currentBack current item back button
     * @param currentDesc current item description
     * @param currentHeader current item header
     * @param currentImageView current item image view
     * @param currentImage current item image
     * @param currentChoice current item choice box
     * @param previousAdd previous item add button
     * @param previousHeader previous item header
     * @param previousChoice previous item choice box
     * @param currentNames current item choice box content
     * @param selectWhat select /what/, ex. "CPU"
     */
    private void backButtonCpu(String buttonName, Button currentAdd, Button currentBack, Label currentDesc, Label currentHeader,
                               ImageView currentImageView, Image currentImage, ChoiceBox currentChoice, Button previousAdd,
                               Label previousHeader, ChoiceBox previousChoice, ObservableList currentNames, String selectWhat) {
        System.out.println(buttonName + " clicked");
        currentAdd.getStyleClass().remove("pcbuilder-enabled");
        currentBack.getStyleClass().remove("pcbuilder-enabled");
        currentDesc.getStyleClass().remove("pcbuilder-enabled");
        currentHeader.getStyleClass().remove("pcbuilder-enabled");
        currentImageView.getStyleClass().remove("pcbuilder-enabled");
        currentChoice.getStyleClass().remove("pcbuilder-enabled");

        previousAdd.getStyleClass().add("pcbuilder-enabled");
        previousHeader.setText("Add " + selectWhat + ":");
        previousChoice.getStyleClass().add("pcbuilder-enabled");


        currentDesc.setText(null);
        currentImage = new Image("images/no_img.png");
        currentImageView.setImage(currentImage);
    }

    /**
     * Universal back button action.
     * @param buttonName button name which will be displayed on the console
     * @param currentAdd current item add button
     * @param currentBack current item back button
     * @param currentDesc current item description
     * @param currentHeader current item header
     * @param currentImageView current item image view
     * @param currentImage current item image
     * @param currentChoice current item choice box
     * @param previousAdd previous item add button
     * @param previousBack previous item back button
     * @param previousHeader previous item header
     * @param previousChoice previous item choice box
     * @param currentNames current item choice box content
     * @param selectWhat select /what/, ex. "CPU"
     */
    private void backButtonUniversal(String buttonName, Button currentAdd, Button currentBack, Label currentDesc, Label currentHeader,
                                     ImageView currentImageView, Image currentImage, ChoiceBox currentChoice, Button previousAdd, Button previousBack,
                                     Label previousHeader, ChoiceBox previousChoice, ObservableList currentNames, String selectWhat) {

        backButtonCpu(buttonName, currentAdd, currentBack, currentDesc, currentHeader, currentImageView, currentImage, currentChoice,
                previousAdd, previousHeader, previousChoice, currentNames, selectWhat);

        previousBack.getStyleClass().add("pcbuilder-enabled");
    }

    /**
     * Add cpu button action.
     * @param buttonName button name which will be displayed on the console
     * @param nextAdd next item add button
     * @param nextBack next item back button
     * @param nextDesc next item description
     * @param nextHeader next item header
     * @param nextImageView next item image view
     * @param nextChoice next item choice box
     * @param currentAdd current item add button
     * @param currentChoice current item choice box
     * @param currentHeader current item header
     * @param currentLabel current item label
     * @param nextNames next item choice box content
     * @param nextImage next item image
     */
    private void addButtonCpu(String buttonName, Button nextAdd, Button nextBack, Label nextDesc, Label nextHeader, ImageView nextImageView,
                              ChoiceBox nextChoice, Button currentAdd, ChoiceBox currentChoice, Label currentHeader, String currentLabel,
                              ObservableList nextNames, Image nextImage) {
        System.out.println(buttonName + " clicked");
        nextAdd.getStyleClass().add("pcbuilder-enabled");
        nextBack.getStyleClass().add("pcbuilder-enabled");
        nextDesc.getStyleClass().add("pcbuilder-enabled");
        nextHeader.getStyleClass().add("pcbuilder-enabled");
        nextImageView.getStyleClass().add("pcbuilder-enabled");
        nextChoice.getStyleClass().add("pcbuilder-enabled");

        currentAdd.getStyleClass().remove("pcbuilder-enabled");
        currentChoice.getStyleClass().remove("pcbuilder-enabled");
        currentHeader.setText(currentLabel);

        nextDesc.setText(null);
        nextImage = new Image("images/no_img.png");
        nextImageView.setImage(nextImage);

    }

    /**
     * Universal add button action.
     * @param buttonName button name which will be displayed on the console
     * @param nextAdd next item add button
     * @param nextBack next item back button
     * @param nextDesc next item description
     * @param nextHeader next item header
     * @param nextImageView next item image view
     * @param nextChoice next item choice box
     * @param nextNames next item choice box content
     * @param currentAdd current item add button
     * @param currentBack current item back button
     * @param currentChoice current item choice box
     * @param currentHeader current item header
     * @param currentLabel current item label
     */
    private void addButtonUniversal(String buttonName, Button nextAdd, Button nextBack, Label nextDesc, Label nextHeader, ImageView nextImageView, Image nextImage,
                                    ChoiceBox nextChoice, ObservableList nextNames, Button currentAdd, Button currentBack, ChoiceBox currentChoice, Label currentHeader, String currentLabel) {
        addButtonCpu(buttonName, nextAdd, nextBack, nextDesc, nextHeader, nextImageView, nextChoice, currentAdd, currentChoice, currentHeader, currentLabel, nextNames, nextImage);
        currentBack.getStyleClass().remove("pcbuilder-enabled");
    }

    /**
     * Add cpu button action
     */
    @FXML
    private void addCpuButtonAction() {
        if (selectedCpu != null) {
            addButtonCpu("addCpuButton", addCpuCooler, backCpuCooler, cpuCoolerDesc, cpuCoolerHeaderLabel, cpuCoolerImageView, choiceCpuCooler, addCpu, choiceCpu,
                    cpuHeaderLabel, "Your CPU:", cpuCoolerNames, cpuCoolerImg);
            cpuCoolerNames.setAll(dlaf.getCpuCoolerNames(selectedCpu, cpuCoolerList));
            choiceCpuCooler.setItems(cpuCoolerNames);
            labelsAddValues(selectedCpu.getWattage(), selectedCpu.getPrice());
        }
    }

    /**
     * Back cpu cooler button action
     */
    @FXML
    private void backCpuCoolerButtonAction() {
        backButtonCpu("backCpuCoolerButtonAction", addCpuCooler, backCpuCooler, cpuCoolerDesc, cpuCoolerHeaderLabel,
                cpuCoolerImageView, cpuCoolerImg, choiceCpuCooler, addCpu, cpuHeaderLabel, choiceCpu, cpuNames, "CPU");
        selectedCpuCooler = null;
        dlaf.clearCpuCoolerNames();
        if (selectedCpu != null) {
            labelsSubValues(selectedCpu.getWattage(), selectedCpu.getPrice());
        }
    }

    /**
     * Add cpu cooler button action
     */
    @FXML
    private void addCpuCoolerButtonAction() {
        if (selectedCpu.getBoxCooler() == false && selectedCpuCooler == null) {
            cpuCoolerDesc.setText("There is no box cooler included!");
            Image boxCoolerImg = new Image("images/error.png");
            cpuCoolerImageView.setImage(boxCoolerImg);
        }
        if (selectedCpu.getBoxCooler() == true && selectedCpuCooler == null) {
            addButtonUniversal("addCpuCoolerButtonAction", addMobo, backMobo, moboDesc, moboHeaderLabel, moboImageView, moboImg,
                    choiceMobo, moboNames, addCpuCooler, backCpuCooler, choiceCpuCooler, cpuCoolerHeaderLabel, "Your CPU Cooler:");
            cpuCoolerImg = new Image(selectedCpu.getSmallImagePath());
            setImg(cpuCoolerImg, cpuCoolerImageView);
            cpuCoolerDesc.setText("BOX Cooler");
        }
        if (selectedCpu.getBoxCooler() == false && selectedCpuCooler != null || selectedCpu.getBoxCooler() == true && selectedCpuCooler != null) {
            addButtonUniversal("addCpuCoolerButtonAction", addMobo, backMobo, moboDesc, moboHeaderLabel, moboImageView, moboImg,
                    choiceMobo, moboNames, addCpuCooler, backCpuCooler, choiceCpuCooler, cpuCoolerHeaderLabel, "Your CPU Cooler:");
            labelsAddValues(selectedCpuCooler.getWattage(), selectedCpuCooler.getPrice());
        }
        if (selectedCpu.getBoxCooler() == false && selectedCpuCooler != null || selectedCpu.getBoxCooler() == true && selectedCpuCooler != null || selectedCpu.getBoxCooler() == true && selectedCpuCooler == null) {
            moboNames = dlaf.getMoboNames(selectedCpu, selectedCpuCooler, moboList);
            choiceMobo.setItems(moboNames);
        }
    }

    /**
     * Back mobo button action
     */
    @FXML
    private void backMoboButtonAction() {
        backButtonUniversal("backMoboButtonAction", addMobo, backMobo, moboDesc, moboHeaderLabel, moboImageView, moboImg, choiceMobo,
                addCpuCooler, backCpuCooler, cpuCoolerHeaderLabel, choiceCpuCooler, moboNames, "Cpu cooler");
        selectedMobo = null;
        dlaf.clearMoboNames();
        if (selectedCpuCooler != null) {
            labelsSubValues(selectedCpuCooler.getWattage(), selectedCpuCooler.getPrice());
        }
    }

    /**
     * Add mobo button action
     */
    @FXML
    private void addMoboButtonAction() {
        if (selectedMobo != null) {
            addButtonUniversal("addMoboButtonAction", addGpu, backGpu, gpuDesc, gpuHeaderLabel, gpuImageView, gpuImg, choiceGpu, gpuNames,
                    addMobo, backMobo, choiceMobo, moboHeaderLabel, "Your Motherboard: ");
            labelsAddValues(selectedMobo.getWattage(), selectedMobo.getPrice());
           gpuNames = dlaf.getGpuNames(selectedCpu, gpuList);
           choiceGpu.setItems(gpuNames);
        }
    }

    /**
     * Back gpu button action
     */
    @FXML
    private void backGpuButtonAction() {
        backButtonUniversal("backGpuButtonAction", addGpu, backGpu, gpuDesc, gpuHeaderLabel, gpuImageView, gpuImg, choiceGpu,
                addMobo, backMobo, moboHeaderLabel, choiceMobo, moboNames, "Motherboard");
        selectedGpu = null;
        dlaf.clearGpuNames();
        if (selectedMobo != null) {
            labelsSubValues(selectedMobo.getWattage(), selectedMobo.getPrice());
        }
    }

    /**
     * Add gpu button action
     */
    @FXML
    private void addGpuButtonAction() {
        boolean canGo = false;
        if (selectedCpu.getHasIntegratedGraphic() == true && selectedGpu == null) {
            gpuImg = new Image(selectedCpu.getSmallImagePath());
            setImg(gpuImg, gpuImageView);
            gpuDesc.setText("Integrated graphic card");
            canGo = true;
        }
        if (selectedGpu != null) {
            labelsAddValues(selectedGpu.getWattage(), selectedGpu.getPrice());
            canGo = true;
        }
        if (canGo == true) {
            addButtonUniversal("addGpuButtonName", addRam, backRam, ramDesc, ramHeaderLabel, ramImageView, ramImg, choiceRam, ramNames,
                    addGpu, backGpu, choiceGpu, gpuHeaderLabel, "Your Graphic card:");
            ramNames.clear();
            ramNames.addAll(dlaf.getRamNames(selectedMobo, ramList));
            choiceRam.setItems(ramNames);
        }
    }

    /**
     * Back ram button action
     */
    @FXML
    private void backRamButtonAction() {
        backButtonUniversal("backRamButtonAction", addRam, backRam, ramDesc, ramHeaderLabel, ramImageView, ramImg, choiceRam,
                addGpu, backGpu, gpuHeaderLabel, choiceGpu, gpuNames, "GPU");
        selectedRam = null;
        dlaf.clearRamNames();
        if (selectedGpu != null) {
            labelsSubValues(selectedGpu.getWattage(), selectedGpu.getPrice());
        }
    }

    /**
     * Add ram button action
     */
    @FXML
    private void addRamButtonAction() {
        if (selectedRam != null) {
            addButtonUniversal("addRamButtonName", addSsd, backSsd, ssdDesc, ssdHeaderLabel, ssdImageView, ssdImg, choiceSsd, ssdNames,
                    addRam, backRam, choiceRam, ramHeaderLabel, "Your RAM:");
            labelsAddValues(selectedRam.getWattage(), selectedRam.getPrice());
            ssdNames.clear();
            ssdNames.addAll(dlaf.getSsdNames(selectedMobo, ssdList));
            choiceSsd.setItems(ssdNames);
        }
    }

    /**
     * Back ssd button action
     */
    @FXML
    private void backSsdButtonAction() {
        backButtonUniversal("backSsdButtonAction", addSsd, backSsd, ssdDesc, ssdHeaderLabel, ssdImageView, ssdImg, choiceSsd,
                addRam, backRam, ramHeaderLabel, choiceRam, ramNames, "RAM");
        selectedSsd = null;
        dlaf.clearSsdNames();
        if (selectedRam != null) {
            labelsSubValues(selectedRam.getWattage(), selectedRam.getPrice());
        }
    }

    /**
     * Add ssd button action
     */
    @FXML
    private void addSsdButtonAction() {
        Boolean canGo = false;
        if (selectedSsd != null) {
            labelsAddValues(selectedSsd.getWattage(), selectedSsd.getPrice());
            canGo = true;
        }
        else {
            setNoImg(ssdImg, ssdImageView);
            ssdDesc.setText("No SSD selected");
            selectedSsd = null;
            canGo = true;
            hddNames.clear();
        }
        if (canGo == true) {
            addButtonUniversal("addSsdButtonName", addHdd, backHdd, hddDesc, hddHeaderLabel, hddImageView, hddImg, choiceHdd, hddNames,
                    addSsd, backSsd, choiceSsd, ssdHeaderLabel, "Your SSD:");
            hddNames.clear();
            hddNames.addAll(dlaf.getHddNames(selectedSsd, hddList));
            choiceHdd.setItems(hddNames);
        }
    }

    /**
     * Back hdd button action
     */
    @FXML
    private void backHddButtonAction() {
        backButtonUniversal("backHddButtonAction", addHdd, backHdd, hddDesc, hddHeaderLabel, hddImageView, hddImg, choiceHdd,
                addSsd, backSsd, ssdHeaderLabel, choiceSsd, ssdNames, "SSD");
        selectedHdd = null;
        dlaf.clearHddNames();
        if (selectedSsd != null) {
            labelsSubValues(selectedSsd.getWattage(), selectedSsd.getPrice());
        }
    }

    /**
     * Add hdd button action
     */
    @FXML
    private void addHddButtonAction() {
        boolean canGo = false;
        if (selectedHdd != null) {
            labelsAddValues(selectedHdd.getWattage(), selectedHdd.getPrice());
            canGo = true;
        }
        else {
            setNoImg(hddImg, hddImageView);
            hddDesc.setText("No HDD selected");
            selectedHdd = null;
            if (selectedSsd == null)
                canGo = false;
            else {
                hddNames.clear();
                canGo = true;
            }
        }
        if (canGo == true) {
            addButtonUniversal("addHddButtonName", addPsu, backPsu, psuDesc, psuHeaderLabel, psuImageView, psuImg, choicePsu, psuNames,
                    addHdd, backHdd, choiceHdd, hddHeaderLabel, "Your HDD:");
            psuNames.clear();
            psuNames.addAll(dlaf.getPsuNames(maxLoad, psuList));
            choicePsu.setItems(psuNames);
        }
    }

    /**
     * Back psu button action
     */
    @FXML
    private void backPsuButtonAction() {
        backButtonUniversal("backPsuButtonAction", addPsu, backPsu, psuDesc, psuHeaderLabel, psuImageView, psuImg, choicePsu,
                addHdd, backHdd, hddHeaderLabel, choiceHdd, hddNames, "HDD");
        selectedPsu = null;
        dlaf.clearPsuNames();
        if (selectedHdd != null) {
            labelsSubValues(selectedHdd.getWattage(), selectedHdd.getPrice());
        }
    }

    /**
     * Add psu button action
     */
    @FXML
    private void addPsuButtonAction() {
        if (selectedPsu != null) {
            addButtonUniversal("addHddButtonName", addCase, backCase, caseDesc, caseHeaderLabel, caseImageView, caseImg, choiceCase, caseNames,
                    addPsu, backPsu, choicePsu, psuHeaderLabel, "Your PSU:");
            totalPrice += selectedPsu.getPrice();
            totalPriceCounter.setText(String.valueOf(df2.format(totalPrice)) + "PLN");
            caseNames.clear();
            caseNames.addAll(dlaf.getCaseNames(selectedMobo, caseList));
            choiceCase.setItems(caseNames);
        }
    }

    /**
     * Back case button action
     */
    @FXML
    private void backCaseButtonAction() {
        backButtonUniversal("backHddButtonAction", addCase, backCase, caseDesc, caseHeaderLabel, caseImageView, caseImg, choiceCase,
                addPsu, backPsu, psuHeaderLabel, choicePsu, psuNames, "PSU");
        if (selectedPsu != null) {
            totalPrice -= selectedPsu.getPrice();
            totalPriceCounter.setText(String.valueOf(df2.format(totalPrice)) + "PLN");
            saveBuildButton.getStyleClass().add("pcbuilder-enabled");
        }
        if (selectedCase != null) {
            if (lastAdded == true) {
                totalPrice -= selectedCase.getPrice();
                lastAdded = false;
            }
            totalPriceCounter.setText(String.valueOf(df2.format(totalPrice)) + "PLN");
            saveBuildButton.getStyleClass().add("pcbuilder-enabled");
            selectedCase = null;
        }
        labelSaved.getStyleClass().remove("pcbuilder-enabled");
    }

    /**
     * Save build button action
     */
    @FXML
    private void saveBuildButtonAction() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hhmmss");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        String savedString = createTextToSave(currentDate);
        String filePath = "C:/PCBuilder";
        String fileName = currentDate;
        String fileNameAndPath = "C:/PCBuilder/PCBuilder " + fileName + ".txt";
        saveTextToFile(fileNameAndPath, filePath, savedString);
    }

    /**
     * This method creates pcbuilder report string.
     * @param currentDate current date string
     * @return string to save
     */
    private String createTextToSave(String currentDate){
        String savedString = "This file has been generated on " + currentDate
                + "\r\n----------------------------------------------------------------------------"
                + "\r\n" + padRight("Your build", 13) + " " + padLeft("Price",62)
                + "\r\n----------------------------------------------------------------------------";
        savedString += "\r\n" + padRight("CPU:", 13) + padRight(selectedCpu.getBrand() + " " + selectedCpu.getFamily()
                + " " + selectedCpu.getName(), 50) + " " + padRight(String.valueOf(selectedCpu.getPrice()), 8) + " PLN";
        if (selectedCpuCooler != null) {
            savedString += "\r\n" + padRight("CPU Cooler:", 13) + padRight(selectedCpuCooler.getBrand() + " "
                    + selectedCpuCooler.getName(), 50) + " " + padRight(String.valueOf(selectedCpuCooler.getPrice()),8) + " PLN";
        }
        else {
            savedString += "\r\n" + padRight("CPU Cooler:", 13)  + padRight("BOX Cooler", 51) + padRight("0",8) +" PLN";
        }
        savedString += "\r\n"+ padRight("Motherboard:", 13) + padRight(selectedMobo.getBrand() + " "
                + selectedMobo.getName(),50) + " " + padRight(String.valueOf(selectedMobo.getPrice()),8) + " PLN";
        if (selectedGpu != null) {
            savedString += "\r\n"+ padRight("GPU:", 13)+  padRight(selectedGpu.getChipManufacturer() + " "
                    + selectedGpu.getseries() + " " + selectedGpu.getName(),50) + " " + padRight(String.valueOf(selectedMobo.getPrice()),8) + " PLN";
        } else {
            savedString += "\r\n" + padRight("GPU:", 13) + padRight("Integrated graphic card", 51) + padRight("0",8) +" PLN";
        }
        savedString += "\r\n"+ padRight("RAM:", 13) + padRight(selectedRam.getBrand() + " " + selectedRam.getName()
                + " " + selectedRam.getRamType() + " " +
                selectedRam.getMemorySize() + "GB " + selectedRam.getMemoryClock() + "MHz CL " + selectedRam.getCasLatency(),50)
                + " " + padRight(String.valueOf(selectedRam.getPrice()),8) + " PLN";
        if (selectedSsd != null) {
            savedString += "\r\n" + padRight("SSD:", 13) + padRight(selectedSsd.getBrand() + " " + selectedSsd.getName() + " "
                    + selectedSsd.getCapacity() + "GB " + selectedSsd.getFormFactor() + " " + selectedSsd.getInterfaceType() + " " + selectedSsd.getMemoryType(), 50) + " "
                    + padRight(String.valueOf(selectedSsd.getPrice()), 8) + " PLN";
        } else {
            savedString += "\r\n" + padRight("SSD:", 13) + padRight("No SSD selected", 51) + padRight("0",8) +" PLN";
        }
        if (selectedHdd != null) {
            savedString += "\r\n" + padRight("HDD:", 13) + padRight(selectedHdd.getBrand() + " " + selectedHdd.getName() + " "
                    + selectedHdd.getCapacity() + "GB, " + selectedHdd.getFormFactor() + " " + selectedHdd.getRotationalSpeed() + "rpm", 50) + " "
                    + padRight(String.valueOf(selectedHdd.getPrice()), 8) + " PLN";
        } else {
            savedString += "\r\n" + padRight("HDD:", 13) + padRight("No HDD selected", 51) + padRight("0",8) +" PLN";
        }
        savedString += "\r\n"+ padRight("PSU:", 13) + padRight(selectedPsu.getBrand() + " " + selectedPsu.getName() + " "
                + selectedPsu.getWattage() + "W " + selectedPsu.getCertificate80Plus(),50) + " " + padRight(String.valueOf(selectedPsu.getPrice()),8) + " PLN";
        savedString += "\r\n"+ padRight("Case:", 13) + padRight(selectedCase.getBrand() + " " + selectedCase.getName() + " ",50) +
                " " + padRight(String.valueOf(selectedCase.getPrice()),8) + " PLN";
        savedString += "\r\n----------------------------------------------------------------------------";
        savedString += "\r\n" + padRight("Total price:", 64) +  padRight(String.valueOf(df2.format(totalPrice)), 9) + "PLN";
        savedString += "\r\n----------------------------------------------------------------------------";
        savedString += "\r\n"+ padRight("Max load:", 70)  + padRight(String.valueOf(maxLoad), 5) + "W";
        savedString += "\r\n"+ padRight("Recommended PSU wattage: ", 70) + padRight(String.valueOf(df.format(recommendedPsu)), 5) + "W";
        if (((selectedMobo.getChipset().contains("H110") || selectedMobo.getChipset().contains("B150") || selectedMobo.getChipset().contains("Z170"))) &&
                (selectedCpu.getBrand().equals("Intel"))) {
            savedString += "\r\n----------------------------------------------------------------------------";
            savedString += "\r\n" + padLeft("WARNING! YOU NEED TO UPDATE MOTHERBOARD'S BIOS", 60);
            savedString += "\r\n" + padLeft("TO RUN KABY LAKE ON H110/B150/Z170 MOTHERBOARD", 60);
        }
        savedString += "\r\n----------------------------------------------------------------------------";
        savedString += "\r\nPCBuilder - made by Michał Drab";
        savedString += "\r\n----------------------------------------------------------------------------";
        return savedString;
    }

    /**
     * This method saves string to txt file.
     * @param fileNameAndPath txt file name and path
     * @param path txt file path
     * @param savedString string to save
     */
    private void saveTextToFile(String fileNameAndPath, String path, String savedString) {
        File dir = new File(path);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        BufferedWriter bw = null;
        try {
            File file = new File(fileNameAndPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(savedString);
            System.out.println("saved to " + fileNameAndPath);
            labelSaved.setText("saved to " + fileNameAndPath);
            labelSaved.getStyleClass().add("pcbuilder-enabled");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }

        }
    }


    /**
     * This method formats strings (right padding).
     * @param s string
     * @param n number of chars
     * @return formatted string
     */
    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * This method formats strings (left padding).
     * @param s string
     * @param n number of chars
     * @return formatted string
     */
    private static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    /**
     * This function adds values and updates labels
     * @param wattage wattage
     * @param price price
     */
    private void labelsAddValues(int wattage, double price){
        maxLoad += wattage;
        maxLoadCounter.setText(String.valueOf(maxLoad) + "W");
        recommendedPsu = 1.4 * maxLoad;
        recommendedPsuCounter.setText(String.valueOf(df.format(recommendedPsu)) + "W");
        totalPrice += price;
        totalPriceCounter.setText(String.valueOf(df2.format(totalPrice)) + "PLN");
    }

    /**
     * This function subs values and updates labels
     * @param wattage wattage
     * @param price price
     */
    private void labelsSubValues(int wattage, double price){
        maxLoad -= wattage;
        maxLoadCounter.setText(String.valueOf(maxLoad) + "W");
        recommendedPsu = 1.4 * maxLoad;
        recommendedPsuCounter.setText(String.valueOf(df.format(recommendedPsu)) + "W");
        totalPrice -= price;
        totalPriceCounter.setText(String.valueOf(df2.format(totalPrice)) + "PLN");
    }
}

