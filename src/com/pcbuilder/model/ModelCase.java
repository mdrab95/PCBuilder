package com.pcbuilder.model;
import javafx.beans.property.*;

/**
 * Case Model Class.
 */
public class ModelCase {

    private final StringProperty brand;
    private final StringProperty serialNumber;
    private final StringProperty name;
    private final StringProperty formFactor; // like ATX, µATX
    private final StringProperty type;
    private final StringProperty psuPosition;
    private final DoubleProperty height;
    private final DoubleProperty width;
    private final DoubleProperty depth;
    private final DoubleProperty weight;
    private final DoubleProperty maxCpuCoolerHeight;
    private final IntegerProperty external525;
    private final IntegerProperty internal25;
    private final IntegerProperty internal35;
    private final IntegerProperty frontFanNumber;
    private final IntegerProperty maxFrontFanNumber;
    private final IntegerProperty topFanNumber;
    private final IntegerProperty maxTopFanNumber;
    private final IntegerProperty rearFanNumber;
    private final IntegerProperty maxRearFanNumber;
    private final IntegerProperty frontFanSize;
    private final IntegerProperty topFanSize;
    private final IntegerProperty rearFanSize;
    private final IntegerProperty price;
    private final StringProperty imagePath;
    private final StringProperty smallImagePath;

    /**
     * Constructor:
     * @param caseBrand case brand
     * @param caseSerialNumber manufacturer serial code-number
     * @param caseName product name
     * @param caseFormFactor case form factor - like ATX, µATX
     * @param caseType case type like mini,midi tower
     * @param casePsuPosition psu position (top/bottom)
     * @param caseHeight height [mm]
     * @param caseWidth width [mm]
     * @param caseDepth depth [mm]
     * @param caseWeight weight [mm]
     * @param caseMaxCpuCoolerHeight max cpu cooler height [mm]
     * @param caseExternal525 number of external 5.25"
     * @param caseInternal25 number of internal 2.5"
     * @param caseInternal35 number of internal 3.5"
     * @param caseFrontFanNumber number of installed front fans
     * @param caseMaxFrontFanNumber max quantity of front fans
     * @param caseFrontFanSize front fans size
     * @param caseTopFanNumber number of installed top fans
     * @param caseMaxTopFanNumber max quantity of top fans
     * @param caseTopFanSize top fans size
     * @param caseRearFanNumber number of installed rear fans
     * @param caseMaxRearFanNumber max quantity of rear fans
     * @param caseRearFanSize rear fans size
     * @param casePrice case price
     * @param caseImagePath path to big image file
     * @param caseSmallImagePath path to small image file
     */
    public ModelCase (String caseBrand, String caseSerialNumber, String caseName, String caseFormFactor, String caseType, String casePsuPosition,
                      double caseHeight, double caseWidth, double caseDepth, double caseWeight, double caseMaxCpuCoolerHeight, int caseExternal525,
                      int caseInternal25, int caseInternal35, int caseFrontFanNumber, int caseMaxFrontFanNumber, int caseFrontFanSize, int caseTopFanNumber,
                      int caseMaxTopFanNumber, int caseTopFanSize, int caseRearFanNumber, int caseMaxRearFanNumber, int caseRearFanSize,
                      int casePrice, String caseImagePath, String caseSmallImagePath ) {
        brand = new SimpleStringProperty(caseBrand);
        serialNumber = new SimpleStringProperty(caseSerialNumber);
        name = new SimpleStringProperty(caseName);
        formFactor = new SimpleStringProperty(caseFormFactor);
        type = new SimpleStringProperty(caseType);
        psuPosition = new SimpleStringProperty(casePsuPosition);
        height = new SimpleDoubleProperty(caseHeight);
        width = new SimpleDoubleProperty(caseWidth);
        depth = new SimpleDoubleProperty(caseDepth);
        weight = new SimpleDoubleProperty(caseWeight);
        maxCpuCoolerHeight = new SimpleDoubleProperty(caseMaxCpuCoolerHeight);
        external525 = new SimpleIntegerProperty(caseExternal525);
        internal25 = new SimpleIntegerProperty(caseInternal25);
        internal35 = new SimpleIntegerProperty(caseInternal35);
        frontFanNumber = new SimpleIntegerProperty(caseFrontFanNumber);
        maxFrontFanNumber = new SimpleIntegerProperty(caseMaxFrontFanNumber);
        frontFanSize = new SimpleIntegerProperty(caseFrontFanSize);
        topFanNumber = new SimpleIntegerProperty(caseTopFanNumber);
        maxTopFanNumber = new SimpleIntegerProperty(caseMaxTopFanNumber);
        topFanSize = new SimpleIntegerProperty(caseTopFanSize);
        rearFanNumber = new SimpleIntegerProperty(caseRearFanNumber);
        maxRearFanNumber = new SimpleIntegerProperty(caseMaxRearFanNumber);
        rearFanSize = new SimpleIntegerProperty(caseRearFanSize);
        price = new SimpleIntegerProperty(casePrice);
        imagePath = new SimpleStringProperty(caseImagePath);
        smallImagePath = new SimpleStringProperty(caseSmallImagePath);
    }

    public String getBrand(){return brand.get();}
    public String getSerialNumber(){return serialNumber.get();}
    public String getName(){return name.get();}
    public String getFormFactor(){return formFactor.get();}
    public String getType (){return type.get();}
    public String getPsuPosition(){return psuPosition.get(); }
    public double getWidth(){return width.get(); }
    public double getHeight(){return height.get(); }
    public double getDepth() {return depth.get();}
    public double getWeight(){return weight.get();}
    public double getMaxCpuCoolerHeight(){return maxCpuCoolerHeight.get();}
    public int getExternal525(){return external525.get(); }
    public int getInternal25(){return internal25.get(); }
    public int getInternal35(){return internal35.get(); }
    public int getFrontFanNumber(){return frontFanNumber.get(); }
    public int getMaxFrontFanNumber(){return maxFrontFanNumber.get(); }
    public int getFrontFanSize(){return frontFanSize.get(); }
    public int getTopFanNumber(){return topFanNumber.get(); }
    public int getMaxTopFanNumber(){return maxTopFanNumber.get(); }
    public int getTopFanSize(){return topFanSize.get(); }
    public int getRearFanNumber(){return rearFanNumber.get(); }
    public int getMaxRearFanNumber(){return maxRearFanNumber.get(); }
    public int getRearFanSize(){return rearFanSize.get(); }
    public int getPrice(){return price.get(); }
    public String getImagePath(){return imagePath.get();}
    public String getSmallImagePath(){return smallImagePath.get();}

}
