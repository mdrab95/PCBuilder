package com.pcbuilder.servlets;

import com.pcbuilder.model.ModelRAM;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * RAM servlet
 */
@WebServlet (name="servletram", urlPatterns = {"/ram"})
public class ServletRam extends HttpServlet {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelRAM> ramList = FXCollections.observableArrayList();
    ObservableList<ModelRAM> ramSearchedList = FXCollections.observableArrayList();

    /**
     * Controller - load item list
     */
    public ServletRam(){
        ramList.addAll(dlaf.ramDataLoader());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ramSearchedList.clear();
        if(request.getParameter("ramSearchButton")!= null){
            String search = request.getParameter("ramSearchText");
            if (search == "") {
                ramSearchedList.addAll(ramList);
            }
            else {
                ramSearchedList.addAll(filterRamList(search));
            }
        }
        else {
            ramSearchedList.addAll(ramList);
        }
        request.setAttribute("ramList", ramSearchedList);
        request.getRequestDispatcher("/ram.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("ramList", ramList);
        request.getRequestDispatcher("/ram.jsp").forward(request, response);
    }

    /**
     * Create list of items based on searched value
     * @param searchedValue searched value
     * @return list of items
     */
    protected ObservableList<ModelRAM> filterRamList(String searchedValue) {
        ObservableList<ModelRAM> ramSearchedList = FXCollections.observableArrayList();
        for (ModelRAM item : ramList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getStandard() + " " + item.getMemorySize() + "GB "
                    + item.getBrand() + " " + item.getName() + " " + item.getMemorySize() + "GB " + item.getMemoryClock() + "MHz "
                    + item.getBrand() + " " + item.getMemorySize() + "GB " + item.getMemoryClock() + "GB " + item.getStandard()
                    + " " + item.getSerialNumber();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                ramSearchedList.add(item);
            }
        }
        return ramSearchedList;
    }
}

