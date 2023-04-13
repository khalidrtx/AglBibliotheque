package test;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;

import model.Livre;
import model.ReservationBibliotheque;
import model.Utilisateur;

public class ReservationBibliothequeTest {

	@Test
	public void testGetId() {
		ReservationBibliotheque reservation = new ReservationBibliotheque(1, new Date(), "en attente", new Utilisateur(), new Livre());
		assertEquals(1, reservation.getId());
	}

	@Test
	public void testSetId() {
		ReservationBibliotheque reservation = new ReservationBibliotheque();
		reservation.setId(2);
		assertEquals(2, reservation.getId());
	}

	@Test
	public void testGetDate() {
		Date date = new Date();
		ReservationBibliotheque reservation = new ReservationBibliotheque(1, date, "en attente", new Utilisateur(), new Livre());
		assertEquals(date, reservation.getDate());
	}

	@Test
	public void testSetDate() {
		Date date = new Date();
		ReservationBibliotheque reservation = new ReservationBibliotheque();
		reservation.setDate(date);
		assertEquals(date, reservation.getDate());
	}

	@Test
	public void testGetEtat() {
		ReservationBibliotheque reservation = new ReservationBibliotheque(1, new Date(), "en attente", new Utilisateur(), new Livre());
		assertEquals("en attente", reservation.getEtat());
	}

	@Test
	public void testSetEtat() {
		ReservationBibliotheque reservation = new ReservationBibliotheque();
		reservation.setEtat("confirmée");
		assertEquals("confirmée", reservation.getEtat());
	}

	@Test
	public void testGetReservataire() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("khalid");
		utilisateur.setPrenom("Elbouari");
		utilisateur.setSexe("H");		ReservationBibliotheque reservation = new ReservationBibliotheque(1, new Date(), "en attente", utilisateur, new Livre());
		assertEquals(utilisateur, reservation.getReservataire());
	}

	@Test
	public void testSetReservataire() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("khalid");
		utilisateur.setPrenom("Elbouari");
		utilisateur.setSexe("H");

		ReservationBibliotheque reservation = new ReservationBibliotheque();
		reservation.setReservataire(utilisateur);
		assertEquals(utilisateur, reservation.getReservataire());
	}

	@Test
	public void testGetLivre() {
        Livre livre = new Livre(1, "1234567890", "Titre", "Auteur", "Description");
		ReservationBibliotheque reservation = new ReservationBibliotheque(1, new Date(), "en attente", new Utilisateur(), livre);
		assertEquals(livre, reservation.getLivre());
	}

	@Test
	public void testSetLivre() {
        Livre livre = new Livre(1, "1234567890", "Titre", "Auteur", "Description");
		ReservationBibliotheque reservation = new ReservationBibliotheque();
		reservation.setLivre(livre);
		assertEquals(livre, reservation.getLivre());
	}

	@Test
	public void testToString() {
		ReservationBibliotheque reservation = new ReservationBibliotheque(1, new Date(), "en attente", new Utilisateur(), new Livre());
		assertEquals("ReservationBibliotheque [id=1, date=" + reservation.getDate() + ", etat=en attente, reservataire=" + reservation.getReservataire() + ", livre=" + reservation.getLivre() + "]", reservation.toString());
	}

}
