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
import agendaalineweb.models.Agendamento_ProcedimentoModel;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "EditarAgendamento", urlPatterns = {"/editar-agendamento"})
public class EditarAgendamento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idAgendamento = request.getParameter("id");
        String idCliente = request.getParameter("idCliente");
        //String idProcedimento = request.getParameter("idProcedimento");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");

        Agendamento agendamento = new Agendamento(Integer.parseInt(idAgendamento), LocalTime.parse(hora), LocalDate.parse(data), Integer.parseInt(idCliente));
        request.setAttribute("agendamento", agendamento);
        request.setAttribute("modal", "modalEditarAgendamento");

        AgendamentoModel agendamentoModel = new AgendamentoModel();
        ArrayList<Agendamento> agendamentos = agendamentoModel.selectAll();
        request.setAttribute("agendamentos", agendamentos);

        String caminhoContexto = request.getContextPath();
        request.setAttribute("caminhoContexto", caminhoContexto);

        ClienteModel clienteModel = new ClienteModel();
        ArrayList<Cliente> clientes = clienteModel.selectAll();
        request.setAttribute("clientes", clientes);
        //Procedimento procedimentoSelecionado = procedimentoModel.selectById(Integer.parseInt(idProcedimento));
        // request.setAttribute("procedimentoSelecionado", procedimentoSelecionado);
        Cliente clienteSelecionado = clienteModel.selectById(Integer.parseInt(idCliente));
        request.setAttribute("clienteSelecionado", clienteSelecionado);

        Agendamento_ProcedimentoModel agendamento_ProcedimentoModel = new Agendamento_ProcedimentoModel();
        ArrayList<Procedimento> procedimentosSelecionados;
        try {
            procedimentosSelecionados = agendamento_ProcedimentoModel.getProcedimentosByIdAgendamento(agendamento.getId());
            request.setAttribute("procedimentosSelecionados", procedimentosSelecionados);

            ProcedimentoModel procedimentoModel = new ProcedimentoModel();
            ArrayList<Procedimento> procedimentos2 = procedimentoModel.selectAll();

            for (int i = 0; i < procedimentos2.size(); i++) {

                for (int j = 0; j < procedimentosSelecionados.size(); j++) {

                    if (procedimentos2.get(i).getId() == procedimentosSelecionados.get(j).getId()) {
                        procedimentos2.remove(i);
                    }
                }

            }
            request.setAttribute("procedimentos2", procedimentos2);

            ArrayList<Procedimento> procedimentos = procedimentoModel.selectAll();
            request.setAttribute("procedimentos", procedimentos);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/pageAgendamentos.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        String mensagem = null;
        if (usuario == null) {
            mensagem = "Você deve fazer login para realizar esta operação.";
        } else {

            String idAgendamento = request.getParameter("id");
            String idCliente = request.getParameter("idCliente");

            boolean idClienteInformado = false;
            if (!idCliente.isEmpty()) { // se o id do cliente não for vazio (! serve para negar)
                idClienteInformado = true;
            }

            String data = request.getParameter("data");
            LocalDate dataConvertida = null;
            boolean dataInformada = false;
            if (!data.isEmpty()) {
                dataConvertida = LocalDate.parse(data);
                dataInformada = true;
            }

            String hora = request.getParameter("hora");
            LocalTime horaConvertida = null;
            boolean horaInformada = false;
            if (!hora.isEmpty()) {
                horaConvertida = LocalTime.parse(hora);
                horaInformada = true;
            }
            ProcedimentoModel procedimentoModel = new ProcedimentoModel();
            ArrayList<Procedimento> procedimentos = procedimentoModel.selectAll();

            ArrayList<Integer> idsProcedimentos = new ArrayList();
            for (int i = 0; i < procedimentos.size(); i++) {
                String idProcedimento = request.getParameter("idProcedimentoStart" + i);
                if (idProcedimento != null) {
                    idsProcedimentos.add(Integer.parseInt(idProcedimento));
                }
            }
            for (int i = 0; i < procedimentos.size(); i++) {
                String idProcedimento = request.getParameter("idProcedimentoEnd" + i);
                if (idProcedimento != null) {
                    idsProcedimentos.add(Integer.parseInt(idProcedimento));
                }
            }

            boolean idsProcedimentosInformados = false;
            if (idsProcedimentos.size() > 0) {
                idsProcedimentosInformados = true;
            }

            if (idClienteInformado && idsProcedimentosInformados && horaInformada && dataInformada) {
                Agendamento agendamento = new Agendamento(Integer.parseInt(idAgendamento), horaConvertida, dataConvertida, Integer.parseInt(idCliente));
                AgendamentoModel agendamentoModel = new AgendamentoModel();
                try {
                    agendamentoModel.updateById(agendamento, idsProcedimentos);// Agendamento ja editado
                } catch (SQLException ex) {
                    Logger.getLogger(EditarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                mensagem = "Todos os campos devem ser preenchidos.";
            }
        }
        // Retorno para a pagina de cadastro(tabela visualizacao agendamentos)
        request.setAttribute("mensagemErro", mensagem);

        String caminhoContexto = request.getContextPath();
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        ArrayList<Agendamento> agendamentos = agendamentoModel.selectAll();
        request.setAttribute("caminhoContexto", caminhoContexto);
        request.setAttribute("agendamentos", agendamentos);

        request.getRequestDispatcher("WEB-INF/pageAgendamentos.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
