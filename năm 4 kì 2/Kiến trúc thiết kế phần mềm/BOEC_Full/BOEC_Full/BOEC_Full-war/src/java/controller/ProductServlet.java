/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitiesProduct.Product;
import static entitiesProduct.Product_.typeProductIdtypeProduct;
import entitiesProduct.Typeproduct;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionProduct.ProductFacadeLocal;

/**
 *
 * @author nguye
 */
public class ProductServlet extends HttpServlet {

    @EJB
    private ProductFacadeLocal productFacade;

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        long food_id = Long.parseLong(request.getParameter("food_id"));
        String category_id = request.getParameter("category_id");
        String food_name = request.getParameter("food_name");
         String food_image = request.getParameter("food_image");
        double food_price = Double.parseDouble(request.getParameter("food_price"));
        String food_description = request.getParameter("food_description");
        String operation = request.getParameter("operation");
         Product student = new Product(food_id, food_name, food_image, food_description, food_price, (Typeproduct) typeProductIdtypeProduct);

        if (operation.equalsIgnoreCase("Add")) {
            productFacade.create(student);
            request.setAttribute("student", student);
        } else if (operation.equalsIgnoreCase("Edit")) {
            productFacade.edit(student);
            Product searchedStudent = productFacade.find(food_id);
            request.setAttribute("student", searchedStudent);
        } else if (operation.equalsIgnoreCase("Delete")) {
            productFacade.remove(student);
        } 
        request.getRequestDispatcher("insert_product.jsp").forward(request, response);
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
