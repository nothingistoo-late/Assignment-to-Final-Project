package Controller;

import Products.ProductsDAO;
import Products.ProductsDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchPriceController", urlPatterns = {"/SearchPriceController"})
public class SearchPriceController extends HttpServlet {
    private static final String ERROR = "shop.jsp";
    private static final String SUCCESS = "shop.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String minPriceStr = request.getParameter("minPrice");
            String maxPriceStr = request.getParameter("maxPrice");

            if (minPriceStr != null && !minPriceStr.isEmpty() && maxPriceStr != null && !maxPriceStr.isEmpty()) {
                float minPrice = Float.parseFloat(minPriceStr);
                float maxPrice = Float.parseFloat(maxPriceStr);

                ProductsDAO dao = new ProductsDAO(); 
                List<ProductsDTO> productsList = dao.getAllByPriceRange(minPrice, maxPrice);
                if (productsList.size() > 0) {
                    request.setAttribute("Products_List", productsList);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "No products found in the given price range!!!");
                }
            } else {
                request.setAttribute("MESSAGE", "Please enter valid price values!!!");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("MESSAGE", "Invalid price format!!!");
            log("Error at SearchPriceController: " + e.toString());
        } catch (Exception e) {
            request.setAttribute("MESSAGE", "An error occurred while searching for products!!!");
            log("Error at SearchPriceController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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