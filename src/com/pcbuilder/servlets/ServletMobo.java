package com.pcbuilder.servlets;

import com.pcbuilder.model.ModelMOBO;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * MOBO servlet
 */
@WebServlet (name="servletmobo", urlPatterns = {"/mobo"})
public class ServletMobo extends HttpServlet {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelMOBO> moboList = FXCollections.observableArrayList();
    ObservableList<ModelMOBO> moboSearchedList = FXCollections.observableArrayList();

    /**
     * Controller - load item list
     */
    public ServletMobo(){
        moboList.addAll(dlaf.moboDataLoader());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        moboSearchedList.clear();
        if(request.getParameter("moboSearchButton")!= null){
            String search = request.getParameter("moboSearchText");
            if (search == "") {
                moboSearchedList.addAll(moboList);
            }
            else {
                moboSearchedList.addAll(filterMoboList(search));
            }
        }
        else {
            moboSearchedList.addAll(moboList);
        }
        request.setAttribute("moboList", moboSearchedList);
        request.getRequestDispatcher("/mobo.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("moboList", moboList);
        request.getRequestDispatcher("/mobo.jsp").forward(request, response);
    }

    /**
     * Create list of items based on searched value
     * @param searchedValue searched value
     * @return list of items
     */
    protected ObservableList<ModelMOBO> filterMoboList(String searchedValue) {
        ObservableList<ModelMOBO> moboSearchedList = FXCollections.observableArrayList();
        for (ModelMOBO item : moboList) {
            String itemData = item.getBrand() + " " + item.getChipset() + " " + item.getName() + " " + item.getBrand()
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                moboSearchedList.add(item);
            }
        }
        return moboSearchedList;
    }
}

