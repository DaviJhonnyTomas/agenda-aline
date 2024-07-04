/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaalineweb.models;

import agendaalineweb.daos.ClienteDao;
import agendaalineweb.entities.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dell
 */
public class ClienteModel {

    public ClienteModel() {
        
    }

    public void insert(Cliente cliente) {//recebe da interface um cliente(AgendaAline.java).
        ClienteDao daoCliente = new ClienteDao();
        daoCliente.insert(cliente);//chamada do metodo insert da DAO.
        
    }

    public ArrayList<Cliente> selectAll() {//Tipo do retorno do metodo = ArrayList.
        ClienteDao daoCliente = new ClienteDao();
        ArrayList<Cliente> clientes = daoCliente.selectAll();//chamada do metodo selectAll da DAO.

        return clientes;
    }

    public void updateById( Cliente clienteEditado) {//recebemos da interface(AgendaAline.java).
        ClienteDao daoCliente = new ClienteDao();
        daoCliente.updateById(clienteEditado);//chamada do metodo updateById da DAO.
       
    }

    public void deleteById(int id) {
        ClienteDao daoCliente = new ClienteDao();
        daoCliente.deleteById(id);
        
    }

    public boolean verificarClienteById(int idConvertido) {
        ClienteDao daoCliente = new ClienteDao();
        boolean clienteExiste = daoCliente.verificarClienteById(idConvertido); //chamada do metodo selectAll da DAO.

        return clienteExiste;
    }


    public ArrayList<Cliente> selectByNome(String pesquisar) {
        ClienteDao clienteDao = new ClienteDao();
        ArrayList<Cliente> clientes = clienteDao.selectByNome(pesquisar);// Atualizar nossos clientes
       
        return clientes;

    }

    public Cliente selectById(int id) {
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.selectById(id);
        return cliente;
    }

    public ArrayList<Cliente> getClientesByIds(int[] idsClientes) throws SQLException {
        ClienteDao clienteDao = new ClienteDao();
        ArrayList<Cliente> clientes = clienteDao.getClientesByIds(idsClientes);
        return clientes;
    }

}
