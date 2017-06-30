package com.pcbuilder.model;
import javafx.beans.property.*;

/**
 * SSD Model Class.
 */
public class ModelSSD {

    private final StringProperty brand;
    private final StringProperty serialNumber;
    private final StringProperty name;
    private final StringProperty interfaceType; // like SATA, M.2
    private final StringProperty formFactor; // like 2.5", M.2 2280
    private final StringProperty protocol; // like AHCI, NVME 1.2
    private final IntegerProperty capacity;
    private final IntegerProperty readSpeed;
    private final IntegerProperty writeSpeed;
    private final StringProperty memoryType; // like TLC, MLC, SLC
    private final IntegerProperty tbw;
    private final StringProperty controllerManufacturer;
    private final StringProperty controllerModel;
    private final IntegerProperty wattage;
    private final BooleanProperty hasRadiator;
    private final IntegerProperty price;
    private final StringProperty imagePath;
    private final StringProperty smallImagePath;

    /**
     *
     * @param ssdBrand ssd producer
     * @param ssdSerialNumber producer serial number code
     * @param ssdName producer name
     * @param ssdInterfaceType interface type
     * @param ssdFormFactor form factor
     * @param ssdProtocol protocol name [AHCI, NVME 1.4, ...]
     * @param ssdCapacity capacity [GB]
     * @param ssdReadSpeed read speed [MB/s]
     * @param ssdWriteSpeed write speed [MB/s]
     * @param ssdMemoryType memory type [SLC/MLC/TLC]
     * @param ssdTbw tbw-factor (TeraBytes written)
     * @param ssdControllerManufacturer controller manufacturer
     * @param ssdControllerModel controller model name
     * @param ssdWattage wattage which is used to calculate PSU requirements [W]
     * @param ssdhasRadiator does this ssd has a radiator?
     * @param ssdPrice ssd price
     * @param ssdImagePath path to big image file
     * @param ssdSmallImagePath path to small image file
     */
    public ModelSSD (String ssdBrand, String ssdSerialNumber, String ssdName, String ssdInterfaceType, String ssdFormFactor, String ssdProtocol,
              int ssdCapacity, int ssdReadSpeed, int ssdWriteSpeed, String ssdMemoryType, int ssdTbw,
              String ssdControllerManufacturer, String ssdControllerModel, int ssdWattage, boolean ssdhasRadiator,
              int ssdPrice, String ssdImagePath, String ssdSmallImagePath) {
        brand = new SimpleStringProperty(ssdBrand);
        serialNumber = new SimpleStringProperty(ssdSerialNumber);
        name = new SimpleStringProperty(ssdName);
        interfaceType = new SimpleStringProperty(ssdInterfaceType);
        formFactor = new SimpleStringProperty(ssdFormFactor);
        protocol = new SimpleStringProperty(ssdProtocol);
        capacity = new SimpleIntegerProperty(ssdCapacity);
        readSpeed = new SimpleIntegerProperty(ssdReadSpeed);
        writeSpeed = new SimpleIntegerProperty(ssdWriteSpeed);
        memoryType = new SimpleStringProperty(ssdMemoryType);
        tbw = new SimpleIntegerProperty(ssdTbw);
        controllerManufacturer = new SimpleStringProperty(ssdControllerManufacturer);
        controllerModel = new SimpleStringProperty(ssdControllerModel);
        wattage = new SimpleIntegerProperty(ssdWattage);
        hasRadiator = new SimpleBooleanProperty(ssdhasRadiator);
        price = new SimpleIntegerProperty(ssdPrice);
        imagePath = new SimpleStringProperty(ssdImagePath);
        smallImagePath = new SimpleStringProperty(ssdSmallImagePath);
    }

    public String getBrand(){return brand.get();}
    public String getSerialNumber(){return serialNumber.get();}
    public String getName(){return name.get();}
    public String getInterfaceType(){return interfaceType.get();}
    public String getFormFactor() {return formFactor.get();}
    public String getProtocol() {return protocol.get();}
    public int getCapacity(){return capacity.get(); }
    public int getReadSpeed(){return readSpeed.get(); }
    public int getWriteSpeed(){return writeSpeed.get(); }
    public String getMemoryType() {return memoryType.get();}
    public int getTbw(){return tbw.get(); }
    public String getControllerManufacturer(){return controllerManufacturer.get(); }
    public String getControllerModel(){return controllerModel.get(); }
    public int getWattage(){return wattage.get(); }
    public boolean getHasRadiator(){return hasRadiator.get();}
    public int getPrice(){return price.get(); }
    public String getImagePath(){return imagePath.get();}
    public String getSmallImagePath(){return smallImagePath.get();}

}
