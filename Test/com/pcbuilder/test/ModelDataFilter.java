package com.pcbuilder.test;

import com.pcbuilder.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Ace on 28.06.2017.
 */
class ModelDataFilter {

    //region data init
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelCPU> cpuList = FXCollections.observableArrayList();
    ObservableList<ModelCPUCooler> cpuCoolerList = FXCollections.observableArrayList();
    ObservableList<ModelMOBO> moboList = FXCollections.observableArrayList();
    ObservableList<ModelGPU> gpuList = FXCollections.observableArrayList();
    ObservableList<ModelRAM> ramList = FXCollections.observableArrayList();
    ObservableList<ModelHDD> hddList = FXCollections.observableArrayList();
    ObservableList<ModelSSD> ssdList = FXCollections.observableArrayList();
    ObservableList<ModelPSU> psuList = FXCollections.observableArrayList();
    ObservableList<ModelCase> caseList = FXCollections.observableArrayList();
    ModelCPU selectedCpu;
    ModelCPUCooler selectedCpuCooler;
    ModelMOBO selectedMobo;
    ModelGPU selectedGpu;
    ModelRAM selectedRam;
    ModelSSD selectedSsd;
    ModelHDD selectedHdd;
    ModelPSU selectedPsu;
    ModelCase selectedCase;
    ObservableList cpuCoolerNames = FXCollections.observableArrayList();
    ObservableList moboNames = FXCollections.observableArrayList();
    ObservableList gpuNames = FXCollections.observableArrayList();
    ObservableList cpuNames = FXCollections.observableArrayList();
    ObservableList ramNames = FXCollections.observableArrayList();
    ObservableList ssdNames = FXCollections.observableArrayList();
    ObservableList hddNames = FXCollections.observableArrayList();
    ObservableList psuNames = FXCollections.observableArrayList();
    ObservableList caseNames = FXCollections.observableArrayList();
    //endregion

    @Test
    void getCpuCoolerNamesTest() {
        System.out.println("CPU Cooler names method test: " +
                "\n1. Load all CPU Coolers" +
                "\n2. Create CPU Cooler names list based on selected cpu socket" +
                "\n3. For every CPU Cooler check if socket is ok.\n--------------------");
        try {
            cpuCoolerList.addAll(dlaf.cpuCoolerDataLoader());
        } catch (Exception ex) {
            fail("Can't load data!");
        }

        selectedCpu = new ModelCPU("Intel", "1151", "7700", "Core i7", 14, 4, 8, true, true,
                3.6, 4.2, 1, 8, 65, 95, "box", true, 1580,
                "images/cpuImages/big/i7-7700.png", "images/cpuImages/small/i7-7700_small.png");

        cpuCoolerNames.addAll(dlaf.getCpuCoolerNames(selectedCpu, cpuCoolerList));
        if (cpuCoolerNames.size() == 0) {
            fail("Cpu cooler names list is null!");
        }

        for (int i = 0; i < cpuCoolerNames.size(); i++) {
            for (int j = 0; j < cpuCoolerList.size(); j++) {
                String name = cpuCoolerNames.get(i).toString();
                if (name.matches(cpuCoolerList.get(j).getBrand() + " " + cpuCoolerList.get(j).getName())) {
                    if (cpuCoolerList.get(j).getSockets().contains(selectedCpu.getSocket()) == false) {
                        if (name.equals("BOX Cooler") == false) {
                            fail("CPU Cooler has wrong socket! Invalid method.");
                        }
                        if (name.equals("BOX Cooler")) {
                            if (selectedCpu.getBoxCooler() == false)
                                fail("CPU has no box cooler included! Invalid method.");
                        }
                    } else {
                        System.out.println("CPU Cooler number " + (j + 1) + ": socket - ok");
                    }
                }
            }
        }
        System.out.println("--------------------  " +
                "\nAll CPU Coolers have proper sockets." +
                "\nMethod is OK.");
    }

    @Test
    void getMoboNamesTest() {
        System.out.println("MOBO names method test: " +
                "\n1. Load all motherboards" +
                "\n2. Create mobo names list based on selected cpu socket" +
                "\n3. For every motherboard check if socket is ok.\n--------------------");
        try {
            moboList.addAll(dlaf.moboDataLoader());
        } catch (Exception ex) {
            fail("Can't load data!");
        }

        selectedCpu = new ModelCPU("Intel", "1151", "7700", "Core i7", 14, 4, 8, true, true,
                3.6, 4.2, 1, 8, 91, 95, "box", true, 1350,
                "images/cpuImages/big/i7-7700.png", "images/cpuImages/small/i7-7700_small.png");

        moboNames.addAll(dlaf.getMoboNames(selectedCpu, selectedCpuCooler, moboList));
        if (moboNames.size() == 0) {
            fail("Cpu cooler names list is null!");
        }

        for (int i = 0; i < moboNames.size(); i++) {
            String name = moboNames.get(i).toString();
            for (int j = 0; j < moboList.size(); j++) {
                if (name.matches(moboList.get(j).getBrand() + " " + moboList.get(j).getChipset() + " " + moboList.get(j).getName())) {
                    if (moboList.get(j).getSocket().equals(selectedCpu.getSocket()) == false) {
                        fail("MOBO has wrong socket! Invalid method.");
                    } else {
                        System.out.println("Mobo number " + (j + 1) + ": socket - ok");
                    }
                }
            }
        }
        System.out.println("--------------------  " +
                "\nAll MOBOs have proper sockets." +
                "\nMethod is OK.");
    }

    @Test
    void getGpuNamesTest() {
        System.out.println("GPU names method test: " +
                "\n1. Load all GPUs" +
                "\n2. Select CPU with integrated graphic card." +
                "\n3. Check if GPU names contains integrated graphics or not." +
                "\n3. Select cpu without integrated graphic card." +
                "\n4. Check if GPU names contains integrated graphics or not. \n--------------------");
        try {
            gpuList.addAll(dlaf.gpuDataLoader());
        } catch (Exception ex) {
            fail("Can't load data!");
        }

        selectedCpu = new ModelCPU("Intel", "1151", "7700", "Core i7", 14, 4, 8, true, true,
                3.6, 4.2, 1, 8, 91, 95, "box", true, 1350,
                "images/cpuImages/big/i7-7700.png", "images/cpuImages/small/i7-7700_small.png");

        gpuNames.addAll(dlaf.getGpuNames(selectedCpu, gpuList));
        if (gpuNames.size() == 0) {
            fail("Cpu cooler names list is null!");
        }
        String integratedGfx = " this cpu have integrated graphic card.";
        if (selectedCpu.getHasIntegratedGraphic() == false)
            integratedGfx = " this cpu doesn't have integrated graphic card.";
        System.out.println("CPU1: " + selectedCpu.getBrand() + " " + selectedCpu.getFamily() + " " + selectedCpu.getName() + integratedGfx);
        for (int i = 0; i < gpuNames.size(); i++) {
            String name = gpuNames.get(i).toString();
            if (name.equals("CPU Integrated graphic card")) {
                System.out.println("Integrated graphic option found - OK.");
                break;
            }
        }

        gpuNames.clear();
        selectedCpu = new ModelCPU("Intel", "2066", "7740k", "Core i7", 14, 4, 8, true, false,
                4.3, 4.5, 1, 8, 145, 112, "box", true, 1750,
                "images/cpuImages/big/i7-7700.png", "images/cpuImages/small/i7-7700_small.png");
        gpuNames.addAll(dlaf.getGpuNames(selectedCpu, gpuList));
        integratedGfx = " this cpu have integrated graphic card.";
        if (selectedCpu.getHasIntegratedGraphic() == false)
            integratedGfx = " this cpu doesn't have integrated graphic card.";
        if (gpuNames.size() == 0) {
            fail("Cpu cooler names list is null!");
        }
        System.out.println("CPU2: " + selectedCpu.getBrand() + " " + selectedCpu.getFamily() + " " + selectedCpu.getName() + integratedGfx);
        for (int i = 0; i < gpuNames.size(); i++) {
            String name = gpuNames.get(i).toString();
            if (name.equals("CPU Integrated graphic card")) {
                System.out.println("Integrated graphic option found.");
                fail("This CPU has no integrated graphic card. Method is incorrect.");
            }
        }
        System.out.println("Integrated graphic option not found - OK.");
        System.out.println("--------------------  " +
                "\nCPU1 has integrated graphic card, gpu names list has that option." +
                "\nCPU2 has no integrated graphic card, gpu names list dont have that option." +
                "\nMethod is OK.");
    }

    @Test
    void getRamNamesTest() {
        System.out.println("RAM names method test: " +
                "\n1. Load all RAM" +
                "\n2. Select MOBO with H110m chipset" +
                "\n3. Check if RAM names contains RAM with <=2400MHz clock only" +
                "\n3. Select MOBO with Z270 chipsest" +
                "\n4. Check if RAM names contains all elements from ram list \n--------------------");
        try {
            ramList.addAll(dlaf.ramDataLoader());
        } catch (Exception ex) {
            fail("Can't load data!");
        }
        selectedMobo = new ModelMOBO("ASRock", "90-MXGZU0-A0UAYZ", "H110M-HDV", "Micro ATX", "1151", "H110",
                "DDR4", 32, 2, 2400, "2x PCI Express x1,1x PCI Express x16, 4x SATA III, 1x HDMI, 1x DVI-D, 1x PS/2, 3x audio connector, 4x USB 2.0, 2x USB 3.0, 1x D-Sub (VGA), 1x RJ-45",
                "Realtek ALC887", 220, "images/moboImages/big/", "images/moboImages/small/");
        System.out.println("MOBO1 chipset: " + selectedMobo.getChipset());
        ramNames.addAll(dlaf.getRamNames(selectedMobo, ramList));
        for (int i = 0; i < ramNames.size(); i++) {
            for (int j = 0; j < ramList.size(); j++) {
                String ramName = ramList.get(j).getBrand() + " " + ramList.get(j).getName() + " " + ramList.get(j).getMemorySize() + "GB (" + ramList.get(j).getNumberOfModules() + "x" + ramList.get(j).getSingleModuleSize() + "GB) " + ramList.get(j).getStandard() + " " + ramList.get(j).getMemoryClock() + "MHz (" + ramList.get(j).getSerialNumber() + ")";
                if (ramName.equals(ramNames.get(i).toString())) {
                    if (ramList.get(j).getMemoryClock() > 2400) {
                        fail("RAM Memory clock >2400MHz. This chipset allows us to use RAM with clock speed up to 2400MHz only. Method is incorrect");
                    } else {
                        System.out.println("RAM memory clock: " + ramList.get(j).getMemoryClock() + " - OK.");
                    }
                }
            }
        }
        System.out.println("All RAM have memory clock <=2400MHz. OK. ");
        selectedMobo = new ModelMOBO("MSI", "Z270 GAMING M5", "Z270 GAMING M5", "ATX", "1151", "Z270",
                "DDR4", 64, 4, 3800, "3x PCI Express x1,3x PCI Express x16, 6x SATA III, 1x U.2 port, 2x M.2 slot, 1x HDMI, 1x DisplayPort, 1x PS/2, 1x USB 3.1, 1x USB typu C, 3x USB 2.0, 2x USB 3.1 gen 1, 1x RJ-45",
                "Realtek ALC1220", 795, "images/moboImages/big/", "images/moboImages/small/");
        System.out.println("MOBO2 chipset: " + selectedMobo.getChipset());
        ramNames.clear();
        ramNames.addAll(dlaf.getRamNames(selectedMobo, ramList));
        for (int i = 0; i < ramNames.size(); i++) {
            for (int j = 0; j < ramList.size(); j++) {
                String ramName = ramList.get(j).getBrand() + " " + ramList.get(j).getName() + " " + ramList.get(j).getMemorySize() + "GB (" + ramList.get(j).getNumberOfModules() + "x" + ramList.get(j).getSingleModuleSize() + "GB) " + ramList.get(j).getStandard() + " " + ramList.get(j).getMemoryClock() + "MHz (" + ramList.get(j).getSerialNumber() + ")";
                if (ramName.equals(ramNames.get(i).toString())) {
                    System.out.println("RAM memory clock: " + ramList.get(j).getMemoryClock() + " - OK.");
                }
            }
        }
        System.out.println("Check if both lists have same size:");
        if (ramNames.size() != ramList.size())
            fail("Ram list has more elements than RAM names list. Method is incorrect");
        System.out.println("RAM names list and RAM list both have the same size - OK.");
        System.out.println("--------------------  " +
                "\nMethod is OK.");
    }

    @Test
    void getSsdNamesTest(){
        System.out.println("SSD names method test: " +
                "\n1. Load all SSD" +
                "\n2. Select MOBO without m.2 slot" +
                "\n3. Check if SSD names list contains SSD with m.2 form factor" +
                "\n3. Select MOBO with m.2 slot" +
                "\n4. Check if SSD names list contains all elements from SSD list \n--------------------");
        try {
            ssdList.addAll(dlaf.ssdDataLoader());
        } catch (Exception ex) {
            fail("Can't load data!");
        }
        selectedMobo = new ModelMOBO("ASRock", "90-MXGZU0-A0UAYZ", "H110M-HDV", "Micro ATX", "1151", "H110",
                "DDR4", 32, 2, 2400, "2x PCI Express x1,1x PCI Express x16, 4x SATA III, 1x HDMI, 1x DVI-D, 1x PS/2, 3x audio connector, 4x USB 2.0, 2x USB 3.0, 1x D-Sub (VGA), 1x RJ-45",
                "Realtek ALC887", 220, "images/moboImages/big/", "images/moboImages/small/");
        System.out.println("MOBO1 have no M.2 slot. ");
        ssdNames.addAll(dlaf.getSsdNames(selectedMobo, ssdList));
        for (int i = 0; i < ssdNames.size(); i++) {
            for (int j = 0; j < ssdList.size(); j++) {
                String ssdName = ssdList.get(j).getBrand() + " " + ssdList.get(j).getName() + " " + ssdList.get(j).getCapacity() + "GB (" + ssdList.get(j).getSerialNumber() + ")";
                if (ssdName.equals(ssdNames.get(i).toString())) {
                    if (ssdList.get(j).getInterfaceType().contains("M.2")) {
                        fail("M.2 SSD found. This motherboard allows us to use SATA SSDs only. Method is incorrect");
                    } else {
                        System.out.println("SSD form factor is not M.2 - OK.");
                    }
                }
            }
        }
        System.out.println("No SSD has M.2 form factor. OK. ");
        selectedMobo = new ModelMOBO("MSI", "Z270 GAMING M5", "Z270 GAMING M5", "ATX", "1151", "Z270",
                "DDR4", 64, 4, 3800, "3x PCI Express x1,3x PCI Express x16, 6x SATA III, 1x U.2 port, 2x M.2 slot, 1x HDMI, 1x DisplayPort, 1x PS/2, 1x USB 3.1, 1x USB typu C, 3x USB 2.0, 2x USB 3.1 gen 1, 1x RJ-45",
                "Realtek ALC1220", 795, "images/moboImages/big/", "images/moboImages/small/");
        System.out.println("MOBO2 chipset: " + selectedMobo.getChipset());
        ssdNames.clear();
        ssdNames.addAll(dlaf.getSsdNames(selectedMobo, ssdList));
        System.out.println("MOBO2 have M.2 slot. Size of SSD names list and SSD list should be equal.");
        System.out.println("Check if both lists have same size:");
        if (ssdNames.size() != ssdList.size()+1) // +1, because ssdNames has additional "NO SSD" option.
            fail("SSSD list has more elements than SSD names list. Method is incorrect");
        System.out.println("SSD names list and SSD list both have the same size - OK.");
        System.out.println("--------------------  " +
                "\nMethod is OK.");
    }

    @Test
    void getHddNamesTest(){
        System.out.println("HDD names method test: " +
                "\n1. Load all HDD" +
                "\n2. Select SSD as 'NO SSD SELECTED'" +
                "\n3. Check if HDD names list is empty (it should be)" +
                "\n3. Select random SSD (not null option)" +
                "\n4. Check if HDD names list is not empty \n--------------------");
        try {
            hddList.addAll(dlaf.hddDataLoader());
        } catch (Exception ex) {
            fail("Can't load data!");
        }
        selectedSsd = null;
        System.out.println("No SSD selected.");
        hddNames.addAll(dlaf.getHddNames(selectedSsd, hddList));
        if (hddNames.size() == 0)
            fail ("No SSD selected and list is empty. Invalid method.");
        System.out.println("HDD names list is not empty - OK");
        selectedSsd = new ModelSSD("Samsung", "MZVPW256HEGL-00000", "SM961", "M.2 (PCIE)", "M.2 2280",
                "NVME 1.2", 256,3100,1400, "MLC" , 0, "Samsung", "Polaris", 5,
                false, 620, "images/ssdImages/big/", "images/ssdImages/small/");
        System.out.println("SSD Selected (not null option)");
        hddNames.clear();
        hddNames.addAll(dlaf.getHddNames(selectedSsd, hddList));
        if(hddNames.size() != hddList.size()+1)
            fail ("SSD has been selected but HDD names list is empty or there is no 'NO HDD' option. Invalid method.");
        System.out.println("HDD names list is empty while SSD is not selected\n"
                        + "HDD names list is not empty while SSD is selected.");
        System.out.println("--------------------  " +
                "\nMethod is OK.");

    }

    @Test
    void getPsuNamesTest(){
        System.out.println("PSU names method test: " +
                "\n1. Load all PSU data" +
                "\n2. Set max load as 400W" +
                "\n3. Minimal PSU Wattage should be equal to 480W" +
                "\n3. If there is any psu with wattage < 480W - fail" +
                "\n4. Set max load as 0W. PSU names list should be equal to PSU list \n--------------------");
        try {
            psuList.addAll(dlaf.psuDataLoader());
        } catch (Exception ex) {
            fail("Can't load data!");
        }
        psuNames.addAll(dlaf.getPsuNames(400, psuList));
        for (int i=0; i<psuNames.size(); i++){
            String psuName = psuList.get(i).getBrand() + " " + psuList.get(i).getName() + " " + psuList.get(i).getWattage() + "W";
            if (psuName.equals(psuNames.get(i))){
                if (psuList.get(i).getWattage() < 480) {
                    fail ("PSU Has " + psuList.get(i).getWattage() + "W - Method is incorrect.");
                } else {
                    System.out.println("PSU Has " + psuList.get(i).getWattage() + "W - Method is OK.");
                }
            }
        }
        System.out.println("All PSUs have proper wattage.");
        psuNames.clear();
        psuNames.addAll(dlaf.getPsuNames(0, psuList));
        if (psuNames.size() != psuList.size())
            fail("Wattage is equal to 0 - PSU names list should be equal to PSU list. Method is incorrect.");
        System.out.println("--------------------  " +
                "\nMethod is OK.");
    }

    @Test
    void getCaseNamesTest() {
        System.out.println("Case names method test: " +
                "\n1. Load all cases" +
                "\n2. Create case names list based on selected mobo form factor" +
                "\n3. For every motherboard check if form factor is ok.\n--------------------");
        try {
            caseList.addAll(dlaf.caseDataLoader());
        } catch (Exception ex) {
            fail("Can't load data!");
        }

        selectedMobo = new ModelMOBO("ASRock", "90-MXGZU0-A0UAYZ", "H110M-HDV", "Micro ATX", "1151", "H110",
                "DDR4", 32, 2, 2400, "2x PCI Express x1,1x PCI Express x16, 4x SATA III, 1x HDMI, 1x DVI-D, 1x PS/2, 3x audio connector, 4x USB 2.0, 2x USB 3.0, 1x D-Sub (VGA), 1x RJ-45",
                "Realtek ALC887", 220, "images/moboImages/big/", "images/moboImages/small/");

        caseNames.addAll(dlaf.getCaseNames(selectedMobo, caseList));
        if (caseNames.size() == 0) {
            fail("Case names list is empty.");
        }

        for (int i = 0; i < caseNames.size(); i++) {
            String caseName = caseList.get(i).getBrand() + " " + caseList.get(i).getName();
            for (int j = 0; j < caseList.size(); j++) {
                if (caseName.equals(caseList.get(j).getBrand() + " " + caseList.get(j).getName())) {
                    if (caseList.get(j).getFormFactor().contains(selectedMobo.getFormFactor()) == false) {
                        fail("MOBO has wrong socket! Invalid method.");
                    } else {
                        System.out.println("Case number " + (j + 1) + ": form factor - ok");
                    }
                }
            }
        }
        System.out.println("--------------------  " +
                "\nAll Cases have proper form factor." +
                "\nMethod is OK.");
    }
}