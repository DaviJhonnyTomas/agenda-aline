/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendaalineweb.entities;

/**
 *
 * @author Utilizador
 */
public class Negocio {
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private int nif;
    private int plano;
    private double valor;
    private int idUsuarioAdm;
    private int idEstilo;

    public Negocio(int id, String nome, String telefone, String endereco, int nif, int plano, double valor) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nif = nif;
        this.plano = plano;
        this.valor = valor;
    }

    public Negocio(String nome, String telefone, String endereco, int nif, int plano, double valor) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nif = nif;
        this.plano = plano;
        this.valor = valor;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getPlano() {
        return plano;
    }

    public void setPlano(int plano) {
        this.plano = plano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
