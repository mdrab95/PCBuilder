package com.pcbuilder.model;
import javafx.beans.property.*;

/**
 * RAM Model Class.
 */
public class ModelRAM {

    private final StringProperty brand;
    private final StringProperty serialNumber;
    private final StringProperty name;
    private final StringProperty standard; // like ddr4
    private final IntegerProperty memorySize;
    private final IntegerProperty numberOfModules;
    private final IntegerProperty singleModuleSize;
    private final StringProperty ramType; // like DIMM, SO-DIMM
    private final IntegerProperty memoryClock;
    private final IntegerProperty casLatency;
    private final DoubleProperty voltage;
    private final IntegerProperty wattage;
    private final BooleanProperty hasRadiator;
    private final BooleanProperty hasLighting;
    private final IntegerProperty price;
    private final StringProperty imagePath;
    private final StringProperty smallImagePath;

    /**
     * Constructor:
     * @param ramBrand RAM brand
     * @param ramSerialNumber manufacturer serial code-number
     * @param ramName product name
     * @param ramStandard standard type
     * @param ramMemorySize memory-kit capacity [GB]
     * @param ramNumberOfModules number of modules
     * @param ramSingleModuleSize size of the single module [GB]
     * @param ramRamType memory type (DIMM, SO-DIMM)
     * @param ramMemoryClock clock speed [MHz]
     * @param ramCasLatency CAS-Latency (CL)
     * @param ramVoltage Voltage [V]
     * @param ramWattage wattage which is used to calculate PSU requirements [W]
     * @param ramHasRadiator does this RAM has radiator?
     * @param ramHasLighting does this RAM has lighting?
     * @param ramPrice ram price
     * @param ramImagePath path to big image file
     * @param ramSmallImagePath path to small image file
     */
    public ModelRAM (String ramBrand, String ramSerialNumber, String ramName, String ramStandard, int ramMemorySize, int ramNumberOfModules,
              int ramSingleModuleSize, String ramRamType, int ramMemoryClock, int ramCasLatency, double ramVoltage,
              int ramWattage, boolean ramHasRadiator, boolean ramHasLighting, int ramPrice, String ramImagePath, String ramSmallImagePath ) {
        brand = new SimpleStringProperty(ramBrand);
        serialNumber = new SimpleStringProperty(ramSerialNumber);
        name = new SimpleStringProperty(ramName);
        standard = new SimpleStringProperty(ramStandard);
        memorySize = new SimpleIntegerProperty(ramMemorySize);
        numberOfModules = new SimpleIntegerProperty(ramNumberOfModules);
        singleModuleSize = new SimpleIntegerProperty(ramSingleModuleSize);
        ramType = new SimpleStringProperty(ramRamType);
        memoryClock = new SimpleIntegerProperty(ramMemoryClock);
        casLatency = new SimpleIntegerProperty(ramCasLatency);
        voltage = new SimpleDoubleProperty(ramVoltage);
        wattage = new SimpleIntegerProperty(ramWattage);
        hasRadiator = new SimpleBooleanProperty(ramHasRadiator);
        hasLighting = new SimpleBooleanProperty(ramHasLighting);
        price = new SimpleIntegerProperty(ramPrice);
        imagePath = new SimpleStringProperty(ramImagePath);
        smallImagePath = new SimpleStringProperty(ramSmallImagePath);
    }

    public String getBrand(){return brand.get();}
    public String getSerialNumber(){return serialNumber.get();}
    public String getName(){return name.get();}
    public String getStandard(){return standard.get();}
    public int getMemorySize(){return memorySize.get(); }
    public int getNumberOfModules(){return numberOfModules.get(); }
    public int getSingleModuleSize(){return singleModuleSize.get(); }
    public String getRamType() {return ramType.get();}
    public int getMemoryClock() {return memoryClock.get();}
    public int getCasLatency() {return casLatency.get();}
    public double getVoltage() {return voltage.get();}
    public int getWattage(){return wattage.get(); }
    public boolean getHasRadiator(){return hasRadiator.get(); }
    public boolean getHasLighting(){return hasLighting.get(); }
    public int getPrice(){return price.get(); }
    public String getImagePath(){return imagePath.get();}
    public String getSmallImagePath(){return smallImagePath.get();}

}
