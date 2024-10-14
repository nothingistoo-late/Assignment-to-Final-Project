package Controller;

import Invoice.InvoiceDAO;
import Invoice.InvoiceDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date; // Correct import for Date

@WebServlet(name = "CreateInvoiceController", urlPatterns = {"/CreateInvoiceController"})
public class CreateInvoiceController extends HttpServlet {

    private static final String ERROR="checkout.jsp";
    private static final String SUCCESS="thankyou.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= ERROR;
        InvoiceDAO dao= new InvoiceDAO();
        try {
            String invId= request.getParameter("invId");
            String userID= request.getParameter("userID");
            String dateBuy = request.getParameter("dateBuy");
            
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateBuy = formatter.format(date);
            
            String gmail= request.getParameter("gmail");
            String address= request.getParameter("address");
            float total= Float.parseFloat(request.getParameter("total"));
            
            InvoiceDTO invoice= new InvoiceDTO(invId, userID, dateBuy, gmail, address, total);
            boolean checkInsert= dao.createInvoice(invoice);
            if(checkInsert){
                url= SUCCESS;
            }
        } catch (Exception e) {
            log("Error at CreateInvoiceController: "+ e.toString());
        }finally{
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
