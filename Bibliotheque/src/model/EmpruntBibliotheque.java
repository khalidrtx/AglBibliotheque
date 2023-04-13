
package model;

import java.util.Date;

/**
 *
 * @author khali
 */
public class EmpruntBibliotheque {
	private int id;
	private Utilisateur emprunteur;
	private Date dateEmprunt;
	private Date dateRetour;
        private Livre livre;
        
    public EmpruntBibliotheque() {
        }
    public EmpruntBibliotheque(int id, Utilisateur emprunteur, Date dateEmprunt, Date dateRetour, Livre livre) {
        this.id = id;
        this.emprunteur = emprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.livre = livre;
    }

    public int getId() {
        return id;
    }

    public Utilisateur getEmprunteur() {
        return emprunteur;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmprunteur(Utilisateur emprunteur) {
        this.emprunteur = emprunteur;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "EmpruntBibliotheque{" + "id=" + id + ", emprunteur=" + emprunteur + ", dateEmprunt=" + dateEmprunt + ", dateRetour=" + dateRetour + ", livre=" + livre + '}';
    }

    



}
