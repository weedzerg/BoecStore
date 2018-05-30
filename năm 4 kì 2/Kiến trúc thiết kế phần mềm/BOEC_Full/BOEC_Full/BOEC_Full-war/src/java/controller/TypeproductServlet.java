/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitiesProduct.Typeproduct;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionProduct.TypeproductFacadeLocal;

/**
 *
 * @author nguye
 */
public class TypeproductServlet extends HttpServlet {

    @EJB
    private TypeproductFacadeLocal typeproductFacade;

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        long idtypeProduct = Long.parseLong(request.getParameter("idtypeProduct"));
        String nameType = request.getParameter("nameType");
        String operation = request.getParameter("operation");

        Typeproduct student = new Typeproduct(idtypeProduct, nameType);

        if (operation.equalsIgnoreCase("Add")) {
            typeproductFacade.create(student);
            request.setAttribute("student", student);
            response.sendRedirect("thongbao.jsp");
        } else if (operation.equalsIgnoreCase("Edit")) {
            typeproductFacade.edit(student);
            Typeproduct searchedStudent = typeproductFacade.find(idtypeProduct);
            request.setAttribute("student", searchedStudent);
            response.sendRedirect("thongbao.jsp");
        } else if (operation.equalsIgnoreCase("Delete")) {
            typeproductFacade.remove(student);
             response.sendRedirect("thongbao.jsp");
        } else if (operation.equalsIgnoreCase("Search")) {
            Typeproduct searchedStudent = typeproductFacade.find(idtypeProduct);
            request.setAttribute("student", searchedStudent);
             RequestDispatcher view = request.getRequestDispatcher("/searchview1.jsp");
            view.forward(request, response);
        }
      //  request.getRequestDispatcher("typedetail.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
