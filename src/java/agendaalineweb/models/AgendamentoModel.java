/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaalineweb.models;

import agendaalineweb.daos.AgendamentoDao;
import agendaalineweb.daos.ClienteDao;
import agendaalineweb.entities.Agendamento;
import agendaalineweb.entities.Cliente;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dell
 */
public class AgendamentoModel {

    public void insert(Agendamento agendamento) {//recebe da interface um cliente(AgendaAline.java).
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        daoAgendamento.insert(agendamento);//chamada do metodo insert da DAO.

    }

    public void updateById(int id, Agendamento agendamento) {//recebemos da interface(AgendaAline.java).
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        daoAgendamento.updateById(id, agendamento);//chamada do metodo updateById da DAO.

    }

    public ArrayList<Agendamento> selectAll() {//Tipo do retorno do metodo = ArrayList.
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        ArrayList<Agendamento> agendamentos = daoAgendamento.selectAll();//chamada do metodo selectAll da DAO.

        return agendamentos;
    }

    public ArrayList<Agendamento> selectByData(Date date) {
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        ArrayList<Agendamento> agendamentos = daoAgendamento.selectByData(date);
        return agendamentos;
    }

    public void deleteById(int id) {
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        daoAgendamento.deleteById(id);

    }

    public boolean verificarAgendamentoById(int idAgendamentoConvertido) {
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        boolean agendamentoExiste = daoAgendamento.verificarAgendamentoById(idAgendamentoConvertido);
        return agendamentoExiste;
    }

}
