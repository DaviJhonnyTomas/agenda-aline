/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Agendamento;
import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Procedimento;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@WebServlet(name = "EditarAgendamento", urlPatterns = {"/editar-agendamento"})
public class EditarAgendamento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idAgendamento = request.getParameter("id");
        String idCliente = request.getParameter("idCliente");
        String idProcedimento = request.getParameter("idProcedimento");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");

        //Agendamento agendamento = new Agendamento(Integer.parseInt(idAgendamento), Integer.parseInt(idProcedimento), LocalTime.parse(hora), LocalDate.parse(data), Integer.parseInt(idCliente));
        //request.setAttribute("agendamento", agendamento);
        request.setAttribute("modal", "modalEditarAgendamento");

        AgendamentoModel agendamentoModel = new AgendamentoModel();
        ArrayList<Agendamento> agendamentos = agendamentoModel.selectAll();
        request.setAttribute("agendamentos", agendamentos);

        String caminhoContexto = request.getContextPath();
        request.setAttribute("caminhoContexto", caminhoContexto);
        ProcedimentoModel procedimentoModel = new ProcedimentoModel();
        ArrayList<Procedimento> procedimentos = procedimentoModel.selectAll();
        request.setAttribute("procedimentos", procedimentos);
        ClienteModel clienteModel = new ClienteModel();
        ArrayList<Cliente> clientes = clienteModel.selectAll();
        request.setAttribute("clientes", clientes);
        Procedimento procedimentoSelecionado = procedimentoModel.selectById(Integer.parseInt(idProcedimento));
        request.setAttribute("procedimentoSelecionado", procedimentoSelecionado);
        Cliente clienteSelecionado = clienteModel.selectById(Integer.parseInt(idCliente));
        request.setAttribute("clienteSelecionado", clienteSelecionado);

        request.getRequestDispatcher("WEB-INF/pageAgendamentos.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idAgendamento = request.getParameter("id");
        String idCliente = request.getParameter("idCliente");
        String idProcedimento = request.getParameter("idProcedimento");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        //Agendamento agendamento = new Agendamento(Integer.parseInt(idAgendamento), Integer.parseInt(idProcedimento), LocalTime.parse(hora), LocalDate.parse(data), Integer.parseInt(idCliente));
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        //agendamentoModel.updateById(agendamento);// Agendamento ja editado
        String caminhoContexto = request.getContextPath();
        response.sendRedirect(caminhoContexto + "/cadastrar-agendamento");// Retorno para a pagina de cadastro(tabela visualizacao agendamentos)

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
