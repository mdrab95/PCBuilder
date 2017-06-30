package com.pcbuilder.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * index servlet
 */
@WebServlet(name = "ServletLastVisit", urlPatterns={"/index"})
public class ServletLastVisit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Cookie[] cookies = request.getCookies();
            String lastVisit = "never";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastVisit")) {
                    lastVisit = cookie.getValue();
                    break;
                }
            }
            Cookie cookie = new Cookie("lastVisit", new java.util.Date().toString());
            response.addCookie(cookie);
            request.setAttribute("lastVisit", lastVisit);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
