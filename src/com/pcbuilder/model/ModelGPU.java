package com.pcbuilder.model;
import javafx.beans.property.*;

/**
 * GPU Model Class.
 */
public class ModelGPU {

    private final StringProperty chipManufacturer;
    private final StringProperty manufacturer;
    private final StringProperty series;
    private final StringProperty name;
    private final StringProperty manufacturerCode;
    private final StringProperty architecture;
    private final DoubleProperty cardLength;
    private final IntegerProperty technology;
    private final StringProperty interfaceType;
    private final IntegerProperty memorySize;
    private final StringProperty memorySizeClass;
    private final StringProperty memoryType;
    private final IntegerProperty speed;
    private final IntegerProperty boostSpeed;
    private final DoubleProperty memorySpeed;
    private final IntegerProperty external6Pin;
    private final IntegerProperty external8Pin;
    private final IntegerProperty dpConnectors;
    private final IntegerProperty hdmiConnectors;
    private final IntegerProperty dviConnectors;
    private final IntegerProperty vgaConnectors;
    private final IntegerProperty tdp;
    private final IntegerProperty wattage;
    private final BooleanProperty isCooledPassive;
    private final BooleanProperty hasBackplate;
    private final IntegerProperty price;
    private final StringProperty imagePath;
    private final StringProperty smallImagePath;

    /**
     * Constructor:
     * @param gpuChipManufacturer producer of chip
     * @param gpuManufacturer card manufacturer
     * @param gpuSeries gpu series
     * @param gpuName gpu producer name
     * @param gpuManufacturerCode gpu manufacturer code
     * @param gpuArchitecture gpu architecture name
     * @param gpuCardLength card length [mm]
     * @param gpuTechnology technology process [nm]
     * @param gpuInterfaceType interface type
     * @param gpuMemorySize VRAM capacity [GB]
     * @param gpuMemoryType VRAM type
     * @param gpuSpeed speed [MHz]
     * @param gpuBoostSpeed boost speed [MHz]
     * @param gpuMemorySpeed memory speed [GHz]
     * @param gpuExternal6Pin number of external 6 pin connectors
     * @param gpuExternal8Pin number of external 8 pin connectors
     * @param gpuDpConnectors number of Display Port connectors
     * @param gpuHdmiConnectors number of HDMI connectors
     * @param gpuDviConnectors number of DVI connectors
     * @param gpuVgaConnectors number of VGA connectors
     * @param gpuTdp tdp given by producer
     * @param gpuWattage wattage which is used to calculate PSU requirements [W]
     * @param gpuIsCooledPassive has passive cooling system or at least passive mode?
     * @param gpuHasBackplate does this card has a backplate?
     * @param gpuPrice card price
     * @param gpuImagePath path to big image file
     * @param gpuSmallImagePath path to small image file

     */
    public ModelGPU (String gpuChipManufacturer, String gpuManufacturer, String gpuSeries, String gpuName, String gpuManufacturerCode,
                     String gpuArchitecture, double gpuCardLength, int gpuTechnology, String gpuInterfaceType, int gpuMemorySize, String gpuMemoryType,
                     int gpuSpeed, int gpuBoostSpeed, double gpuMemorySpeed, int gpuExternal6Pin, int gpuExternal8Pin, int gpuDpConnectors,
                     int gpuHdmiConnectors, int gpuDviConnectors, int gpuVgaConnectors, int gpuTdp, int gpuWattage, boolean gpuIsCooledPassive,
                     boolean gpuHasBackplate, int gpuPrice,String gpuImagePath, String gpuSmallImagePath) {

        chipManufacturer = new SimpleStringProperty(gpuChipManufacturer);
        manufacturer = new SimpleStringProperty(gpuManufacturer);
        series = new SimpleStringProperty(gpuSeries);
        name = new SimpleStringProperty(gpuName);
        manufacturerCode = new SimpleStringProperty(gpuManufacturerCode);
        cardLength = new SimpleDoubleProperty(gpuCardLength);
        architecture = new SimpleStringProperty(gpuArchitecture);
        technology = new SimpleIntegerProperty(gpuTechnology);
        interfaceType = new SimpleStringProperty(gpuInterfaceType);
        memorySize = new SimpleIntegerProperty(gpuMemorySize);
        memorySizeClass = new SimpleStringProperty(memorySizeClassCreator(gpuMemorySize));
        memoryType = new SimpleStringProperty(gpuMemoryType);
        speed = new SimpleIntegerProperty(gpuSpeed);
        boostSpeed = new SimpleIntegerProperty(gpuBoostSpeed);
        memorySpeed = new SimpleDoubleProperty(gpuMemorySpeed);
        external6Pin = new SimpleIntegerProperty(gpuExternal6Pin);
        external8Pin = new SimpleIntegerProperty(gpuExternal8Pin);
        dpConnectors = new SimpleIntegerProperty(gpuDpConnectors);
        hdmiConnectors = new SimpleIntegerProperty(gpuHdmiConnectors);
        dviConnectors = new SimpleIntegerProperty(gpuDviConnectors);
        vgaConnectors = new SimpleIntegerProperty(gpuVgaConnectors);
        tdp = new SimpleIntegerProperty(gpuTdp);
        wattage = new SimpleIntegerProperty(gpuWattage);
        isCooledPassive = new SimpleBooleanProperty(gpuIsCooledPassive);
        hasBackplate = new SimpleBooleanProperty(gpuHasBackplate);
        price = new SimpleIntegerProperty(gpuPrice);
        imagePath = new SimpleStringProperty(gpuImagePath);
        smallImagePath = new SimpleStringProperty(gpuSmallImagePath);

    }

    private String memorySizeClassCreator(int memorySize){
        String memorySizeClass="1";
        if (memorySize <= 1 || memorySize >=8) {
            if (memorySize <=1)
                memorySizeClass = "1";
            if (memorySize >= 8)
                memorySizeClass = "8";
        }
        else
            memorySizeClass = String.valueOf(memorySize);

        return memorySizeClass;
    }

    public String getChipManufacturer(){return chipManufacturer.get();}
    public String getManufacturer(){return manufacturer.get();}
    public String getseries(){return series.get();}
    public String getName(){return name.get();}
    public String getManufacturerCode(){return manufacturerCode.get();}
    public Double getCardLength(){return cardLength.get();}
    public String getarchitecture(){return architecture.get();}
    public int getTechnology(){return technology.get(); }
    public String getinterfaceType(){return interfaceType.get(); }
    public int getmemorySize(){return memorySize.get(); }
    public String getmemorySizeClass(){return memorySizeClass.get(); }
    public String getMemoryType(){return memoryType.get();}
    public int getSpeed(){return speed.get(); }
    public int getBoostSpeed(){return boostSpeed.get(); }
    public double getMemorySpeed() {return memorySpeed.get();}
    public int getExternal6Pin(){return external6Pin.get(); }
    public int getExternal8Pin(){return external8Pin.get(); }
    public int getDpConnectors(){return dpConnectors.get(); }
    public int getHdmiConnectors(){return hdmiConnectors.get(); }
    public int getDviConnectors(){return dviConnectors.get(); }
    public int getVgaConnectors(){return vgaConnectors.get(); }
    public int getTdp(){return tdp.get(); }
    public int getWattage(){return wattage.get(); }
    public boolean getIsCooledPassive(){return isCooledPassive.get(); }
    public boolean getHasBackplate(){return hasBackplate.get(); }
    public int getPrice(){return price.get(); }
    public String getImagePath(){return imagePath.get();}
    public String getSmallImagePath(){return smallImagePath.get();}


}
