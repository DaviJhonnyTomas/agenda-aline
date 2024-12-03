/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Agendamento;
import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Procedimento;
import agendaalineweb.entities.Usuario;
import agendaalineweb.models.AgendamentoModel;
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

/**
 *
 * @author Utilizador
 */
@WebServlet(name = "ExcluirAgendamento", urlPatterns = {"/excluir-agendamento"})
public class ExcluirAgendamento extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String mensagem = null;
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (usuario != null) {

            int id = Integer.parseInt(request.getParameter("id"));
            AgendamentoModel modelAgendamento = new AgendamentoModel();

            modelAgendamento.deleteById(id);
            
           

        }else{
            mensagem = "Você deve estar logado para fazer esta operação.";
        }
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        ArrayList<Agendamento> agendamentos = agendamentoModel.selectAll(usuario.getId());

        ProcedimentoModel modelProcedimento = new ProcedimentoModel();
        ClienteModel modelCliente = new ClienteModel();
        String caminhoContexto = request.getContextPath();
       
        request.setAttribute("agendamentos", agendamentos);
        ArrayList<Procedimento> procedimentos = modelProcedimento.selectAll(usuario.getId());
        request.setAttribute("procedimentos", procedimentos);
        ArrayList<Cliente> clientes = modelCliente.selectAll(usuario.getIdNegocio());
        request.setAttribute("clientes", clientes);
       
        request.setAttribute("caminhoContexto", caminhoContexto);
        request.setAttribute("mensagemErro", mensagem);
        
        request.getRequestDispatcher("WEB-INF/pageAgendamentos.jsp").forward(request, response);
        
    }

}
