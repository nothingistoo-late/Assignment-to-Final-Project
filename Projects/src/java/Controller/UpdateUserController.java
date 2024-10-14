package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Users.UserDAO;
import Users.UserDTO;
import Users.UserError;


@WebServlet(name = "UpdateUserController", urlPatterns = {"/UpdateUserController"})
public class UpdateUserController extends HttpServlet {

    private static final String ERROR = "update.jsp";
    private static final String SUCCESS = "update.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String url = ERROR;
    UserError userError= new UserError();
    try {
        String userId = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String roleID = request.getParameter("roleID");
        String gmail = request.getParameter("gmail");
        String address = request.getParameter("address");

        UserDTO user= new UserDTO(userId, fullName, password, roleID, gmail, address,1);
        UserDAO dao = new UserDAO();
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        if(loginUser.getUserId().equals(userId)){
            loginUser.setFullName(fullName);
            loginUser.setPassword(password);
            
            loginUser.setGmail(gmail);
            loginUser.setAddress(address);
            session.setAttribute("LOGIN_USER", loginUser);
        }
        boolean checkUpdate = dao.update(user);
        if (checkUpdate) {
            url = SUCCESS;
            request.setAttribute("message", "Update success!");
        } else {
            request.setAttribute("message", "Update fail!");
        }

    } catch (Exception e) {
        log("Error at UpdateController: " + e.toString());
        if(e.toString().contains("duplicate")){
            userError.setUserID("Duplicate ID!");
            request.setAttribute("USER_ERROR", userError);
        }
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
