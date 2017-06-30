package com.pcbuilder.model;

import javafx.beans.property.*;

/**
 * PSU Model Class.
 */
public class ModelPSU {
    private final StringProperty brand;
    private final StringProperty name;
    private final StringProperty manufacturerCode;
    private final StringProperty specification;
    private final StringProperty certificate80Plus;
    private final StringProperty coolingMode;
    private final StringProperty protection;
    private final DoubleProperty fanSize;
    private final DoubleProperty depth;
    private final BooleanProperty isModular;
    private final IntegerProperty wattage;
    private final IntegerProperty price;
    private final StringProperty imagePath;
    private final StringProperty smallImagePath;

    /**
     * Constructor:
     * @param psuBrand psu producer
     * @param psuName item name
     * @param psuManufacturerCode manufacturer code
     * @param psuSpecification psu size specification (ATX, pico, ...)
     * @param psuCertificate80Plus 80 Plus certificate name
     * @param psuCoolingMode passive / active / active with passive mode
     * @param psuProtection protective mechanisms
     * @param psuFanSize fan diameter [mm]
     * @param psuDepth depth [mm]
     * @param psuIsModular has modular cabling?
     * @param psuWattage wattage [W]
     * @param psuPrice item price
     * @param psuImagePath path to big image file
     * @param psuSmallImagePath path to small image file
     */
    public ModelPSU(String psuBrand, String psuName, String psuManufacturerCode, String psuSpecification, String psuCertificate80Plus, String psuCoolingMode,
                    String psuProtection, double psuFanSize, double psuDepth, boolean psuIsModular, int psuWattage, int psuPrice, String psuImagePath, String psuSmallImagePath){
        brand = new SimpleStringProperty(psuBrand);
        name = new SimpleStringProperty(psuName);
        manufacturerCode = new SimpleStringProperty(psuManufacturerCode);
        specification = new SimpleStringProperty(psuSpecification);
        certificate80Plus = new SimpleStringProperty(psuCertificate80Plus);
        coolingMode = new SimpleStringProperty(psuCoolingMode);
        protection = new SimpleStringProperty(psuProtection);
        fanSize = new SimpleDoubleProperty(psuFanSize);
        depth = new SimpleDoubleProperty(psuDepth);
        isModular = new SimpleBooleanProperty(psuIsModular);
        wattage = new SimpleIntegerProperty(psuWattage);
        price = new SimpleIntegerProperty(psuPrice);
        imagePath = new SimpleStringProperty(psuImagePath);
        smallImagePath = new SimpleStringProperty(psuSmallImagePath);
    }

    public String getBrand(){ return brand.get();}
    public String getName(){ return name.get();}
    public String getManufacturerCode(){return manufacturerCode.get();}
    public String getSpecification(){ return specification.get();}
    public String getCertificate80Plus(){ return certificate80Plus.get();}
    public String getCoolingMode(){ return coolingMode.get();}
    public String getProtection() {return protection.get();}
    public double getFanSize(){return fanSize.get();}
    public double getDepth(){return depth.get();}
    public boolean getIsModular(){return isModular.get();}
    public int getWattage(){return wattage.get(); }
    public int getPrice(){return price.get(); }
    public String getImagePath(){return imagePath.get();}
    public String getSmallImagePath(){return smallImagePath.get();}

}
