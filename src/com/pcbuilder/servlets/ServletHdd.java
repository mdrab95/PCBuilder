package com.pcbuilder.servlets;

import com.pcbuilder.model.ModelDataLoaderAndFilter;
import com.pcbuilder.model.ModelHDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * HDD servlet
 */
@WebServlet (name="servlethdd", urlPatterns = {"/hdd"})
public class ServletHdd extends HttpServlet {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelHDD> hddList = FXCollections.observableArrayList();
    ObservableList<ModelHDD> hddSearchedList = FXCollections.observableArrayList();

    /**
     * Controller - load item list
     */
    public ServletHdd(){
        hddList.addAll(dlaf.hddDataLoader());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        hddSearchedList.clear();
        if(request.getParameter("hddSearchButton")!= null){
            String search = request.getParameter("hddSearchText");
            if (search == "") {
                hddSearchedList.addAll(hddList);
            }
            else {
                hddSearchedList.addAll(filterHddList(search));
            }
        }
        else {
            hddSearchedList.addAll(hddList);
        }
        request.setAttribute("hddList", hddSearchedList);
        request.getRequestDispatcher("/hdd.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("hddList", hddList);
        request.getRequestDispatcher("/hdd.jsp").forward(request, response);
    }

    /**
     * Create list of items based on searched value
     * @param searchedValue searched value
     * @return list of items
     */
    protected ObservableList<ModelHDD> filterHddList(String searchedValue) {
        ObservableList<ModelHDD> hddSearchedList = FXCollections.observableArrayList();
        for (ModelHDD item : hddList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getCapacity() + "GB " + item.getBrand() + " " + item.getCapacity() + "GB"
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                hddSearchedList.add(item);
            }
        }
        return hddSearchedList;
    }
}

