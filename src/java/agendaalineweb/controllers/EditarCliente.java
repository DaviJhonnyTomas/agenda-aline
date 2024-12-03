/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Usuario;
import agendaalineweb.models.ClienteModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "EditarCliente", urlPatterns = {"/editar-cliente"})
public class EditarCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String idNegocio = request.getParameter("idNegocio");

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario != null) {
            Cliente cliente = new Cliente(Integer.parseInt(id), nome, telefone, email, Integer.parseInt(idNegocio));
            request.setAttribute("cliente", cliente);
            request.setAttribute("modal", "modalEditarCliente");

            ClienteModel clienteModel = new ClienteModel();
            ArrayList<Cliente> clientes = clienteModel.selectAll(usuario.getIdNegocio());
            request.setAttribute("clientes", clientes);

            String caminhoContexto = request.getContextPath();
            request.setAttribute("caminhoContexto", caminhoContexto);

            request.getRequestDispatcher("WEB-INF/pageCliente.jsp").forward(request, response);
        }else{
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");

        String caminhoContexto = request.getContextPath();
        ClienteModel clienteModel = new ClienteModel();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        String mensagem = null;
        if (usuario == null) {
            mensagem = "Você deve fazer login para realizar esta operação.";
        } else if (!id.isEmpty() && !nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty()) {
            Cliente cliente = new Cliente(Integer.parseInt(id), nome, telefone, email);

            clienteModel.updateById(cliente);// CLiente ja editado
            ArrayList<Cliente> clientes = clienteModel.selectAll(usuario.getIdNegocio());
             request.setAttribute("clientes", clientes);
            
        } else {
            mensagem = "Todos os campos devem ser preenchidos.";
        }
        request.setAttribute("mensagemErro", mensagem);
       
        request.setAttribute("caminhoContexto", caminhoContexto);

        request.getRequestDispatcher("WEB-INF/pageCliente.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
