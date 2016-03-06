/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "EmailServlet1", urlPatterns = {"/EmailServlet1"})
public class EmailServlet1 extends HttpServlet {

 
    
    @EJB
    public MailSendBean mailSender;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        response.setContentType("text/html;charset=UTF-8");
           String fromEmail = "siri.varma94@gmail.com";
        String username = "siri.varma94@gmail.com";
        String password = "BlueJay_Arling94";
        String toEmail = request.getParameter("email");
        if(request.getParameter("aName")!=null)
        {
        
        String adName = request.getParameter("aName");
        String tos = request.getParameter("type");
        String date = request.getParameter("date");
        String time = request.getParameter("startTime");
   mailSender.sendEmail(fromEmail, username, password, toEmail, adName, tos,date,time);
        }
        else if (request.getParameter("password")!=null)
        {
            String password1 = request.getParameter("password");
            mailSender.sendEmail(fromEmail, username, password,toEmail,password1);
        }
        
     
        
       
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmailServlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<b>Mail Sent Successfully </b>");
             out.println("Click <a href = 'Mail.jsp'>here</a> to go back!!!");
            out.println("</body>");
            out.println("</html>");
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
