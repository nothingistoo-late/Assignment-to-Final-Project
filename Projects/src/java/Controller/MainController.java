package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@MultipartConfig
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String HOME = "login.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";

    private static final String SEARCH_INVOICE = "SearchInvoice";
    private static final String SEARCH_INVOICE_CONTROLLER = "SearchInvoiceController";

    private static final String SEARCH_USER = "SearchUser";
    private static final String SEARCH_USER_CONTROLLER = "SearchUserController";

    private static final String SEARCH_CATEGORIES = "SearchCategories";
    private static final String SEARCH_CATEGORIES_CONTROLLER = "SearchCategoriesController";

    private static final String SEARCH_CATEGORIES_2 = "SearchCategories2";
    private static final String SEARCH_CATEGORIES_2_CONTROLLER = "SearchCategoriesController2";

    private static final String SEARCH_PRICE = "SearchPrice";
    private static final String SEARCH_PRICE_CONTROLLER = "SearchPriceController";

    private static final String UPDATE_USER = "Update User";
    private static final String UPDATE_USER_CONTROLLER = "UpdateUserController";

    private static final String UPDATE_USER_2 = "Update User2";
    private static final String UPDATE_USER_2_CONTROLLER = "UpdateUserController2";

    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";

    private static final String DELETE_USER = "DeleteUser";
    private static final String DELETE_USER_CONTROLLER = "DeleteUserController";

    private static final String UPDATE = "UpdateProducts";
    private static final String UPDATE_CONTROLLER = "UpdateProductsController";

    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";

    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";

    private static final String ADD = "Add";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartController";

    private static final String VIEW = "View";
    private static final String VIEW_CART = "viewCart.jsp";

    private static final String CHANGE = "Change";
    private static final String CHANGE_CONTROLLER = "ChangeController";

    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";

    private static final String SEARCH_CART = "SearchCart";
    private static final String SEARCH_CART_CONTROLLER = "SearchCartController";

    private static final String REGISTER = "Register";
    private static final String REGISTER_CONTROLLER = "CreateUserController";

    private static final String CREATE_INVOICE = "CreateInvoice";
    private static final String CREATE_INVOICE_CONTROLLER = "CreateInvoiceController";

    private static final String DELETE_INVOICE = "DeleteInvoice";
    private static final String DELETE_INVOICE_CONTROLLER = "DeleteInvoiceController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {

            String action = request.getParameter("action");

            if (null == action) {
                url = HOME;
            } else {
                switch (action) {
                    case LOGIN:
                        url = LOGIN_CONTROLLER;
                        break;
                    case SEARCH:
                        url = SEARCH_CONTROLLER;
                        break;
                    case SEARCH_USER:
                        url = SEARCH_USER_CONTROLLER;
                        break;
                    case SEARCH_INVOICE:
                        url = SEARCH_INVOICE_CONTROLLER;
                        break;
                    case DELETE:
                        url = DELETE_CONTROLLER;
                        break;
                    case DELETE_USER:
                        url = DELETE_USER_CONTROLLER;
                        break;
                    case UPDATE:
                        url = UPDATE_CONTROLLER;
                        break;
                    case LOGOUT:
                        url = LOGOUT_CONTROLLER;
                        break;
                    case CREATE:
                        url = CREATE_CONTROLLER;
                        break;
                    case ADD:
                        url = ADD_TO_CART_CONTROLLER;
                        break;
                    case VIEW:
                        url = VIEW_CART;
                        break;
                    case CHANGE:
                        url = CHANGE_CONTROLLER;
                        break;
                    case REMOVE:
                        url = REMOVE_CONTROLLER;
                        break;
                    case SEARCH_CART:
                        url = SEARCH_CART_CONTROLLER;
                        break;
                    case REGISTER:
                        url = REGISTER_CONTROLLER;
                        break;
                    case UPDATE_USER:
                        url = UPDATE_USER_CONTROLLER;
                        break;
                    case UPDATE_USER_2:
                        url = UPDATE_USER_2_CONTROLLER;
                        break;
                    case SEARCH_CATEGORIES:
                        url = SEARCH_CATEGORIES_CONTROLLER;
                        break;
                    case SEARCH_CATEGORIES_2:
                        url = SEARCH_CATEGORIES_2_CONTROLLER;
                        break;
                    case SEARCH_PRICE:
                        url = SEARCH_PRICE_CONTROLLER;
                        break;
                    case CREATE_INVOICE:
                        url = CREATE_INVOICE_CONTROLLER;
                        break;
                    case DELETE_INVOICE:
                        url = DELETE_INVOICE_CONTROLLER;
                        break;
                    default:
                        request.setAttribute("message", "Your action is not supported");
                        break;
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
