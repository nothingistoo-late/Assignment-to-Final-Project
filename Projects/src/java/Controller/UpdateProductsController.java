package Controller;

import Products.ProductsDAO;
import Products.ProductsDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UpdateProductsController", urlPatterns = {"/UpdateProductsController"})
public class UpdateProductsController extends HttpServlet {
    private static final String ERROR = "products.jsp";
    private static final String SUCCESS = "products.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String idProduct = request.getParameter("idProducts");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String type = request.getParameter("type");
            String image = request.getParameter("image");
            float price = Float.parseFloat(request.getParameter("price"));
            int status = 1; // assuming status is always 1 for active

            ProductsDTO product = new ProductsDTO(idProduct, name, description, type, image, price, status);
            ProductsDAO dao = new ProductsDAO();
            boolean checkUpdate = dao.updateProducts(product);
            if (checkUpdate) {
                url = SUCCESS;
                request.setAttribute("message", "Successfully updated!!!");
            } else {
                request.setAttribute("message", "Fail to update!!!");
            }

        } catch (Exception e) {
            log("Error at UpdateProductsController: " + e.toString());
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
