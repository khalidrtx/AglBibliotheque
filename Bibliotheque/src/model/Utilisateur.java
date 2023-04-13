
package model;

import java.util.Date;

/**
 *
 * @author khali
 */
public class Utilisateur {
	protected int id;
	protected String nom;
	protected String prenom;
	protected String sexe;
	protected String cin;
	protected Date dateNais;

    public Utilisateur() {
    	
    }

    public Utilisateur(int id, String nom, String prenom, String sexe, String cin, Date dateNais) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.cin = cin;
        this.dateNais = dateNais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setDateNais(Date dateNais) {
        this.dateNais = dateNais;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getCin() {
        return cin;
    }

    public Date getDateNais() {
        return dateNais;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", cin=" + cin + ", dateNais=" + dateNais + '}';
    }


	
}
