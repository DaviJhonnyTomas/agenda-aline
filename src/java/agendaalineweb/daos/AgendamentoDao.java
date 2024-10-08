package agendaalineweb.daos;

import agendaalineweb.conect.Conexao;
import agendaalineweb.entities.Agendamento;
import agendaalineweb.entities.Agendamento_Procedimento;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dell
 */
public class AgendamentoDao {

    public void insert(Agendamento agendamento, ArrayList<Integer> idsProcedimentos) {
        String sql = "INSERT INTO agendamento (hora, data, idCliente, idUsuario) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        int id = 0;
        try {
            conexao = new Conexao().getConnection();
            conexao.setAutoCommit(false);
            estadoPreparado = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            Time horaConvertida = Time.valueOf(agendamento.getHora());
            estadoPreparado.setTime(1, horaConvertida);
            Date dataConvertida = Date.valueOf(agendamento.getData());
            estadoPreparado.setDate(2, dataConvertida);
            estadoPreparado.setInt(3, agendamento.getIdCliente());
            estadoPreparado.setInt(4, agendamento.getIdUsuario());
            estadoPreparado.executeUpdate();

            // Recupera as chaves geradas
            try (ResultSet rs = estadoPreparado.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1); // Obtém o ID gerado
                }
            }

            // Vincular procedimento ao agendamento cadastrado
            insertProcedimentos(idsProcedimentos, id);

            conexao.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            try {
                if (estadoPreparado != null) {
                    estadoPreparado.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void insertProcedimentos(ArrayList<Integer> idsProcedimentos, int idAgendamento) {
        String sql = "INSERT INTO Agendamento_Procedimento (idProcedimento, idAgendamento) VALUES (?, ?)";
        final int MAX_RETRIES = 3;
        int tentativa = 0;

        while (tentativa < MAX_RETRIES) {
            try (Connection conn = new Conexao().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

                conn.setAutoCommit(false);

                int batchSize = 100;
                for (int i = 0; i < idsProcedimentos.size(); i += batchSize) {
                    List<Integer> batchIds = idsProcedimentos.subList(i, Math.min(i + batchSize, idsProcedimentos.size()));
                    // Execute a atualização em batch para a lista de IDs atual
                    ps.executeBatch();
                }
                conn.commit();
                return; // Sai do método se bem-sucedido

            } catch (SQLException ex) {
                tentativa++;
                if (tentativa >= MAX_RETRIES) {
                    ex.printStackTrace();
                    break; // Sai do loop após o número máximo de tentativas
                }
                // Opcional: Adicione um pequeno atraso antes de tentar novamente
                try {
                    Thread.sleep(1000); // Atraso de 1 segundo
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    public void updateById(Agendamento agendamento, int[] idProcedimentos) {//recebe da Model.
        String sql = "update agendamento set hora = ?, data = ?, idCliente = ?, idUsuario = ? where id = ? ";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        try {
            conexao = new Conexao().getConnection();
            conexao.setAutoCommit(false);
            estadoPreparado = conexao.prepareStatement(sql);
            Time horaConvertida = Time.valueOf(agendamento.getHora());
            estadoPreparado.setTime(1, horaConvertida);
            Date dataConvertida = Date.valueOf(agendamento.getData());
            estadoPreparado.setDate(2, dataConvertida);
            estadoPreparado.setInt(3, agendamento.getIdCliente());
            estadoPreparado.setInt(4, agendamento.getIdUsuario());
            estadoPreparado.setInt(5, agendamento.getId());// where ID ?.
            estadoPreparado.execute();

            // 1
            //Jhonata
            //unhas2 = 2, unhas3 = 3
            if (idProcedimentos.length != 0) {
                for (int i = 0; i < idProcedimentos.length; i++) {
                    Agendamento_Procedimento pa = new Agendamento_Procedimento(idProcedimentos[i], agendamento.getId());
                    updateProcedimentoAgendamento(pa);
                }
            }

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

    public void updateProcedimentoAgendamento(Agendamento_Procedimento pa) {
        String sql = "update Agendamento_Procedimento set idProcedimento = ? where id = ?";
    }

    public ArrayList<Agendamento> selectAll() {
        String sql = "select * from agendamento ";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        ArrayList<Agendamento> agendamentos = null;
        try {
            conexao = new Conexao().getConnection();

            estadoPreparado = conexao.prepareStatement(sql);
            ResultSet retorno = estadoPreparado.executeQuery();
            agendamentos = new ArrayList();
            while (retorno.next() == true) {
                Time hora = retorno.getTime("hora");
                LocalTime horaConvertida = hora.toLocalTime();
                Date data = retorno.getDate("data");
                LocalDate dataConvertida = data.toLocalDate();
                Agendamento agendamento = new Agendamento(retorno.getInt("id"), horaConvertida, dataConvertida, retorno.getInt("idCliente"), retorno.getInt("idUsuario"));
                agendamentos.add(agendamento);
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
        return agendamentos;
    }

    public ArrayList<Agendamento> selectByData(Date date) {
        String sql = "select * from agendamento where data = ? ";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        ArrayList<Agendamento> agendamentos = null;
        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setDate(1, date);
            ResultSet retorno = estadoPreparado.executeQuery();
            agendamentos = new ArrayList();
            while (retorno.next() == true) {
                Time hora = retorno.getTime("hora");
                LocalTime horaConvertida = hora.toLocalTime();
                Date data = retorno.getDate("data");
                LocalDate dataConvertida = data.toLocalDate();
                Agendamento agendamento = new Agendamento(retorno.getInt("id"), horaConvertida, dataConvertida, retorno.getInt("idCliente"), retorno.getInt("idUsuario"));
                agendamentos.add(agendamento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

        }
        return agendamentos;
    }

    public void deleteById(int id) {// recebe da Model.
        String sql = " delete from agendamento where id = ? ";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        try {
            conexao = new Conexao().getConnection();
            conexao.setAutoCommit(false);
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setInt(1, id);
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

    public boolean verificarAgendamentoById(int idAgendamentoConvertido) {
        String sql = "select * from agendamento where id = ? ";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;

        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setInt(1, idAgendamentoConvertido);
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

    public ArrayList<Agendamento> selectAgendamentosByIdsClientes(int[] idsClientes) throws SQLException {
        String sql = "select * from agendamento";
        Connection conn = new Conexao().getConnection();
        PreparedStatement estadoPreparado = conn.prepareStatement(sql);
        ResultSet rs = estadoPreparado.executeQuery();
        ArrayList<Agendamento> agendamentos = new ArrayList();
        while (rs.next()) {
            int idClienteBanco = rs.getInt("idCliente");
            for (int i = 0; i < idsClientes.length; i++) {
                if (idsClientes[i] == idClienteBanco) {
                    //Agendamento agendamento = new Agendamento(rs.getInt("id"), horaConvertida, dataConvertida, retorno.getInt("idCliente"), retorno.getInt("idUsuario"));
                    //agendamentos.add(agendamento);
                }
            }
        }
        return agendamentos;
    }

    //variáveis, métodos, objetos, tipos de dados primitivos
    public ArrayList<Agendamento> selectByDataAndNome(Date data, String nome) throws SQLException {
        String sql = "SELECT ag.*, cl.nome FROM Agendamento ag INNER JOIN Cliente cl ON ag.idCliente = cl.id WHERE ag.data = ? and cl.nome LIKE ? ";//INNER JOIN -> junção interna de tabelas
        Connection conn = new Conexao().getConnection();
        PreparedStatement estadoPreparado = conn.prepareStatement(sql);
        estadoPreparado.setDate(1, data);
        estadoPreparado.setString(2, nome + "%");
        ResultSet rs = estadoPreparado.executeQuery();

        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        while (rs.next() == true) {
            // Agendamento agendamento = new Agendamento(rs.getInt("id"), rs.getInt("idProcedimento"), rs.getTime("hora").toLocalTime(), rs.getDate("data").toLocalDate(), rs.getInt("idCliente"));
            // agendamentos.add(agendamento);
        }
        return agendamentos;

    }

}
