/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Cliente;
import agendaalineweb.models.ClienteModel;
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
@WebServlet(name = "FiltrarCliente", urlPatterns = {"/filtrar-cliente"})
public class FiltrarCliente extends HttpServlet {

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
       String nomeCliente = request.getParameter("cliente");
       
        ArrayList<Cliente> clientes = new ArrayList();
        ClienteModel clienteModel = new ClienteModel();
       if(!nomeCliente.isEmpty()){ //! ao contrário da condição (se o nomeCliente não for vazio)
           clientes = clienteModel.selectByNome(nomeCliente);
       }
       String caminhoContexto = request.getContextPath();
       
       request.setAttribute("caminhoContexto", caminhoContexto);
       request.setAttribute("clientes", clientes);
       request.getRequestDispatcher("WEB-INF/pageCliente.jsp").forward(request, response);
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
