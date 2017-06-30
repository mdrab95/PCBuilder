package com.pcbuilder.model;
import javafx.beans.property.*;

/**
 * CPU Model class.
 */
public class ModelCPU {

    private final StringProperty brand;
    private final StringProperty socket;
    private final StringProperty name;
    private final StringProperty family;
    private final IntegerProperty technology;
    private final IntegerProperty numberOfCores;
    private final IntegerProperty numberOfThreads;
    private final BooleanProperty isUnlocked;
    private final BooleanProperty integratedGfx;
    private final DoubleProperty speed;
    private final DoubleProperty boostSpeed;
    private final DoubleProperty cacheL2;
    private final DoubleProperty cacheL3;
    private final IntegerProperty tdp;
    private final IntegerProperty wattage;
    private final StringProperty packageType;
    private final BooleanProperty isTheCpuCoolerIncluded;
    private final IntegerProperty price;
    private final StringProperty imagePath;
    private final StringProperty smallImagePath;

    /**
     * Constructor:
     * @param cpuBrand brand
     * @param cpuSocket socket
     * @param cpuName code name
     * @param cpuFamily family name
     * @param cpuTechnology technology process [nm]
     * @param cpuNumberOfCores number of cores
     * @param cpuNumberOfThreads number of threads
     * @param cpuIsUnlocked is the cpu unlocked?
     * @param cpuIntegratedGfx does it have integrated graphic card
     * @param cpuSpeed normal speed [in GHz]
     * @param cpuBoostSpeed boost speed [GHz]
     * @param cpuCacheL2 L2 cache memory [MB]
     * @param cpuCacheL3 L3 cache memory [MB]
     * @param cpuTdp tdp given by producer [W]
     * @param cpuWattage wattage which is used to calculate PSU requirements [W]
     * @param cpuPackageType type of package (oem, box)
     * @param cpuIsTheCpuCoolerIncluded does this package version has cpu cooler included?
     * @param cpuPrice price of the cpu
     * @param cpuImagePath path to big image file
     * @param cpuSmallImagePath path to small image file
     */
    public ModelCPU (String cpuBrand, String cpuSocket, String cpuName, String cpuFamily, int cpuTechnology, int cpuNumberOfCores,
                    int cpuNumberOfThreads, boolean cpuIsUnlocked, boolean cpuIntegratedGfx, double cpuSpeed, double cpuBoostSpeed, double cpuCacheL2, double cpuCacheL3,
                    int cpuTdp, int cpuWattage, String cpuPackageType, boolean cpuIsTheCpuCoolerIncluded, int cpuPrice, String cpuImagePath, String cpuSmallImagePath) {
        brand = new SimpleStringProperty(cpuBrand);
        socket = new SimpleStringProperty(cpuSocket);
        name = new SimpleStringProperty(cpuName);
        family = new SimpleStringProperty(cpuFamily);
        technology = new SimpleIntegerProperty(cpuTechnology);
        numberOfCores = new SimpleIntegerProperty(cpuNumberOfCores);
        numberOfThreads = new SimpleIntegerProperty(cpuNumberOfThreads);
        isUnlocked = new SimpleBooleanProperty(cpuIsUnlocked);
        integratedGfx = new SimpleBooleanProperty(cpuIntegratedGfx);
        speed = new SimpleDoubleProperty(cpuSpeed);
        boostSpeed = new SimpleDoubleProperty(cpuBoostSpeed);
        cacheL2 = new SimpleDoubleProperty(cpuCacheL2);
        cacheL3 = new SimpleDoubleProperty(cpuCacheL3);
        tdp = new SimpleIntegerProperty(cpuTdp);
        wattage = new SimpleIntegerProperty(cpuWattage);
        packageType = new SimpleStringProperty(cpuPackageType);
        isTheCpuCoolerIncluded = new SimpleBooleanProperty(cpuIsTheCpuCoolerIncluded);
        price = new SimpleIntegerProperty(cpuPrice);
        imagePath = new SimpleStringProperty(cpuImagePath);
        smallImagePath = new SimpleStringProperty(cpuSmallImagePath);
    }

    public String getBrand(){return brand.get();}
    public String getSocket(){return socket.get();}
    public String getName(){return name.get();}
    public String getFamily(){return family.get();}
    public int getTechnology(){return technology.get(); }
    public int getNumberOfCores(){return numberOfCores.get(); }
    public int getNumberOfThreads(){return numberOfThreads.get(); }
    public boolean getIsUnlocked() {return isUnlocked.get();}
    public boolean getHasIntegratedGraphic() {return integratedGfx.get();}
    public double getSpeed(){return speed.get(); }
    public double getBoostSpeed(){return boostSpeed.get(); }
    public double getCacheL2(){return cacheL2.get(); }
    public double getCacheL3(){return cacheL3.get(); }
    public int getTdp(){return tdp.get(); }
    public int getWattage(){return wattage.get(); }
    public String getPackageType(){return packageType.get();}
    public boolean getBoxCooler(){return isTheCpuCoolerIncluded.get(); }
    public int getPrice(){return price.get(); }
    public String getImagePath(){return imagePath.get();}
    public String getSmallImagePath(){return smallImagePath.get();}

}
