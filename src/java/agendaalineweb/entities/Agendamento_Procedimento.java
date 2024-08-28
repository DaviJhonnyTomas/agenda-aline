/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendaalineweb.entities;

/**
 *
 * @author Utilizador
 */
public class Agendamento_Procedimento {
    private int id; //paramos aqui
    private int idProcedimento;
    private int idAgendamento;

    public Agendamento_Procedimento(int idProcedimento, int idAgendamento) {
        this.idProcedimento = idProcedimento;
        this.idAgendamento = idAgendamento;
    }

    public int getIdProcedimento() {
        return idProcedimento;
    }

    public void setIdProcedimento(int idProcedimento) {
        this.idProcedimento = idProcedimento;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }
    
    
}
