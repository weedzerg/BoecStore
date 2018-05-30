/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitiesOrder.Cart;
import entitiesOrder.Item;
import entitiesProduct.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionProduct.ProductFacadeLocal;

/**
 *
 * @author nguye
 */
public class CartServlet extends HttpServlet {

    @EJB
    private ProductFacadeLocal productFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        String productID = request.getParameter("idProduct");
        Cart cart = (Cart) session.getAttribute("cart");
        
        try {
            Long idProduct = Long.parseLong(productID);
            Product product = productFacade.find(idProduct);
            switch (command) {
                case "plus":
                    if (cart.getCartItems().containsKey(idProduct)) {
                        cart.plusToCart(idProduct, new Item(product,
                                cart.getCartItems().get(idProduct).getQuantity()));
                    } else {
                        cart.plusToCart(idProduct, new Item(product, 1));
                    }
                    break;
                case "remove":
                    cart.removeToCart(idProduct);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("http://localhost:8080/BOEC_Full-war/homepage.jsp");
        }
        session.setAttribute("cart", cart);
        response.sendRedirect("http://localhost:8080/BOEC_Full-war/homepage.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
