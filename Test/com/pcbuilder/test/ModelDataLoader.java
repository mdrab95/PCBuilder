package com.pcbuilder.test;
import com.pcbuilder.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Ace on 28.06.2017.
 */
class ModelDataLoader {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();

    ObservableList <ModelCPU> cpuList = FXCollections.observableArrayList();
    ObservableList <ModelCPUCooler> cpuCoolerList = FXCollections.observableArrayList();
    ObservableList <ModelMOBO> moboList = FXCollections.observableArrayList();
    ObservableList <ModelGPU> gpuList = FXCollections.observableArrayList();
    ObservableList <ModelRAM> ramList = FXCollections.observableArrayList();
    ObservableList <ModelHDD> hddList = FXCollections.observableArrayList();
    ObservableList <ModelSSD> ssdList = FXCollections.observableArrayList();
    ObservableList<ModelPSU> psuList = FXCollections.observableArrayList();
    ObservableList <ModelCase> caseList = FXCollections.observableArrayList();

    @Test
    void cpuDataLoaderTest(){
        try {
            cpuList.addAll(dlaf.cpuDataLoader());
        } catch (Exception ex) {
            fail ("Can't load cpu data");
        }
        if (cpuList.size() == 0)
            fail ("CPU data file is empty or does not exist");
    }

    @Test
    void cpuCoolerDataLoaderTest(){
        try {
            cpuCoolerList.addAll(dlaf.cpuCoolerDataLoader());
        } catch (Exception ex) {
            fail ("Can't load cpu cooler data");
        }
        if (cpuCoolerList.size() == 0)
            fail ("Cpu cooler data file is empty or does not exist");
    }

    @Test
    void MoboDataLoaderTest(){
        try {
            moboList.addAll(dlaf.moboDataLoader());
        } catch (Exception ex) {
            fail ("Can't load mobo data");
        }
        if (moboList.size() == 0)
            fail ("Mobo data file is empty or does not exist");
    }

    @Test
    void GpuDataLoaderTest(){
        try {
            gpuList.addAll(dlaf.gpuDataLoader());
        } catch (Exception ex) {
            fail ("Can't load gpu data");
        }
        if (gpuList.size() == 0)
            fail ("GPU data file is empty or does not exist");
    }

    @Test
    void RamDataLoaderTest(){
        try {
            ramList.addAll(dlaf.ramDataLoader());
        } catch (Exception ex) {
            fail ("Can't load ram data");
        }
        if (ramList.size() == 0)
            fail ("RAM data file is empty or does not exist");
    }

    @Test
    void SsdDataLoaderTest(){
        try {
            ssdList.addAll(dlaf.ssdDataLoader());
        } catch (Exception ex) {
            fail ("Can't load ssd data");
        }
        if (ssdList.size() == 0)
            fail ("SSD data file is empty or does not exist");
    }

    @Test
    void HddDataLoaderTest(){
        try {
            hddList.addAll(dlaf.hddDataLoader());
        } catch (Exception ex) {
            fail ("Can't load hdd data");
        }
        if (hddList.size() == 0)
            fail ("HDD data file is empty or does not exist");
    }

    @Test
    void PsuDataLoaderTest(){
        try {
            psuList.addAll(dlaf.psuDataLoader());
        } catch (Exception ex) {
            fail ("Can't load psu data");
        }
        if (psuList.size() == 0)
            fail ("PSU data file is empty or does not exist");
    }

    @Test
    void caseDataLoaderTest(){
        try {
            caseList.addAll(dlaf.caseDataLoader());
        } catch (Exception ex) {
            fail ("Can't load case data");
        }
        if (caseList.size() == 0)
            fail ("Case data file is empty or does not exist");
    }
}