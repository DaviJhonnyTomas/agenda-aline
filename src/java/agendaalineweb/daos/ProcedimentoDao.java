/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaalineweb.daos;

import agendaalineweb.conect.Conexao;
import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Procedimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class ProcedimentoDao {

    public static ArrayList<Procedimento> selectByNome(String pesquisar) {
        String sql = "select * from procedimento where nome like ? ";// like (como alguma coisa / "comparacao")
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        ArrayList<Procedimento> procedimentos = null;
        try {
            conexao = new Conexao().getConnection();// instancia da conexao
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setString(1, "%" + pesquisar + "%");//% tem mais alguma coisa.
            ResultSet retorno = estadoPreparado.executeQuery();
            procedimentos = new ArrayList();
            while (retorno.next() == true) {
                Procedimento procedimento = new Procedimento(retorno.getInt("id"), retorno.getString("nome"), retorno.getString("duracao"), retorno.getDouble("valor"));
                procedimentos.add(procedimento);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema a operacao foi cancelada");
        } finally {
            try {
                estadoPreparado.close();
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Houve um problema ao finalizar a conexao");
            }

        }

        return procedimentos;

    }

    public void insert(Procedimento procedimento) {
        String sql = "insert into procedimento ( nome, duracao, valor ) values(?, ?, ?)";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        try {
            conexao = new Conexao().getConnection();
            conexao.setAutoCommit(false);// setar o commit automatico "envio altomatico".
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setString(1, procedimento.getNome());
            estadoPreparado.setString(2, procedimento.getDuracao());
            estadoPreparado.setDouble(3, procedimento.getValor());
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

    public void updateById(int idConvertido, Procedimento procedimentoEditado) {//recebe da Model.
        String sql = "update procedimento set nome = ?, duracao = ?, valor = ? where id = ? ";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        try {
            conexao = new Conexao().getConnection();
            conexao.setAutoCommit(false);
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setString(1, procedimentoEditado.getNome());
            estadoPreparado.setString(2, procedimentoEditado.getDuracao());
            estadoPreparado.setDouble(3, procedimentoEditado.getValor());
            estadoPreparado.setInt(4, idConvertido);
            estadoPreparado.execute();

            conexao.commit();
            JOptionPane.showMessageDialog(null, "Procedimento alterado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema a operacao foi cancelada");
        } finally {
            try {
                estadoPreparado.close();
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Houve um problema ao finalizar a conexao");
            }

        }

    }

    public ArrayList<Procedimento> selectAll() {
        String sql = "select * from procedimento ";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        ArrayList<Procedimento> procedimentos = null;
        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            ResultSet retorno = estadoPreparado.executeQuery();
            procedimentos = new ArrayList();
            while (retorno.next() == true) {
                
                Procedimento procedimento = new Procedimento(retorno.getInt("id"), retorno.getString("nome"), retorno.getString("duracao"), retorno.getDouble("valor"));
                procedimentos.add(procedimento);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }

        return procedimentos;
    }

    public boolean verificarProcedimentoById(int idConvertido) {
        String sql = "select * from procedimento where id = ?";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setInt(1, idConvertido);
            ResultSet retorno = estadoPreparado.executeQuery();

            if (retorno.next() == true) {
                return true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "houve um problema, operacao cancelada");
        } finally {

            try {
                estadoPreparado.close();
                conexao.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Houve um problema ao finalizar a conexao");

            }

        }
        return false;
    }

    public Procedimento selectById(int id) {
        String sql = "select * from procedimento where id = ?";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        Procedimento procedimento = null;
        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setInt(1, id);
            ResultSet retorno = estadoPreparado.executeQuery();

            if (retorno.next() == true) {
                procedimento = new Procedimento(retorno.getInt("id"), retorno.getString("nome"), retorno.getString("duracao"), retorno.getDouble("valor"));
                return procedimento;

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "houve um problema, operacao cancelada");
        } finally {

            try {
                estadoPreparado.close();
                conexao.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Houve um problema ao finalizar a conexao");

            }

        }
        return null;
    }

    public ArrayList<Procedimento> getProcedimentosByIds(int[] idsProcedimentos) throws SQLException {
        String sql = "Select * from procedimento";
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        PreparedStatement estadoPreparado = conn.prepareStatement(sql);
        ResultSet resultado = estadoPreparado.executeQuery();
        ArrayList<Procedimento> procedimentos = new ArrayList();
        while (resultado.next()) {
            int idProcedimentoBanco = resultado.getInt("id");
            System.out.println("id procedimento banco:" + idProcedimentoBanco);
            for (int idProcedimentoVetor : idsProcedimentos) {
                if (idProcedimentoBanco == idProcedimentoVetor) {
                    Procedimento procedimento = new Procedimento(resultado.getString("nome"), resultado.getString("duracao"), resultado.getDouble("valor"));
                    procedimentos.add(procedimento);
                }

            }
            
        }
        return procedimentos;

    }

}
