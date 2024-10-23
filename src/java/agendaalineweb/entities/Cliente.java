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
public class Cliente {

    private int id;
    private String nome;
    private String telefone;
    private String email;
    private int idNegocio;

    public Cliente(int id, String nome, String telefone, String email, int idNegocio) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.idNegocio = idNegocio;
    }

    public Cliente(String nome, String telefone, String email, int idNegocio) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.idNegocio = idNegocio;
    }
    public Cliente(int id,String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        
    }

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

    @Override // Anotacao de sobre escrita.
    public String toString() {
        return nome; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
