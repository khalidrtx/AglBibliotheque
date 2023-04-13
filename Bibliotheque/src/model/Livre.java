
package model;

import java.util.ArrayList;

/**
 *
 * @author khali
 */

public class Livre {
	private int id;
	private String isbn;
	private String titre;
	private String auteur;
	private String description;
    	public ArrayList<ReservationBibliotheque> reservations = new ArrayList();

    public Livre() {
    }

    public Livre(int id, String isbn, String titre, String auteur, String description) {
        this.id = id;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.description = description;
    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<ReservationBibliotheque> getReservation() {
		return reservations;
	}
	/*
	 * Ajouter une reservation
	 */
	public void addReservation(ReservationBibliotheque reservation) {
		this.reservations.add(reservation);
	}

    @Override
    public String toString() {
        return "Livre{" + "id=" + id + ", isbn=" + isbn + ", titre=" + titre + ", auteur=" + auteur + ", description=" + description + ", reservations=" + reservations + '}';
    }

 


}
