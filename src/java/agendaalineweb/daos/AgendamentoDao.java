package agendaalineweb.daos;

import agendaalineweb.conect.Conexao;
import agendaalineweb.entities.Agendamento;
import agendaalineweb.entities.Agendamento_Procedimento;
import agendaalineweb.entities.Procedimento;
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

    private void insertAgendamento(Connection conexao, Agendamento agendamento) {
        String sql = "INSERT INTO agendamento (hora, data, idCliente, idUsuario) VALUES (?, ?, ?, ?)";

        PreparedStatement estadoPreparado = null;
        int id = 0;
        try {

            conexao.setAutoCommit(false);
            estadoPreparado = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            Time horaConvertida = Time.valueOf(agendamento.getHora());
            estadoPreparado.setTime(1, horaConvertida);
            Date dataConvertida = Date.valueOf(agendamento.getData());
            estadoPreparado.setDate(2, dataConvertida);
            estadoPreparado.setInt(3, agendamento.getIdCliente());
            estadoPreparado.setInt(4, agendamento.getIdUsuario());
            int linhasGeradas = estadoPreparado.executeUpdate();

            if (linhasGeradas == 0) {
                throw new SQLException("Erro ao cadastrar agendamento");
            } else {
                ResultSet rs = estadoPreparado.getGeneratedKeys();
                rs.next();
                id = rs.getInt(1);
                agendamento.setId(id);
            }

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
                    //conexao.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Agendamento> selectByIntervalo(LocalDate dataInicio, LocalDate dataFim, int idUsuario) {
        String sql = "SELECT *"
                + "FROM Agendamento "
                + "WHERE idUsuario = ? AND data BETWEEN ? AND ? ";

        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        try (Connection conn = new Conexao().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setDate(2, Date.valueOf(dataInicio)); //dataInicio -> LocalDate | Date.valueOf(dataInicio) serve para converter
            stmt.setDate(3, Date.valueOf(dataFim));
            

            // Executa a consulta
            ResultSet rs = stmt.executeQuery();

            // Processa os resultados
            while (rs.next()) {
                Agendamento agendamento = new Agendamento(rs.getInt("id"), rs.getTime("hora").toLocalTime(), rs.getDate("data").toLocalDate(), rs.getInt("idCliente"), rs.getInt("idUsuario"));
                agendamentos.add(agendamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agendamentos;

    }

    public void insertProcedimentos(Agendamento agendamento, ArrayList<Integer> idsProcedimentos) throws SQLException {
        String sql = "INSERT INTO Agendamento_Procedimento (idProcedimento, idAgendamento) VALUES (?, ?)";

        Connection conexao = new Conexao().getConnection();
        conexao.setAutoCommit(false);

        insertAgendamento(conexao, agendamento);

        try {

            for (int i = 0; i < idsProcedimentos.size(); i++) {
                PreparedStatement estadoPreparado = conexao.prepareStatement(sql);
                estadoPreparado.setInt(1, idsProcedimentos.get(i));
                estadoPreparado.setInt(2, agendamento.getId());

                estadoPreparado.execute();
                estadoPreparado.close();
            }

            conexao.commit();

        } catch (SQLException ex) {
            conexao.rollback();
            ex.printStackTrace();

        } finally {
            //conexao.close();
        }
    }

    private void updateAgendamento(Connection conexao, Agendamento agendamento) {
        String sql = "update agendamento set hora=?, data=?, idCliente=? where id = ? ";

        PreparedStatement estadoPreparado = null;
        try {

            conexao.setAutoCommit(false);
            estadoPreparado = conexao.prepareStatement(sql);

            Time horaConvertida = Time.valueOf(agendamento.getHora());
            estadoPreparado.setTime(1, horaConvertida);
            Date dataConvertida = Date.valueOf(agendamento.getData());
            estadoPreparado.setDate(2, dataConvertida);
            estadoPreparado.setInt(3, agendamento.getIdCliente());
            estadoPreparado.setInt(4, agendamento.getId());

            estadoPreparado.executeUpdate();

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
                    //conexao.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateAgendamentoProcedimentos(Agendamento agendamento, ArrayList<Integer> idsProcedimentos) throws SQLException {
        // Inicializando DAOs e conexões
        Agendamento_ProcedimentoDao agProcedimentoDao = new Agendamento_ProcedimentoDao();
        Connection conexao = new Conexao().getConnection();
        conexao.setAutoCommit(false);

        try {
            // Passo 1: Obter procedimentos atuais
            ArrayList<Procedimento> procedimentosAtuais = agProcedimentoDao.getProcedimentosByIdAgendamento(agendamento.getId());

            // Convertendo os IDs dos procedimentos atuais para uma lista para facilitar a comparação
            ArrayList<Integer> idsProcedimentosAtuais = new ArrayList<>();
            for (Procedimento procedimento : procedimentosAtuais) {
                idsProcedimentosAtuais.add(procedimento.getId());
            }

            // Passo 2: Identificar procedimentos a serem adicionados e removidos
            ArrayList<Integer> procedimentosParaAdicionar = new ArrayList<>(idsProcedimentos);
            procedimentosParaAdicionar.removeAll(idsProcedimentosAtuais);

            ArrayList<Integer> procedimentosParaRemover = new ArrayList<>(idsProcedimentosAtuais);
            procedimentosParaRemover.removeAll(idsProcedimentos);

            // Passo 3: Remover procedimentos que não estão na nova lista
            for (Integer idProcedimento : procedimentosParaRemover) {
                agProcedimentoDao.removerProcedimentoAgendamento(conexao, agendamento.getId(), idProcedimento);
            }

            // Passo 4: Adicionar novos procedimentos
            for (Integer idProcedimento : procedimentosParaAdicionar) {
                agProcedimentoDao.adicionarProcedimentoAgendamento(conexao, agendamento.getId(), idProcedimento);
            }

            // Passo 5: Atualizar o próprio agendamento, se necessário
            updateAgendamento(conexao, agendamento);

            // Commit das alterações
            conexao.commit();

        } catch (SQLException e) {
            // Em caso de erro, realizar rollback
            conexao.rollback();
            throw e;
        } finally {
            // Fechar a conexão
            conexao.setAutoCommit(true);
            conexao.close();
        }
    }

    public ArrayList<Agendamento> selectAll(int idUsuario) {
        String sql = "select * from agendamento where idUsuario = ?";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        ArrayList<Agendamento> agendamentos = null;
        try {
            conexao = new Conexao().getConnection();

            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setInt(1, idUsuario);
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

    public ArrayList<Agendamento> selectByData(Date date, int idUsuario) {
        String sql = "select * from agendamento where data = ? AND idUsuario = ?";
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        ArrayList<Agendamento> agendamentos = null;
        try {
            conexao = new Conexao().getConnection();
            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setDate(1, date);
            estadoPreparado.setInt(2, idUsuario);
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
        Connection conexao = null;
        PreparedStatement estadoPreparado = null;
        PreparedStatement estadoPreparadoAgp = null;

        String sqlAgp = " delete from agendamento_procedimento where idAgendamento = ? ";

        String sql = " delete from agendamento where id = ? ";

        try {
            conexao = new Conexao().getConnection();
            conexao.setAutoCommit(false);

            estadoPreparadoAgp = conexao.prepareStatement(sqlAgp);
            estadoPreparadoAgp.setInt(1, id);
            estadoPreparadoAgp.execute();

            estadoPreparado = conexao.prepareStatement(sql);
            estadoPreparado.setInt(1, id);
            estadoPreparado.execute();

            conexao.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                estadoPreparado.close();
                estadoPreparadoAgp.close();

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

    public ArrayList<Agendamento> selectAgendamentosByIdsClientes(int[] idsClientes, int idUsuario) {
        String sql = "select * from agendamento where idUsuario = ?";
        Connection conn = null;
        PreparedStatement estadoPreparado = null;
        ArrayList<Agendamento> agendamentos = null;
        try {
            conn = new Conexao().getConnection();
            estadoPreparado = conn.prepareStatement(sql);
            estadoPreparado.setInt(1, idUsuario);
            ResultSet rs = estadoPreparado.executeQuery();
            agendamentos = new ArrayList();
            while (rs.next()) {
                int idClienteBanco = rs.getInt("idCliente");
                for (int i = 0; i < idsClientes.length; i++) {
                    if (idsClientes[i] == idClienteBanco) {
                        Agendamento agendamento = new Agendamento(rs.getInt("id"), rs.getTime("hora").toLocalTime(), rs.getDate("data").toLocalDate(), rs.getInt("idCliente"), rs.getInt("idUsuario"));
                        agendamentos.add(agendamento);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                estadoPreparado.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AgendamentoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }

        return agendamentos;
    }

    //variáveis, métodos, objetos, tipos de dados primitivos
    public ArrayList<Agendamento> selectByDataAndNome(Date data, String nome, int idUsuario) throws SQLException {
        String sql = "SELECT ag.*, cl.nome FROM Agendamento ag INNER JOIN Cliente cl ON ag.idCliente = cl.id WHERE ag.data = ? and cl.nome LIKE ? and ag.idUsuario = ? ";//INNER JOIN -> junção interna de tabelas
        Connection conn = new Conexao().getConnection();
        PreparedStatement estadoPreparado = conn.prepareStatement(sql);
        estadoPreparado.setDate(1, data);
        estadoPreparado.setString(2, nome + "%");
        estadoPreparado.setInt(3, idUsuario);
        ResultSet rs = estadoPreparado.executeQuery();

        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        while (rs.next() == true) {
            // Agendamento agendamento = new Agendamento(rs.getInt("id"), rs.getInt("idProcedimento"), rs.getTime("hora").toLocalTime(), rs.getDate("data").toLocalDate(), rs.getInt("idCliente"));
            // agendamentos.add(agendamento);
        }
        return agendamentos;

    }

    public boolean clienteTemAgendamento(int idCliente) {
        String sql = "SELECT * from Agendamento where idCliente = ?";

        try {
            Connection conn = new Conexao().getConnection();
            PreparedStatement estadoPreparado = conn.prepareStatement(sql);
            estadoPreparado.setInt(1, idCliente);
            ResultSet rs = estadoPreparado.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
