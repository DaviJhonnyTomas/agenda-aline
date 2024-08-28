/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendaalineweb.entities;

/**
 *
 * @author Utilizador
 */
public class Estilo {
    private int id;
    private String corPrincipal;
    private String corSecundaria; 
    private byte[] logo;
    private byte[] imagemFundo;

    public Estilo(int id, String corPrincipal, String corSecundaria, byte[] logo, byte[] imagemFundo) {
        this.id = id;
        this.corPrincipal = corPrincipal;
        this.corSecundaria = corSecundaria;
        this.logo = logo;
        this.imagemFundo = imagemFundo;
    }

    public Estilo(String corPrincipal, String corSecundaria, byte[] logo, byte[] imagemFundo) {
        this.corPrincipal = corPrincipal;
        this.corSecundaria = corSecundaria;
        this.logo = logo;
        this.imagemFundo = imagemFundo;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorPrincipal() {
        return corPrincipal;
    }

    public void setCorPrincipal(String corPrincipal) {
        this.corPrincipal = corPrincipal;
    }

    public String getCorSecundaria() {
        return corSecundaria;
    }

    public void setCorSecundaria(String corSecundaria) {
        this.corSecundaria = corSecundaria;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public byte[] getImagemFundo() {
        return imagemFundo;
    }

    public void setImagemFundo(byte[] imagemFundo) {
        this.imagemFundo = imagemFundo;
    }
    
    
    
}
