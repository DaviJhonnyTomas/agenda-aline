/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Procedimento;
import agendaalineweb.models.ProcedimentoModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Utilizador
 */
@WebServlet(name = "CadastrarProcedimento", urlPatterns = {"/cadastrar-procedimento"})
public class CadastrarProcedimento extends HttpServlet {

    
    

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
            ProcedimentoModel procedimentoModel = new ProcedimentoModel();
            ArrayList<Procedimento> procedimentos = procedimentoModel.selectAll();
            String caminhoContexto = request.getContextPath();
            request.setAttribute("caminhoContexto", caminhoContexto);
            request.setAttribute("procedimentos", procedimentos);
            request.getRequestDispatcher("WEB-INF/pageProcedimentos.jsp").forward(request, response);
        
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
        String nome = request.getParameter("nome");
        String duracao = request.getParameter("duracao");
        String valor = request.getParameter("valor");
        Procedimento procedimento = new Procedimento(nome, duracao, Double.parseDouble(valor));
        ProcedimentoModel procedimentoModel = new ProcedimentoModel();
        procedimentoModel.insert(procedimento);
        response.sendRedirect("cadastrar-procedimento");
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
