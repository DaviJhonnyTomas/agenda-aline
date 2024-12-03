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
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ListagemAgendamentosDoDia", urlPatterns = {"/listagem-agendamentos-dia"})
public class ListagemAgendamentosDoDia extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        if (usuario != null) {
            AgendamentoModel agendamentoModel = new AgendamentoModel();
            ArrayList<Agendamento> agendamentos = agendamentoModel.selectByData(Date.valueOf(LocalDate.now()), usuario.getId());

            ProcedimentoModel modelProcedimento = new ProcedimentoModel();
            ClienteModel modelCliente = new ClienteModel();
            String caminhoContexto = request.getContextPath();
            request.setAttribute("caminhoContexto", caminhoContexto);
            request.setAttribute("agendamentos", agendamentos);
            ArrayList<Procedimento> procedimentos = modelProcedimento.selectAll(usuario.getId());
            request.setAttribute("procedimentos", procedimentos);
            ArrayList<Cliente> clientes = modelCliente.selectAll(usuario.getIdNegocio());
            request.setAttribute("clientes", clientes);

            request.getRequestDispatcher("WEB-INF/agendamentosDoDia.jsp").forward(request, response);

        } else {
            response.sendRedirect("login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
