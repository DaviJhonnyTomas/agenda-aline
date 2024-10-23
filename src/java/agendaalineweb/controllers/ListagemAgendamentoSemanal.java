/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Agendamento;
import agendaalineweb.models.AgendamentoModel;
import agendaalineweb.models.Agendamento_ProcedimentoModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import agendaalineweb.entities.Procedimento;

@WebServlet(name = "ListagemAgendamentoSemanal", urlPatterns = {"/listagem-agendamento-semanal"})
public class ListagemAgendamentoSemanal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AgendamentoModel agendamentoModel = new AgendamentoModel();
        LocalDate diaAtual = LocalDate.now();

        /* Lógica que usamos sem chatgpt
        if (diaAtual.getDayOfWeek() == DayOfWeek.SUNDAY) {
            //dia atual
        } else if (diaAtual.getDayOfWeek() == DayOfWeek.MONDAY) {
            Calendar calendario = new GregorianCalendar();
            calendario.set(Calendar.MONTH, diaAtual.getMonthValue() - 1);
            int quantidadeDias = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
            int diasRestantes = quantidadeDias - diaAtual.getDayOfMonth();

            //calculo para a data do fim da semana
            if (diasRestantes < 6) { //se for final do mês
                if (diaAtual.getMonth() == Month.DECEMBER) { //se for final do mês 12 (final do ano)
                    int diaFinal = (diaAtual.getDayOfMonth() + 5) - quantidadeDias;
                    LocalDate dataFim = LocalDate.of(diaAtual.getYear() + 1, 1, diaFinal);
                } else { //se for somente o final de qualquer outro mês
                    LocalDate dataFim = LocalDate.of(diaAtual.getYear(), diaAtual.getMonthValue() + 1, diaFinal);

                }
                int diaFinal = (diaAtual.getDayOfMonth() + 5) - quantidadeDias;

            }
            //calculo data do inicio da semana
            if (diaAtual.getDayOfMonth() == 1) {
                calendario.set(Calendar.MONTH, diaAtual.getMonthValue() - 2); //pegar o mês passado
                int quantidadeDiasMesPassado = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
                if (diaAtual.getMonth() == Month.JANUARY) {
                    dataInicio = LocalDate.of(diaAtual.getYear() - 1, diaAtual.getMonthValue() - 1, quantidadeDiasMesPassado);

                }else{
                    dataInicio = LocalDate.of(diaAtual.getYear(), diaAtual.getMonthValue() - 1, quantidadeDiasMesPassado);
                }
            } else {
                dataInicio = LocalDate.of(diaAtual.getYear(), diaAtual.getMonthValue(), diaAtual.getDayOfMonth() - 1);
            }

        } else if (diaAtual.getDayOfWeek() == DayOfWeek.TUESDAY) {

        } else if (diaAtual.getDayOfWeek() == DayOfWeek.WEDNESDAY) {

        } else if (diaAtual.getDayOfWeek() == DayOfWeek.THURSDAY) {

        } else if (diaAtual.getDayOfWeek() == DayOfWeek.FRIDAY) {

        } else if (diaAtual.getDayOfWeek() == DayOfWeek.SATURDAY) {

        }

         */
 /*Lógica do CHATGPT: */
        // Calcula o domingo da semana atual (início da semana)
        LocalDate inicioDaSemana = diaAtual.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        // Calcula o sábado da semana atual (fim da semana)
        LocalDate fimDaSemana = diaAtual.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));

        // Formato da data (dd/MM/yyyy)
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ArrayList<Agendamento> agendamentos = agendamentoModel.selectByIntervalo(inicioDaSemana, fimDaSemana);

        Agendamento_ProcedimentoModel apModel = new Agendamento_ProcedimentoModel();

        for (Agendamento agendamento : agendamentos) {
            try {
                ArrayList<Procedimento> procedimentos = apModel.getProcedimentosByIdAgendamento(agendamento.getId());
                agendamento.setProcedimentos(procedimentos);
            } catch (SQLException ex) {
                Logger.getLogger(ListagemAgendamentoSemanal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String[] dias = {"DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SAB"};
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataInicial = dataAtual.minusDays(dataAtual.getDayOfWeek().getValue() % 7);
        request.setAttribute("dias", dias);
        request.setAttribute("dataAtual", dataAtual.toString());
        request.setAttribute("dataInicial", dataInicial);
        request.setAttribute("agendamentos", agendamentos);
        String caminhoContexto = request.getContextPath(); 
        request.setAttribute("caminhoContexto", caminhoContexto);
        request.getRequestDispatcher("/WEB-INF/agendamentoSemanal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
