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

    public void insert(Agendamento agendamento, ArrayList<Integer> idsProcedimentos) throws SQLException {//recebe da interface um cliente(AgendaAline.java).
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        daoAgendamento.insertProcedimentos(agendamento, idsProcedimentos);

    }

    public void updateById(Agendamento agendamento, ArrayList<Integer> idsProcedimentos) {//recebemos da interface(AgendaAline.java).
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        daoAgendamento.updateById(agendamento, idsProcedimentos);//chamada do metodo updateById da DAO.

    }
    
    public ArrayList<Agendamento> selectByIntervalo(LocalDate dataInicio, LocalDate dataFim){
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        return daoAgendamento.selectByIntervalo(dataInicio, dataFim);
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

    public ArrayList<Agendamento> selectAgendamentosByIdsClientes(int[] idsClientes) throws SQLException {
        AgendamentoDao daoAgendamento = new AgendamentoDao();
        ArrayList<Agendamento> agendamentos = daoAgendamento.selectAgendamentosByIdsClientes(idsClientes);
        return agendamentos;
    }

    public ArrayList<Agendamento> selectByDataAndNome(Date data, String nome) throws SQLException {
        AgendamentoDao agendamentoDao = new AgendamentoDao();
        ArrayList<Agendamento> agendamentos = agendamentoDao.selectByDataAndNome(data, nome);
       return agendamentos;
    }

}
