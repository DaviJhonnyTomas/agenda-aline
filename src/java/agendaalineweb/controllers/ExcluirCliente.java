/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Cliente;
import agendaalineweb.models.AgendamentoModel;
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
@WebServlet(name = "ExcluirCliente", urlPatterns = {"/excluir-cliente"})
public class ExcluirCliente extends HttpServlet {
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        int idConvertido = Integer.parseInt(id);
        ClienteModel modelCliente = new ClienteModel();
         String mensagem = null;
         AgendamentoModel agendamentoModel = new AgendamentoModel();
        boolean clienteTemAgendamento = agendamentoModel.clienteTemAgendamento(idConvertido);
        if(clienteTemAgendamento){
            mensagem = "O cliente selecionado não pode ser excluido. Há agendamentos associados a este cliente";
        }else{
            modelCliente.deleteById(idConvertido);
        }
        ArrayList<Cliente> clientes = modelCliente.selectAll();
        request.setAttribute("clientes", clientes);
        request.setAttribute("mensagemErro", mensagem);
        String caminhoContexto = request.getContextPath();
        request.setAttribute("caminhoContexto", caminhoContexto);
        request.getRequestDispatcher("WEB-INF/pageCliente.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
