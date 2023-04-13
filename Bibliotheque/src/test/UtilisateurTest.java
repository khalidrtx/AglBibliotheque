package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Utilisateur;

class UtilisateurTest {

    private Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {
        utilisateur = new Utilisateur(1, "khalid", "Elbouari", "M", "AB123456", new Date());
    }

    @Test
    public void testGetId() {
        assertEquals(1, utilisateur.getId());
    }

    @Test
    public void testGetNom() {
        assertEquals("khalid", utilisateur.getNom());
    }

    @Test
    public void testGetPrenom() {
        assertEquals("Elbouari", utilisateur.getPrenom());
    }

    @Test
    public void testGetSexe() {
        assertEquals("M", utilisateur.getSexe());
    }

    @Test
    public void testGetCin() {
        assertEquals("AB123456", utilisateur.getCin());
    }

    @Test
    public void testGetDateNais() {
        assertTrue(utilisateur.getDateNais() instanceof Date);
    }

    @Test
    public void testToString() {
        String expected = "Utilisateur{id=1, nom=khalid, prenom=Elbouari, sexe=M, cin=AB123456, dateNais=" + utilisateur.getDateNais() + "}";
        assertEquals(expected, utilisateur.toString());
    }

}
