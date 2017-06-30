package com.pcbuilder.servlets;

import com.pcbuilder.model.ModelCPU;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * CPU servlet
 */
@WebServlet (name="servletcpu", urlPatterns = {"/cpu"})
public class ServletCpu extends HttpServlet {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelCPU> cpuList = FXCollections.observableArrayList();
    ObservableList<ModelCPU> cpuSearchedList = FXCollections.observableArrayList();

    /**
     * Controller - load item list
     */
    public ServletCpu(){
        cpuList.addAll(dlaf.cpuDataLoader());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        cpuSearchedList.clear();
        if(request.getParameter("cpuSearchButton")!= null){
            String search = request.getParameter("cpuSearchText");
            if (search == "") {
                cpuSearchedList.addAll(cpuList);
            }
            else {
                cpuSearchedList.addAll(filterCpuList(search));
            }
        }
        else {
            cpuSearchedList.addAll(cpuList);
        }
        request.setAttribute("cpuList", cpuSearchedList);
        request.getRequestDispatcher("/cpu.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("cpuList", cpuList);
        request.getRequestDispatcher("/cpu.jsp").forward(request, response);
    }

    /**
     * Create list of items based on searched value
     * @param searchedValue searched value
     * @return list of items
     */
    protected ObservableList<ModelCPU> filterCpuList(String searchedValue) {
        ObservableList<ModelCPU> cpuSearchedList = FXCollections.observableArrayList();
        for (ModelCPU item : cpuList) {
            String itemData = item.getBrand() + " " + item.getFamily() + " " + item.getName() + " " + item.getBrand()
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                cpuSearchedList.add(item);
            }
        }
        return cpuSearchedList;
    }
}

