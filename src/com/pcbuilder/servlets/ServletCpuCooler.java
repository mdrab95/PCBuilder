package com.pcbuilder.servlets;

import com.pcbuilder.model.ModelCPUCooler;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * CPU Cooler servlet
 */
@WebServlet (name="servletcpucooler", urlPatterns = {"/cpucooler"})
public class ServletCpuCooler extends HttpServlet {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelCPUCooler> cpuCoolerList = FXCollections.observableArrayList();
    ObservableList<ModelCPUCooler> cpuCoolerSearchedList = FXCollections.observableArrayList();

    /**
     * Controller - load item list
     */
    public ServletCpuCooler(){
        cpuCoolerList.addAll(dlaf.cpuCoolerDataLoader());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        cpuCoolerSearchedList.clear();
        if(request.getParameter("cpuCoolerSearchButton")!= null){
            String search = request.getParameter("cpuCoolerSearchText");
            if (search == "") {
                cpuCoolerSearchedList.addAll(cpuCoolerList);
            }
            else {
                cpuCoolerSearchedList.addAll(filterCpuCoolerList(search));
            }
        }
        else {
            cpuCoolerSearchedList.addAll(cpuCoolerList);
        }
        request.setAttribute("cpuCoolerList", cpuCoolerSearchedList);
        request.getRequestDispatcher("/cpucooler.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("cpuCoolerList", cpuCoolerList);
        request.getRequestDispatcher("/cpucooler.jsp").forward(request, response);
    }

    /**
     * Create list of items based on searched value
     * @param searchedValue searched value
     * @return list of items
     */
    protected ObservableList<ModelCPUCooler> filterCpuCoolerList(String searchedValue) {
        ObservableList<ModelCPUCooler> cpuCoolerSearchedList = FXCollections.observableArrayList();
        for (ModelCPUCooler item : cpuCoolerList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getManufacturerCode() + " " + item.getBrand()
                    + " " + item.getManufacturerCode();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                cpuCoolerSearchedList.add(item);
            }
        }
        return cpuCoolerSearchedList;
    }
}

