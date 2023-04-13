package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Controleur.LivreControleur;
import dao.db;
import model.Livre;

public class LivreControleurTest {
    private LivreControleur livreControleur;

    @Before
    public void setUp() throws SQLException {
      //  db.init();
        livreControleur = new LivreControleur();
    }

//    @After
//    public void tearDown() throws SQLException {
//        db.close();
//    }

    @Test
    public void testGetAll() {
        ArrayList<Livre> livres = livreControleur.getAll();
        assertNotNull(livres);
        assertTrue(livres.size() > 0);
    }

    @Test
    public void testAdd() {
        String isbn = "12345";
        String titre = "Test Livre";
        String auteur = "Test Auteur";
        String description = "Test Description";
        livreControleur.add(isbn, titre, auteur, description);

        ArrayList<Livre> livres = livreControleur.getAll();
        Livre lastLivre = livres.get(livres.size() - 1);

        assertEquals(isbn, lastLivre.getIsbn());
        assertEquals(titre, lastLivre.getTitre());
        assertEquals(auteur, lastLivre.getAuteur());
        assertEquals(description, lastLivre.getDescription());
    }

    @Test
    public void testSupprimer() {
        ArrayList<Livre> livres = livreControleur.getAll();
        int countBefore = livres.size();

        Livre livre = livres.get(0);
        int id = livre.getId();
        livreControleur.Supprimer(id);

        livres = livreControleur.getAll();
        int countAfter = livres.size();

        assertEquals(countBefore - 1, countAfter);
        assertNull(livreControleur.getById(id));
    }

    @Test
    public void testUpdate() {
        ArrayList<Livre> livres = livreControleur.getAll();
        Livre livre = livres.get(0);

        String newIsbn = "67890";
        String newTitre = "New Titre";
        String newAuteur = "New Auteur";
        String newDescription = "New Description";

        livreControleur.update(livre.getId(), newIsbn, newTitre, newAuteur, newDescription);
        Livre updatedLivre = livreControleur.getById(livre.getId());

        assertEquals(newIsbn, updatedLivre.getIsbn());
        assertEquals(newTitre, updatedLivre.getTitre());
        assertEquals(newAuteur, updatedLivre.getAuteur());
        assertEquals(newDescription, updatedLivre.getDescription());
    }

    @Test
    public void testSearch() {
        ArrayList<Livre> livres = livreControleur.search("Test");
        assertNotNull(livres);
        assertTrue(!livres.isEmpty());
    }

    @Test
    public void testGetById() {
        ArrayList<Livre> livres = livreControleur.getAll();
        Livre livre = livres.get(0);

        assertNotNull(livreControleur.getById(livre.getId()));
        assertNull(livreControleur.getById(-1));
    }
}