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
public class ClienteModel extends AbstractTableModel {

    ArrayList<Cliente> clientes;
    String[] colunas = {"id", "nome", "telefone", "email"};

    public ClienteModel() {
        clientes = selectAll();
    }

    public void insert(Cliente cliente) {//recebe da interface um cliente(AgendaAline.java).
        ClienteDao daoCliente = new ClienteDao();
        daoCliente.insert(cliente);//chamada do metodo insert da DAO.
        clientes = selectAll();// Atualizar clientes inseridos.
        this.fireTableDataChanged(); // atualizar os dados da nossa tabela.
    }

    public ArrayList<Cliente> selectAll() {//Tipo do retorno do metodo = ArrayList.
        ClienteDao daoCliente = new ClienteDao();
        ArrayList<Cliente> clientes = daoCliente.selectAll();//chamada do metodo selectAll da DAO.

        return clientes;
    }

    public void updateById(int idConvertido, Cliente clienteEditado) {//recebemos da interface(AgendaAline.java).
        ClienteDao daoCliente = new ClienteDao();
        daoCliente.updateById(idConvertido, clienteEditado);//chamada do metodo updateById da DAO.
        clientes = selectAll();// Atualizar clientes editado
        this.fireTableDataChanged();// Atualizar a tabela editada
    }

    public void deleteById(int id) {
        ClienteDao daoCliente = new ClienteDao();
        daoCliente.deleteById(id);
        clientes = selectAll();// Atualizar clientes editado
        this.fireTableDataChanged();// Atualizar a tabela editada
    }

    public boolean verificarClienteById(int idConvertido) {
        ClienteDao daoCliente = new ClienteDao();
        boolean clienteExiste = daoCliente.verificarClienteById(idConvertido); //chamada do metodo selectAll da DAO.

        return clienteExiste;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return clientes.get(rowIndex).getId();
        } else if (columnIndex == 1) {
            return clientes.get(rowIndex).getNome();
        } else if (columnIndex == 2) {
            return clientes.get(rowIndex).getTelefone();
        } else if (columnIndex == 3) {
            return clientes.get(rowIndex).getEmail();
        }
        return "null";
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public ArrayList<Cliente> selectByNome(String pesquisar) {
        ClienteDao clienteDao = new ClienteDao();
        clientes = clienteDao.selectByNome(pesquisar);// Atualizar nossos clientes
        this.fireTableDataChanged(); // atualizar os dados da nossa tabela.
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
