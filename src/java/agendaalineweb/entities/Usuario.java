package agendaalineweb.entities;

public class Usuario {
  private int id;
  private String nome;
  private String senha;
  private String email;
  private String telefone;
  private String endereco;
  private int idNegocio;

    public Usuario(int id, String nome, String senha,  String email, String telefone, String endereco, int idNegocio) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.idNegocio = idNegocio;
    }

    public Usuario(String nome, String login, String senha, String email, String telefone, String endereco, int idNegocio) {
        this.nome = nome;
        this.senha = senha;
        
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.idNegocio = idNegocio;
    }

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
    }
    
    

    public Usuario() {
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

    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
  
  
}
