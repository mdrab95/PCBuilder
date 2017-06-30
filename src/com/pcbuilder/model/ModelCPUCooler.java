package com.pcbuilder.model;
import javafx.beans.property.*;

/**
 * CPU-Cooler Model Class.
 */
public class ModelCPUCooler {

    private final StringProperty brand;
    private final StringProperty name;
    private final StringProperty manufacturerCode;
    private final StringProperty sockets;
    private final StringProperty design;
    private final DoubleProperty depth;
    private final DoubleProperty width;
    private final DoubleProperty height;
    private final IntegerProperty weight;
    private final DoubleProperty airFlow;
    private final IntegerProperty heatPipes;
    private final IntegerProperty numberOfFans;
    private final DoubleProperty fanSizeX;
    private final DoubleProperty fanSizeY;
    private final DoubleProperty fanSizeHeight;
    private final IntegerProperty minFanSpeed;
    private final IntegerProperty maxFanSpeed;
    private final IntegerProperty minFanNoise;
    private final IntegerProperty maxFanNoise;
    private final IntegerProperty wattage;
    private final StringProperty connectorType;
    private final IntegerProperty price;
    private final StringProperty imagePath;
    private final StringProperty smallImagePath;

    /**
     * Constructor:
     * @param cpuCoolerBrand cooler brand
     * @param cpuCoolerName cooler code name
     * @param cpuCoolerManufacturerCode cooler manufacturer's code
     * @param cpuCoolerSockets fitting sockets
     * @param cpuCoolerDesign cooler design (tower cooler / top-blow cooler)
     * @param cpuCoolerDepth cooler depth [mm]
     * @param cpuCoolerWidth cooler width [mm]
     * @param cpuCoolerHeight cooler height [mm]
     * @param cpuCoolerWeight cooler weight [mm]
     * @param cpuCoolerAirFlow air flow [m³/h]
     * @param cpuCoolerHeatPipes number of heat pipes
     * @param cpuCoolerNumberOfFans number of fans
     * @param cpuCoolerFanSizeX width of fan(s) [mm]
     * @param cpuCoolerFanSizeY length of fan(s) [mm]
     * @param cpuCoolerFanSizeHeight height of fan(s) [mm]
     * @param cpuCoolerMinFanSpeed fan speed - min [rpm]
     * @param cpuCoolerMaxFanSpeed fan speed - max [rpm]
     * @param cpuCoolerMinFanNoise fan noise - min [dBA]
     * @param cpuCoolerMaxFanNoise fan noise - max [dBA]
     * @param cpuCoolerWattage wattage which is used to calculate PSU requirements [W]
     * @param cpuCoolerConnectorType connector type (3-PIN / 4PIN PWM)
     * @param cpuCoolerPrice cooler price
     * @param cpuCoolerImagePath path to big image file
     * @param cpuCoolerSmallImagePath path to small image file
     */
    public ModelCPUCooler (String cpuCoolerBrand, String cpuCoolerName, String cpuCoolerManufacturerCode, String cpuCoolerSockets,
                           String cpuCoolerDesign, double cpuCoolerDepth, double cpuCoolerWidth, double cpuCoolerHeight, int cpuCoolerWeight,
                           double cpuCoolerAirFlow, int cpuCoolerHeatPipes, int cpuCoolerNumberOfFans, double cpuCoolerFanSizeX, double cpuCoolerFanSizeY,
                           double cpuCoolerFanSizeHeight, int cpuCoolerMinFanSpeed, int cpuCoolerMaxFanSpeed, int cpuCoolerMinFanNoise,
                           int cpuCoolerMaxFanNoise, int cpuCoolerWattage, String cpuCoolerConnectorType, int cpuCoolerPrice,
                           String cpuCoolerImagePath, String cpuCoolerSmallImagePath ) {

        brand = new SimpleStringProperty(cpuCoolerBrand);
        name = new SimpleStringProperty(cpuCoolerName);
        manufacturerCode = new SimpleStringProperty(cpuCoolerManufacturerCode);
        sockets = new SimpleStringProperty(cpuCoolerSockets);
        design = new SimpleStringProperty(cpuCoolerDesign);
        depth = new SimpleDoubleProperty(cpuCoolerDepth);
        width = new SimpleDoubleProperty(cpuCoolerWidth);
        height = new SimpleDoubleProperty(cpuCoolerHeight);
        weight = new SimpleIntegerProperty(cpuCoolerWeight);
        airFlow = new SimpleDoubleProperty(cpuCoolerAirFlow);
        heatPipes = new SimpleIntegerProperty(cpuCoolerHeatPipes);
        numberOfFans = new SimpleIntegerProperty(cpuCoolerNumberOfFans);
        fanSizeX = new SimpleDoubleProperty(cpuCoolerFanSizeX);
        fanSizeY = new SimpleDoubleProperty(cpuCoolerFanSizeY);
        fanSizeHeight = new SimpleDoubleProperty(cpuCoolerFanSizeHeight);
        minFanSpeed = new SimpleIntegerProperty(cpuCoolerMinFanSpeed);
        maxFanSpeed = new SimpleIntegerProperty(cpuCoolerMaxFanSpeed);
        minFanNoise = new SimpleIntegerProperty(cpuCoolerMinFanNoise);
        maxFanNoise = new SimpleIntegerProperty(cpuCoolerMaxFanNoise);
        wattage = new SimpleIntegerProperty(cpuCoolerWattage);
        connectorType = new SimpleStringProperty(cpuCoolerConnectorType);
        price = new SimpleIntegerProperty(cpuCoolerPrice);
        imagePath = new SimpleStringProperty(cpuCoolerImagePath);
        smallImagePath = new SimpleStringProperty(cpuCoolerSmallImagePath);
    }

    public String getBrand(){return brand.get();}
    public String getName(){return name.get();}
    public String getManufacturerCode(){return manufacturerCode.get();}
    public String getSockets(){return sockets.get();}
    public String getDesign(){return design.get();}
    public double getDepth(){return depth.get(); }
    public double getWidth(){return width.get(); }
    public double getHeight(){return height.get(); }
    public int getWeight(){return weight.get(); }
    public double getAirFlow(){return airFlow.get(); }
    public int getHeatPipes() {return heatPipes.get();}
    public int getNumberOfFans() {return numberOfFans.get();}
    public double getFanSizeX() {return fanSizeX.get();}
    public double getFanSizeY() {return fanSizeY.get();}
    public double getFanSizeHeight() {return fanSizeHeight.get();}
    public int getMinFanSpeed(){return minFanSpeed.get(); }
    public int getMaxFanSpeed(){return maxFanSpeed.get(); }
    public int getMinFanNoise(){return minFanNoise.get(); }
    public int getMaxFanNoise(){return maxFanNoise.get(); }
    public int getWattage(){return wattage.get(); }
    public String getConnectorType(){return connectorType.get();}
    public int getPrice(){return price.get(); }
    public String getImagePath(){return imagePath.get();}
    public String getSmallImagePath(){return smallImagePath.get();}

}
