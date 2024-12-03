/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Procedimento;
import agendaalineweb.models.ClienteModel;
import agendaalineweb.models.ProcedimentoModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;


@WebServlet(name = "FiltrarProcedimento", urlPatterns = {"/filtrar-procedimento"})
public class FiltrarProcedimento extends HttpServlet {

    
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
         String nomeProcedimento = request.getParameter("procedimento");
       
        ArrayList<Procedimento> procedimentos = new ArrayList();
        ProcedimentoModel procedimentoModel = new ProcedimentoModel();
       if(!nomeProcedimento.isEmpty()){ //! ao contrário da condição (se o nomeCliente não for vazio)
           procedimentos = procedimentoModel.selectByNome(nomeProcedimento);
       }
       String caminhoContexto = request.getContextPath();
       
       request.setAttribute("caminhoContexto", caminhoContexto);
       request.setAttribute("procedimentos", procedimentos);
       request.getRequestDispatcher("WEB-INF/pageProcedimentos.jsp").forward(request, response);
      
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
