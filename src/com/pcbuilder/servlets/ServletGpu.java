package com.pcbuilder.servlets;

import com.pcbuilder.model.ModelGPU;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * CPU servlet
 */
@WebServlet (name="servletgpu", urlPatterns = {"/gpu"})
public class ServletGpu extends HttpServlet {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelGPU> gpuList = FXCollections.observableArrayList();
    ObservableList<ModelGPU> gpuSearchedList = FXCollections.observableArrayList();

    /**
     * Controller - load item list
     */
    public ServletGpu(){
        gpuList.addAll(dlaf.gpuDataLoader());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        gpuSearchedList.clear();
        if(request.getParameter("gpuSearchButton")!= null){
            String search = request.getParameter("gpuSearchText");
            if (search == "") {
                gpuSearchedList.addAll(gpuList);
            }
            else {
                gpuSearchedList.addAll(filterGpuList(search));
            }
        }
        else {
            gpuSearchedList.addAll(gpuList);
        }
        request.setAttribute("gpuList", gpuSearchedList);
        request.getRequestDispatcher("/gpu.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("gpuList", gpuList);
        request.getRequestDispatcher("/gpu.jsp").forward(request, response);
    }

    /**
     * Create list of items based on searched value
     * @param searchedValue searched value
     * @return list of items
     */
    protected ObservableList<ModelGPU> filterGpuList(String searchedValue) {
        ObservableList<ModelGPU> gpuSearchedList = FXCollections.observableArrayList();
        for (ModelGPU item : gpuList) {
            String itemData = item.getManufacturer() + " " + item.getseries() + " " + item.getName() + " " + item.getManufacturer()
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                gpuSearchedList.add(item);
            }
        }
        return gpuSearchedList;
    }
}

