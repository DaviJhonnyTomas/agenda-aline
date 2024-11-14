/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaalineweb.daos;

import agendaalineweb.conect.Conexao;
import agendaalineweb.entities.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ClienteDao {

    public void insert(Cliente cliente) {
        String sql = "insert into cliente ( nome, telefone, email, idNegocio ) values(?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        try {
            conexao = new Conexao().getConnection();
            conexao.setAutoCommit(false);
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setString(1, cliente.getNome());
            estadoPreparado.setString(2, cliente.getTelefone());
            estadoPreparado.setString(3, cliente.getEmail());
            estadoPreparado.setInt(4, cliente.getIdNegocio());
            estadoPreparado.execute();
            conexao.commit();

        } catch (SQLException ex) {
                        ex.printStackTrace();

        } finally {
            try {
                estadoPreparado.close();
                conexao.close();
            } catch (SQLException ex) {
            ex.printStackTrace();
            }

        }

    }

    public boolean verificarClienteById(int id) {
        String sql = "select * from cliente where id = ?";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setInt(1, id);
            ResultSet retorno = estadoPreparado.executeQuery();

            if (retorno.next() == true) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                estadoPreparado.close();
                conexao.close();

            } catch (SQLException ex) {
            ex.printStackTrace();

            }

        }
        return false;
    }

    public ArrayList<Cliente> selectAll() {
        String sql = "select * from cliente order by nome asc";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        ArrayList<Cliente> clientes = null;
        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            ResultSet retorno = estadoPreparado.executeQuery();
            clientes = new ArrayList();
            while (retorno.next() == true) {
                int id = retorno.getInt("id");
                Cliente cliente = new Cliente(id, retorno.getString("nome"), retorno.getString("telefone"), retorno.getString("email"), retorno.getInt("idNegocio"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clientes;

    }

    public void updateById(Cliente cliente) {//recebe da Model.
        String sql = "update cliente set nome = ?, telefone = ?, email = ? where id = ? ";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        try {
            conexao = new Conexao().getConnection();
            conexao.setAutoCommit(false);
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setString(1, cliente.getNome());
            estadoPreparado.setString(2, cliente.getTelefone());
            estadoPreparado.setString(3, cliente.getEmail());
            estadoPreparado.setInt(4, cliente.getId());
            estadoPreparado.execute();

            conexao.commit();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                estadoPreparado.close();
                conexao.close();

            } catch (SQLException ex) {
               ex.printStackTrace();
            }

        }

    }

    public void deleteById(int id) {// recebe da Model.
        String sql = " delete from cliente where id = ? ";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        try {
            conexao = new Conexao().getConnection();
            conexao.setAutoCommit(false);
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setInt(1, id);
            estadoPreparado.execute();

            conexao.commit();

        }
        catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                estadoPreparado.close();
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }

    public ArrayList<Cliente> selectByNome(String pesquisar) {
        String sql = "select * from cliente where nome like ? "; //like => semelhante a
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        ArrayList<Cliente> clientes = null;
        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setString(1, pesquisar + "%");
            ResultSet retorno = estadoPreparado.executeQuery();
            clientes = new ArrayList();
            while (retorno.next() == true) {
                Cliente cliente = new Cliente(retorno.getInt("id"), retorno.getString("nome"), retorno.getString("telefone"), retorno.getString("email"), retorno.getInt("idNegocio"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                estadoPreparado.close();
                conexao.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return clientes;

    }

    public Cliente selectById(int id) {
        String sql = "select * from cliente where id = ?";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        Cliente cliente = null;
        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setInt(1, id);
            ResultSet retorno = estadoPreparado.executeQuery();

            if (retorno.next() == true) {
                cliente = new Cliente(retorno.getInt("id"), retorno.getString("nome"), retorno.getString("telefone"), retorno.getString("email"), retorno.getInt("idNegocio"));
                return cliente;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                estadoPreparado.close();
                conexao.close();

            } catch (SQLException ex) {
                ex.printStackTrace();

            }

        }
        return null;
    }

    public ArrayList<Cliente> getClientesByIds(int[] idsClientes) throws SQLException {
        String sql = "Select * from cliente";
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        PreparedStatement estadoPreparado = conn.prepareStatement(sql);
        ResultSet resultado = estadoPreparado.executeQuery();
        ArrayList<Cliente> clientes = new ArrayList();
        while (resultado.next()) {
            int idClienteBanco = resultado.getInt("id");
            System.out.println("id cliente banco:" + idClienteBanco);
            for (int idClienteVetor : idsClientes) {
                if (idClienteBanco == idClienteVetor) {
                    //Cliente cliente = new Cliente(resultado.getString("nome"), resultado.getString("telefone"), resultado.getString("email"));
                   // clientes.add(cliente);
                }

            }

        }
        return clientes;

    }

}
