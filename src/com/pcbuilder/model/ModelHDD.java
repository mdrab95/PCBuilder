package com.pcbuilder.model;
import javafx.beans.property.*;

/**
 * HDD Model Class.
 */
public class ModelHDD {
    private final StringProperty brand;
    private final StringProperty serialNumber;
    private final StringProperty name;
    private final StringProperty interfaceType; // like SATA, IDE
    private final StringProperty formFactor; // like 2.5", 3.5"
    private final IntegerProperty rotationalSpeed; // like 7200 (rpm)
    private final IntegerProperty capacity;
    private final IntegerProperty readSpeed;
    private final IntegerProperty writeSpeed;
    private final IntegerProperty cacheSize;
    private final IntegerProperty wattage;
    private final IntegerProperty price;
    private final StringProperty imagePath;
    private final StringProperty smallImagePath;

    /**
     * Constructor:
     * @param hddBrand brand
     * @param hddSerialNumber manufacturer serial number
     * @param hddName name
     * @param hddInterfaceType hdd interface type
     * @param hddFormFactor hdd form factor
     * @param hddRotationalSpeed rotational speed [rpm]
     * @param hddCapacity capacity [GB]
     * @param hddReadSpeed read speed [MB/s]
     * @param hddWriteSpeed write speed [MB/s]
     * @param hddCacheSize cache size [MB]
     * @param hddWattage wattage which is used to calculate PSU requirements [W]
     * @param hddPrice hdd price
     * @param hddImagePath path to big image file
     * @param hddSmallImagePath path to small image file
     */
    public ModelHDD (String hddBrand, String hddSerialNumber, String hddName, String hddInterfaceType, String hddFormFactor, int hddRotationalSpeed,
              int hddCapacity, int hddReadSpeed, int hddWriteSpeed, int hddCacheSize ,int hddWattage, int hddPrice, String hddImagePath, String hddSmallImagePath) {
        brand = new SimpleStringProperty(hddBrand);
        serialNumber = new SimpleStringProperty(hddSerialNumber);
        name = new SimpleStringProperty(hddName);
        interfaceType = new SimpleStringProperty(hddInterfaceType);
        formFactor = new SimpleStringProperty(hddFormFactor);
        rotationalSpeed = new SimpleIntegerProperty(hddRotationalSpeed);
        capacity = new SimpleIntegerProperty(hddCapacity);
        readSpeed = new SimpleIntegerProperty(hddReadSpeed);
        writeSpeed = new SimpleIntegerProperty(hddWriteSpeed);
        cacheSize = new SimpleIntegerProperty(hddCacheSize);
        wattage = new SimpleIntegerProperty(hddWattage);
        price = new SimpleIntegerProperty(hddPrice);
        imagePath = new SimpleStringProperty(hddImagePath);
        smallImagePath = new SimpleStringProperty(hddSmallImagePath);
    }

    public String getBrand(){return brand.get();}
    public String getSerialNumber(){return serialNumber.get();}
    public String getName(){return name.get();}
    public String getHddInterfaceType(){return interfaceType.get();}
    public String getFormFactor(){return formFactor.get();}
    public int getRotationalSpeed(){return rotationalSpeed.get(); }
    public int getCapacity(){return capacity.get(); }
    public int getReadSpeed(){return readSpeed.get(); }
    public int getWriteSpeed(){return writeSpeed.get(); }
    public int getCacheSize(){return cacheSize.get(); }
    public int getWattage(){return wattage.get(); }
    public int getPrice(){return price.get(); }
    public String getImagePath(){return imagePath.get();}
    public String getSmallImagePath(){return smallImagePath.get();}
}
