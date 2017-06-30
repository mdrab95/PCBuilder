package com.pcbuilder.model;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Comparator;

/**
 * Data loader class.
 */
public class ModelDataLoaderAndFilter {

    String path = "src/com/pcbuilder/files/"; // default path to 'files' directory
    String name;
    ObservableList <ModelCPU> cpuList = FXCollections.observableArrayList();
    ObservableList <ModelCPUCooler> cpuCoolerList = FXCollections.observableArrayList();
    ObservableList <ModelMOBO> moboList = FXCollections.observableArrayList();
    ObservableList <ModelGPU> gpuList = FXCollections.observableArrayList();
    ObservableList <ModelRAM> ramList = FXCollections.observableArrayList();
    ObservableList <ModelHDD> hddList = FXCollections.observableArrayList();
    ObservableList <ModelSSD> ssdList = FXCollections.observableArrayList();
    ObservableList<ModelPSU> psuList = FXCollections.observableArrayList();
    ObservableList <ModelCase> caseList = FXCollections.observableArrayList();

    /**
     * CPU data loader.
     * @return ModelCPU ObservableList
     */
    public ObservableList<ModelCPU> cpuDataLoader(){
        System.out.println("----------\nLoading CPU data...");
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Send inquiry to db
            ResultSet rs = statement.executeQuery("SELECT * FROM CPU");
            // Add recieved data to list

            while (rs.next()) {
                boolean cpuIsUnlocked = false;
                if (rs.getInt("IsUnlocked") == 1)
                    cpuIsUnlocked = true;
                boolean cpuHasIntegratedGraphicCard = false;
                if (rs.getInt("integratedGfx") == 1)
                    cpuHasIntegratedGraphicCard = true;
                boolean isTheCpuCoolerIncluded = false;
                if (rs.getInt("isTheCpuCoolerIncluded") == 1)
                    isTheCpuCoolerIncluded = true;
                try {
                    cpuList.add(new ModelCPU(rs.getString("brand"), rs.getString("socket"), rs.getString("name"), rs.getString("family"),
                            rs.getInt("technology"), rs.getInt("numberOfCores"), rs.getInt("numberOfThreads"), cpuIsUnlocked, cpuHasIntegratedGraphicCard,
                            rs.getDouble("speed"), rs.getDouble("boostSpeed"), rs.getDouble("cacheL2"), rs.getDouble("cacheL3"),
                            rs.getInt("tdp"), rs.getInt("wattage"), rs.getString("packageType"), isTheCpuCoolerIncluded, rs.getInt("price"),
                            rs.getString("imagePath"), rs.getString("smallImagePath")));
                } catch (Exception ex) {
                    System.out.println("Can't load data from table. Row number: " + rs.getInt("id"));
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Can't load CPU data.");
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
            Comparator<ModelCPU> comparator = Comparator.comparingInt(ModelCPU::getPrice);
            FXCollections.sort(cpuList, comparator.reversed());
            if (cpuList.size()!=0)
                System.out.println("CPU data loaded.");
            else
                System.out.println("There is no CPU data.");
        }
        return cpuList;
    }

    /**
     * CPU Cooler data loader.
     * @return ModelCPUCooler ObservableList
     */
    public ObservableList<ModelCPUCooler> cpuCoolerDataLoader() {
        System.out.println("----------\nLoading CPU Cooler data...");
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Send inquiry to db
            ResultSet rs = statement.executeQuery("SELECT * FROM CPUCOOLER");
            // Add recieved data to list

            while (rs.next()) {
                try {
                    cpuCoolerList.add(new ModelCPUCooler(rs.getString("brand"), rs.getString("name"), rs.getString("manufacturerCode"), rs.getString("sockets"),
                            rs.getString("design"), rs.getDouble("depth"), rs.getDouble("width"), rs.getDouble("height"), rs.getInt("weight"),
                            rs.getDouble("airFlow"), rs.getInt("heatPipes"), rs.getInt("numberOfFans"), rs.getDouble("fanSizeX"), rs.getDouble("fanSizeY"),
                            rs.getDouble("fanSizeHeight"), rs.getInt("minFanSpeed"), rs.getInt("maxFanSpeed"), rs.getInt("minFanNoise"), rs.getInt("maxFanNoise"),
                            rs.getInt("wattage"), rs.getString("connectorType"), rs.getInt("price"), rs.getString("imagePath"), rs.getString("smallImagePath")));
                } catch (Exception ex) {
                    System.out.println("Can't load data from table. Row number: " + rs.getInt("id"));
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Can't load CPU Cooler data.");
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
            Comparator<ModelCPUCooler> comparator = Comparator.comparingInt(ModelCPUCooler::getPrice);
            FXCollections.sort(cpuCoolerList, comparator.reversed());
            if (cpuCoolerList.size()!=0)
                System.out.println("CPU Cooler data loaded.");
            else
                System.out.println("There is no CPU Cooler data.");
        }
        return cpuCoolerList;
    }

    /**
     * PSU data loader.
     * @return ModelPSU ObservableList
     */
    public ObservableList<ModelMOBO> moboDataLoader() {
        System.out.println("----------\nLoading MOBO data...");
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Send inquiry to db
            ResultSet rs = statement.executeQuery("SELECT * FROM MOBO");
            // Add recieved data to list

            while (rs.next()) {
                try {
                    moboList.add(new ModelMOBO(rs.getString("brand"), rs.getString("serialNumber"), rs.getString("name"),
                            rs.getString("formFactor"), rs.getString("socket"), rs.getString("chipset"), rs.getString("ramStandard"),
                            rs.getInt("maxRam"), rs.getInt("ramSlots"), rs.getInt("maxRamSpeed"), rs.getString("connectors"),
                            rs.getString("audio"), rs.getInt("price"), rs.getString("imagePath"), rs.getString("smallImagePath")));
                } catch (Exception ex) {
                    System.out.println("Can't load data from table. Row number: " + rs.getInt("id"));
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Can't load MOBO data.");
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
            Comparator<ModelMOBO> comparator = Comparator.comparingInt(ModelMOBO::getPrice);
            FXCollections.sort(moboList, comparator.reversed());
            if (moboList.size()!=0)
                System.out.println("MOBO data loaded.");
            else
                System.out.println("There is no MOBO data.");
        }
        return moboList;
    }

    /**
     * GPU data loader.
     * @return ModelGPU ObservableList
     */
    public ObservableList<ModelGPU> gpuDataLoader(){
        System.out.println("----------\nLoading GPU data...");
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Send inquiry to db
            ResultSet rs = statement.executeQuery("SELECT * FROM GPU");
            // Add recieved data to list

            while (rs.next()) {
                boolean isCooledPassive = false;
                if (rs.getInt("isCooledPassive") == 1)
                    isCooledPassive = true;
                boolean hasBackplate = false;
                if (rs.getInt("hasBackplate") == 1)
                    hasBackplate = true;
                boolean isTheCpuCoolerIncluded = false;

                try {
                    gpuList.add(new ModelGPU(rs.getString("chipManufacturer"), rs.getString("manufacturer"), rs.getString("series"),
                            rs.getString("name"), rs.getString("manufacturerCode"), rs.getString("architecture"),
                            rs.getDouble("cardLength"), rs.getInt("technology"), rs.getString("interfaceType"),
                            rs.getInt("memorySize"), rs.getString("memoryType"), rs.getInt("speed"),
                            rs.getInt("boostSpeed"), rs.getDouble("memorySpeed"), rs.getInt("external6pin"),
                            rs.getInt("external8pin"), rs.getInt("dpConnectors"), rs.getInt("hdmiConnectors"),
                            rs.getInt("dviConnectors"), rs.getInt("vgaConnectors"), rs.getInt("tdp"), rs.getInt("wattage"),
                            isCooledPassive, hasBackplate, rs.getInt("price"), rs.getString("imagePath"), rs.getString("smallImagePath")));
                } catch (Exception ex) {
                    System.out.println("Can't load data from table. Row number: " + rs.getInt("id"));
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Can't load GPU data.");
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
            Comparator<ModelGPU> comparator = Comparator.comparingInt(ModelGPU::getPrice);
            FXCollections.sort(gpuList, comparator.reversed());
            if (gpuList.size()!=0)
                System.out.println("GPU data loaded.");
            else
                System.out.println("There is no GPU data.");
        }
        return gpuList;
    }

    /**
     * RAM data loader.
     * @return ModelRAM ObservableList
     */
    public ObservableList<ModelRAM> ramDataLoader() {
        System.out.println("----------\nLoading RAM data...");
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Send inquiry to db
            ResultSet rs = statement.executeQuery("SELECT * FROM RAM");
            // Add recieved data to list

            while (rs.next()) {
                boolean hasRadiator = false;
                if (rs.getInt("hasRadiator") == 1)
                    hasRadiator = true;
                boolean hasLighting = false;
                if (rs.getInt("hasLighting") == 1)
                    hasLighting = true;
                try {
                    ramList.add(new ModelRAM(rs.getString("brand"), rs.getString("serialNumber"), rs.getString("name"),
                            rs.getString("standard"), rs.getInt("memorySize"), rs.getInt("numberOfModules"),
                            rs.getInt("singleModuleSize"), rs.getString("ramType"), rs.getInt("memoryClock"),
                            rs.getInt("casLatency"), rs.getDouble("voltage"), rs.getInt("wattage"), hasRadiator, hasLighting,
                            rs.getInt("price"), rs.getString("imagePath"), rs.getString("smallImagePath")));
                } catch (Exception ex) {
                    System.out.println("Can't load data from table. Row number: " + rs.getInt("id"));
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Can't load RAM data.");
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
            Comparator<ModelRAM> comparator = Comparator.comparingInt(ModelRAM::getPrice);
            FXCollections.sort(ramList, comparator.reversed());
            if (ramList.size()!=0)
                System.out.println("RAM data loaded.");
            else
                System.out.println("There is no RAM data.");
        }
        return ramList;
    }

    /**
     * SSD data loader.
     * @return ModelSSD ObservableList
     */
    public ObservableList<ModelSSD> ssdDataLoader() {
        System.out.println("----------\nLoading SSD data...");
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Send inquiry to db
            ResultSet rs = statement.executeQuery("SELECT * FROM SSD");
            // Add recieved data to list

            while (rs.next()) {
                boolean hasRadiator = false;
                if (rs.getInt("hasRadiator") == 1)
                    hasRadiator = true;
                try {
                    ssdList.add(new ModelSSD(rs.getString("brand"), rs.getString("serialNumber"), rs.getString("name"),
                            rs.getString("interfaceType"), rs.getString("formFactor"), rs.getString("protocol"),
                            rs.getInt("capacity"), rs.getInt("readSpeed"), rs.getInt("writeSpeed"), rs.getString("memoryType"),
                            rs.getInt("tbw"), rs.getString("controllerManufacturer"), rs.getString("controllerModel"),
                            rs.getInt("wattage"), hasRadiator, rs.getInt("price"), rs.getString("imagePath"), rs.getString("smallImagePath")));
                } catch (Exception ex) {
                    System.out.println("Can't load data from table. Row number: " + rs.getInt("id"));
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Can't load SSD data.");
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
            Comparator<ModelSSD> comparator = Comparator.comparingInt(ModelSSD::getPrice);
            FXCollections.sort(ssdList, comparator.reversed());
            if (ssdList.size()!=0)
                System.out.println("SSD data loaded.");
            else
                System.out.println("There is no SSD data.");
        }
        return ssdList;
    }

    /**
     * HDD data loader.
     * @return ModelHDD ObservableList
     */
    public ObservableList<ModelHDD> hddDataLoader() {
        System.out.println("----------\nLoading HDD data...");
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Send inquiry to db
            ResultSet rs = statement.executeQuery("SELECT * FROM HDD");
            // Add recieved data to list

            while (rs.next()) {
                try {
                    hddList.add(new ModelHDD(rs.getString("brand"), rs.getString("serialNumber"), rs.getString("name"),
                            rs.getString("interfaceType"), rs.getString("formFactor"), rs.getInt("rotationalSpeed"),
                            rs.getInt("capacity"), rs.getInt("readSpeed"), rs.getInt("writeSpeed"),
                            rs.getInt("cacheSize"), rs.getInt("wattage"), rs.getInt("price"),
                            rs.getString("imagePath"), rs.getString("smallImagePath")));
                } catch (Exception ex) {
                    System.out.println("Can't load data from table. Row number: " + rs.getInt("id"));
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Can't load HDD data.");
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
            Comparator<ModelHDD> comparator = Comparator.comparingInt(ModelHDD::getPrice);
            FXCollections.sort(hddList, comparator.reversed());
            if (hddList.size()!=0)
                System.out.println("HDD data loaded.");
            else
                System.out.println("There is no HDD data.");
        }
        return hddList;
    }

    /**
     * PSU data loader.
     * @return ModelPSU ObservableList
     */
    public ObservableList<ModelPSU> psuDataLoader() {
        System.out.println("----------\nLoading PSU data...");
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Send inquiry to db
            ResultSet rs = statement.executeQuery("SELECT * FROM PSU");
            // Add recieved data to list

            while (rs.next()) {
                try {
                    boolean isModular = false;
                    if (rs.getInt("isModular") == 1)
                        isModular = true;
                    psuList.add(new ModelPSU(rs.getString("brand"), rs.getString("name"), rs.getString("manufacturerCode"),
                            rs.getString("specification"), rs.getString("certificate80Plus"),
                            rs.getString("coolingMode"), rs.getString("protection"),
                            rs.getDouble("fanSize"), rs.getDouble("depth"), isModular,
                            rs.getInt("wattage"), rs.getInt("price"), rs.getString("imagePath"),
                            rs.getString("smallImagePath")));
                } catch (Exception ex) {
                    System.out.println("Can't load data from table. Row number: " + rs.getInt("id"));
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Can't load PSU data.");
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
            Comparator<ModelPSU> comparator = Comparator.comparingInt(ModelPSU::getPrice);
            FXCollections.sort(psuList, comparator.reversed());
            if (psuList.size()!=0)
                System.out.println("PSU data loaded.");
            else
                System.out.println("There is no PSU data.");
        }
        return psuList;
    }

    /**
     * Case data loader.
     * @return ModelCase ObservableList
     */
    public ObservableList<ModelCase> caseDataLoader() {
        System.out.println("----------\nLoading Case data...");
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Send inquiry to db
            ResultSet rs = statement.executeQuery("SELECT * FROM PCCase");
            // Add recieved data to list
            while (rs.next()) {
                try {
                    caseList.add(new ModelCase(rs.getString("brand"), rs.getString("serialNumber"),
                            rs.getString("name"), rs.getString("formFactor"), rs.getString("type"),
                            rs.getString("psuPosition"), rs.getDouble("height"), rs.getDouble("width"),
                            rs.getDouble("depth"), rs.getDouble("weight"), rs.getDouble("maxCpuCoolerHeight"),
                            rs.getInt("external525"), rs.getInt("internal25"), rs.getInt("internal35"),
                            rs.getInt("frontFanNumber"), rs.getInt("maxFrontFanNumber"), rs.getInt("frontFanSize"),
                            rs.getInt("topFanNumber"), rs.getInt("maxTopFanNumber"), rs.getInt("topFanSize"),
                            rs.getInt("rearFanNumber"), rs.getInt("maxRearFanNumber"), rs.getInt("rearFanSize"),
                            rs.getInt("price"), rs.getString("imagePath"), rs.getString("smallImagePath")));
                } catch (Exception ex) {
                    System.out.println("Can't load data from table. Row number: " + rs.getInt("id"));
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Can't load Case data.");
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
            Comparator<ModelCase> comparator = Comparator.comparingInt(ModelCase::getPrice);
            FXCollections.sort(caseList, comparator.reversed());
            if (ssdList.size()!=0)
                System.out.println("Case data loaded.");
            else
                System.out.println("There is no Case data.");
            System.out.println("----------");
        }
        return caseList;
    }


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
     * /**
     * This function creates cpu cooler names list based on selected CPU.
     * @param selectedCpu selectedCpu
     * @param cpuCoolerList cpuCoolerList
     * @return cpu cooler names list
     */
    public ObservableList getCpuCoolerNames (ModelCPU selectedCpu, ObservableList<ModelCPUCooler> cpuCoolerList) {
        if (selectedCpu.getBoxCooler() == true) {
            cpuCoolerNames.add("BOX Cooler");
        }
        for (int i = 0; i < cpuCoolerList.size(); i++) {
            String cpuCoolerName = cpuCoolerList.get(i).getBrand() + " " + cpuCoolerList.get(i).getName();
            String sockets = cpuCoolerList.get(i).getSockets();
            if (sockets.contains(selectedCpu.getSocket())) {
                cpuCoolerNames.add(cpuCoolerName);
            }
        }
        return cpuCoolerNames;
    }

    /**
     * This function clears cpu cooler names list.
     */
    public void clearCpuCoolerNames () {
        cpuCoolerNames.clear();
    }

    /**
     *  /**
     * This function creates mobo names list based on selected CPU.
     * @param selectedCpu selected cpu
     * @param selectedCpuCooler selected cpu cooler
     * @param moboList mobo list
     * @return mobo names list
     */
    public ObservableList getMoboNames (ModelCPU selectedCpu, ModelCPUCooler selectedCpuCooler, ObservableList<ModelMOBO> moboList) {
        if (selectedCpu.getBoxCooler() == false && selectedCpuCooler != null || selectedCpu.getBoxCooler() == true && selectedCpuCooler != null || selectedCpu.getBoxCooler() == true && selectedCpuCooler == null) {
            moboNames.clear();
            for (int i = 0; i < moboList.size(); i++) {
                String moboName = moboList.get(i).getBrand() + " " + moboList.get(i).getChipset() + " " + moboList.get(i).getName();
                if (moboList.get(i).getSocket().equals(selectedCpu.getSocket())) {
                    moboNames.add(moboName);
                }
            }
        }
        return moboNames;
    }

    /**
     * This function clears mobo names list
     */
    public void clearMoboNames () {
        moboNames.clear();
    }

    /**
     * * This function creates gpu names list based on selected CPU.
     * @param selectedCpu selected cpu
     * @param gpuList gpu list
     * @return gpu names list
     */
    public ObservableList getGpuNames (ModelCPU selectedCpu, ObservableList<ModelGPU> gpuList){
        gpuNames.clear();
        if (selectedCpu.getHasIntegratedGraphic() == true) {
            gpuNames.add("CPU Integrated graphic card");
        }
        for (int i = 0; i < gpuList.size(); i++) {
            String gpuName = gpuList.get(i).getChipManufacturer() + " " + gpuList.get(i).getseries() + " " + gpuList.get(i).getName();
            gpuNames.add(gpuName);
        }
        return gpuNames;
    }

    /**
     * This function clears gpu names list
     */
    public void clearGpuNames () {
        gpuNames.clear();
    }

    /**
     * This function creates ram names list based on selected mobo.
     * @param selectedMobo selected mobo
     * @param ramList ram list
     * @return ram names list
     */
    public ObservableList getRamNames(ModelMOBO selectedMobo, ObservableList<ModelRAM> ramList){
        ramNames.clear();
        for (int i = 0; i < ramList.size(); i++) {
            if (selectedMobo.getRamStandard().equals(ramList.get(i).getStandard())) {
                if (selectedMobo.getChipset().equals("Z270") || selectedMobo.getChipset().equals("Z170")) {
                    String ramName = ramList.get(i).getBrand() + " " + ramList.get(i).getName() + " " + ramList.get(i).getMemorySize() + "GB (" + ramList.get(i).getNumberOfModules() + "x" + ramList.get(i).getSingleModuleSize() + "GB) " + ramList.get(i).getStandard() + " " + ramList.get(i).getMemoryClock() + "MHz (" + ramList.get(i).getSerialNumber() + ")";
                    ramNames.add(ramName);
                } else {
                    if (ramList.get(i).getMemoryClock() <= 2400) {
                        String ramName = ramList.get(i).getBrand() + " " + ramList.get(i).getName() + " " + ramList.get(i).getMemorySize() + "GB (" + ramList.get(i).getNumberOfModules() + "x" + ramList.get(i).getSingleModuleSize() + "GB) " + ramList.get(i).getStandard() + " " + ramList.get(i).getMemoryClock() + "MHz (" + ramList.get(i).getSerialNumber() + ")";
                        ramNames.add(ramName);
                    }
                }
            }
        }
        return ramNames;
    }

    /**
     * This function clears ram names list
     */
    public void clearRamNames () {
        ramNames.clear();
    }

    /**
     * This function creates ssd names list based on selected mobo.
     * @param selectedMobo selected mobo
     * @param ssdList ssd list
     * @return ssd names list
     */
    public ObservableList getSsdNames(ModelMOBO selectedMobo, ObservableList<ModelSSD> ssdList){
        ssdNames.clear();
        ssdNames.add("Select nothing");
        for (int i = 0; i < ssdList.size(); i++) {
            if (selectedMobo.getConnectors().contains("M.2")) {
                String ssdName = ssdList.get(i).getBrand() + " " + ssdList.get(i).getName() + " " + ssdList.get(i).getCapacity() + "GB (" + ssdList.get(i).getSerialNumber() + ")";
                ssdNames.add(ssdName);
            } else {
                String formFactor = ssdList.get(i).getFormFactor();
                if (formFactor.contains("M.2") == false){
                    String ssdName = ssdList.get(i).getBrand() + " " + ssdList.get(i).getName() + " " + ssdList.get(i).getCapacity() + "GB (" + ssdList.get(i).getSerialNumber() + ")";
                    ssdNames.add(ssdName);
                }
            }
        }
        return ssdNames;
    }

    /**
     * This function clears ssd names list
     */
    public void clearSsdNames () {
        ssdNames.clear();
    }

    /**
     * This function creates hdd names list based on selected ssd
     * @param selectedSsd selected ssd
     * @param hddList hdd list
     * @return hdd names list
     */
    public ObservableList getHddNames (ModelSSD selectedSsd, ObservableList<ModelHDD> hddList) {
        hddNames.clear();
        if (selectedSsd != null) {
            hddNames.add("Select nothing");
        }
        for (int i = 0; i < hddList.size(); i++) {
            String capacity = "";
            if (hddList.get(i).getCapacity() >= 1000)
                capacity = String.valueOf(hddList.get(i).getCapacity()/1000) + "TB";
            else
                capacity = hddList.get(i).getCapacity() + "GB";
            String hddName = hddList.get(i).getBrand() + " " + hddList.get(i).getName() + " " + capacity;
            hddNames.add(hddName);
        }
        return hddNames;
    }

    /**
     * This function clears hdd names list
     */
    public void clearHddNames () {
        hddNames.clear();
    }

    /**
     * This function creates psu names list based on max load
     * @param maxLoad max load wattage (W)
     * @param psuList psu list
     * @return psu names list
     */
    public ObservableList getPsuNames (double maxLoad, ObservableList<ModelPSU> psuList) {
        double minimalPsu = 1.2*maxLoad;
        psuNames.clear();
        for (int i = 0; i < psuList.size(); i++) {
            if(psuList.get(i).getWattage() >= minimalPsu) {
                String psuName = psuList.get(i).getBrand() + " " + psuList.get(i).getName() + " " + psuList.get(i).getWattage() + "W";
                psuNames.add(psuName);
            }
        }
        return psuNames;
    }

    /**
     * This function clears psu names list
     */
    public void clearPsuNames () {
        psuNames.clear();
    }

    /**
     * This function creates case names list based on form factor of selected mobo
     * @param selectedMobo selected mobo
     * @param caseList case list
     * @return case names list
     */
    public ObservableList getCaseNames (ModelMOBO selectedMobo, ObservableList<ModelCase> caseList) {
        caseNames.clear();
        for (int i = 0; i < caseList.size(); i++) {
            if (caseList.get(i).getFormFactor().contains(selectedMobo.getFormFactor())){
                String caseName = caseList.get(i).getBrand() + " " + caseList.get(i).getName();
                caseNames.add(caseName);
            }
        }
        return caseNames;
    }
}
