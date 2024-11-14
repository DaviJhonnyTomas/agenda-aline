/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaalineweb.entities;

/**
 *
 * @author Dell
 */
public class Procedimento {

    private int id;
    private String nome;
    private String duracao;
    private double valor;
    private int idUsuario;

    public Procedimento(int id, String nome, String duracao, double valor, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.valor = valor;
        this.idUsuario = idUsuario;
    }
    public Procedimento(int id, String nome, String duracao, double valor) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.valor = valor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Procedimento(String nome, String duracao, double valor) {

        this.nome = nome;
        this.duracao = duracao;
        this.valor = valor;
    }
    public Procedimento(String nome, String duracao, double valor, int idUsuario) {

        this.nome = nome;
        this.duracao = duracao;
        this.valor = valor;
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return nome; //To change body of generated methods, choose Tools | Templates.
    }
    
}
