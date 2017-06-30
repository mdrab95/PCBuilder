package com.pcbuilder.servlets;

import com.pcbuilder.model.ModelSSD;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * SSD servlet
 */
@WebServlet (name="servletssd", urlPatterns = {"/ssd"})
public class ServletSsd extends HttpServlet {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelSSD> ssdList = FXCollections.observableArrayList();
    ObservableList<ModelSSD> ssdSearchedList = FXCollections.observableArrayList();

    /**
     * Controller - load item list
     */
    public ServletSsd (){
        ssdList.addAll(dlaf.ssdDataLoader());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ssdSearchedList.clear();
        if(request.getParameter("ssdSearchButton")!= null){
            String search = request.getParameter("ssdSearchText");
            if (search == "") {
                ssdSearchedList.addAll(ssdList);
            }
            else {
                ssdSearchedList.addAll(filterSsdList(search));
            }
        }
        else {
            ssdSearchedList.addAll(ssdList);
        }
        request.setAttribute("ssdList", ssdSearchedList);
        request.getRequestDispatcher("/ssd.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("ssdList", ssdList);
        request.getRequestDispatcher("/ssd.jsp").forward(request, response);
    }

    /**
     * Create list of items based on searched value
     * @param searchedValue searched value
     * @return list of items
     */
    protected ObservableList<ModelSSD> filterSsdList(String searchedValue) {
        ObservableList<ModelSSD> ssdSearchedList = FXCollections.observableArrayList();
        for (ModelSSD item : ssdList) {
            String itemData = item.getBrand() + " " + item.getFormFactor() + " " + item.getName() + " " + item.getCapacity() + "GB "
                    + item.getBrand() + " " + item.getName() + " " + item.getFormFactor() + " " + item.getCapacity() + "GB "
                    + item.getBrand() + " " + item.getFormFactor() + " " + item.getBrand() + " " + item.getCapacity() + "GB "
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                ssdSearchedList.add(item);
            }
        }
        return ssdSearchedList;
    }
}

