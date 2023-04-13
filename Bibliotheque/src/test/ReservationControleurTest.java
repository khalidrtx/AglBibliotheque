package test;

import Controleur.ReservationControleur;
import dao.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.Livre;
import org.junit.jupiter.api.*;

class ReservationControleurTest {

    private static Connection con;
    private static PreparedStatement st;
    private static ReservationControleur controleur;

 

    @Test
    public void testGetAll() {
        ReservationControleur rc = new ReservationControleur();
        ArrayList<Livre> livres = rc.getAll(1);
        assertNotNull(livres);
        assertTrue(!livres.isEmpty());
    }
    


}
