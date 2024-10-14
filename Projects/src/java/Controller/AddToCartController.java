package Controller;

import Cart.CartDAO;
import Cart.CartDTO;
import Products.ProductsDAO;
import Products.ProductsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    private static final String ERROR="shop.jsp";
    private static final String SUCCESS="shop.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String url= ERROR;
    try {
        CartDAO dao = new CartDAO();
        String userId = request.getParameter("userId").trim();
        if (userId == null || userId.isEmpty()) {
            ProductsDAO productsDao = new ProductsDAO();
            List<ProductsDTO> productsList = productsDao.getAllProducts();
            request.setAttribute("Products_List", productsList);
            request.setAttribute("message", "Please login to add product.");
            
     
        } else {
            
        String idProducts = request.getParameter("idProducts");
        float price = Float.parseFloat(request.getParameter("price"));
        String invId = request.getParameter("invId");
        
        String image = request.getParameter("image");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float totalPrice = price * quantity;
        String cartId = "B" + new Random().nextInt(10000);
        
        // Check if the product already exists in the cart
        CartDTO existingCart = dao.getCartByProductsId(idProducts, userId);
        if (existingCart != null) {
            // If the product already exists in the cart, update the quantity
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            existingCart.setTotalPrice(price * existingCart.getQuantity());
            boolean check = dao.updateCart(existingCart);
            if (check) {
                request.setAttribute("message", "Updated quantity of item in cart successfully.");
            } else {
                request.setAttribute("message", "Failed to update quantity of item.");
            }
        } else {
            // If the product does not exist in the cart, add a new entry
            boolean check = dao.addToCart(cartId, idProducts, price, totalPrice, quantity, image, userId, invId);
            if (check) {
                url = SUCCESS;
                request.setAttribute("message", "Added successfully!!!");
            } else {
                request.setAttribute("message", "Failed to add to cart!!!");
            }
        }
        }
      

    } catch (Exception e) {
        log("Error at AddToCartController: "+ e.toString());
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
     * @param response servlet response)
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
