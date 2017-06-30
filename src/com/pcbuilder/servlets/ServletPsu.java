package com.pcbuilder.servlets;

import com.pcbuilder.model.ModelPSU;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * PSU servlet
 */
@WebServlet (name="servletpsu", urlPatterns = {"/psu"})
public class ServletPsu extends HttpServlet {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelPSU> psuList = FXCollections.observableArrayList();
    ObservableList<ModelPSU> psuSearchedList = FXCollections.observableArrayList();

    /**
     * Controller - load item list
     */
    public ServletPsu(){
        psuList.addAll(dlaf.psuDataLoader());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        psuSearchedList.clear();
        if(request.getParameter("psuSearchButton")!= null){
            String search = request.getParameter("psuSearchText");
            if (search == "") {
                psuSearchedList.addAll(psuList);
            }
            else {
                psuSearchedList.addAll(filterPsuList(search));
            }
        }
        else {
            psuSearchedList.addAll(psuList);
        }
        request.setAttribute("psuList", psuSearchedList);
        request.getRequestDispatcher("/psu.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("psuList", psuList);
        request.getRequestDispatcher("/psu.jsp").forward(request, response);
    }

    /**
     * Create list of items based on searched value
     * @param searchedValue searched value
     * @return list of items
     */
    protected ObservableList<ModelPSU> filterPsuList(String searchedValue) {
        ObservableList<ModelPSU> psuSearchedList = FXCollections.observableArrayList();
        for (ModelPSU item : psuList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getWattage() + "W " + item.getBrand() + item.getWattage() + "W "
                    + item.getBrand() + " " + item.getName() + " " + item.getWattage() + " " + item.getBrand() + item.getWattage() + " ";
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                psuSearchedList.add(item);
            }
        }
        return psuSearchedList;
    }
}

