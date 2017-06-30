package com.pcbuilder.servlets;

import com.pcbuilder.model.ModelCase;
import com.pcbuilder.model.ModelDataLoaderAndFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * CPU servlet
 */
@WebServlet (name="servletcase", urlPatterns = {"/case"})
public class ServletCase extends HttpServlet {
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    ObservableList<ModelCase> caseList = FXCollections.observableArrayList();
    ObservableList<ModelCase> caseSearchedList = FXCollections.observableArrayList();

    /**
     * Controller - load item list
     */
    public ServletCase(){
        caseList.addAll(dlaf.caseDataLoader());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        caseSearchedList.clear();
        if(request.getParameter("caseSearchButton")!= null){
            String search = request.getParameter("caseSearchText");
            if (search == "") {
                caseSearchedList.addAll(caseList);
            }
            else {
                caseSearchedList.addAll(filterCaseList(search));
            }
        }
        else {
            caseSearchedList.addAll(caseList);
        }
        request.setAttribute("caseList", caseSearchedList);
        request.getRequestDispatcher("/case.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request server request
     * @param response server response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("caseList", caseList);
        request.getRequestDispatcher("/case.jsp").forward(request, response);
    }

    /**
     * Create list of items based on searched value
     * @param searchedValue searched value
     * @return list of items
     */
    protected ObservableList<ModelCase>filterCaseList(String searchedValue) {
        ObservableList<ModelCase>caseSearchedList = FXCollections.observableArrayList();
        for (ModelCase item : caseList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getFormFactor();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                caseSearchedList.add(item);
            }
        }
        return caseSearchedList;
    }
}

