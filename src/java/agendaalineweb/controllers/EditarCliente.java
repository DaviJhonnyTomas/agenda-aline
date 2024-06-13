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


@WebServlet(name = "EditarCliente", urlPatterns = {"/editar-cliente"})
public class EditarCliente extends HttpServlet {

    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        request.setAttribute("modal", "modalEditarCliente");
        request.getRequestDispatcher("WEB-INF/pageCliente.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        Cliente cliente = new Cliente(Integer.parseInt(id), nome, telefone, email);
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.updateById(cliente);// CLiente ja editado
        String caminhoContexto = request.getContextPath();
        response.sendRedirect(caminhoContexto + "/cadastrar-cliente");// Retorno para a pagina de cadastro(tabela vbisuaçizacao clientes)
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
