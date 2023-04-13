/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controleur;

import dao.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Livre;
import model.Utilisateur;

/**
 *
 * @author khali
 */
public class UtilisateurControleur {
     private ArrayList<Utilisateur> tab= new ArrayList();
    	private Connection con;

    public UtilisateurControleur() {
        con=db.getCon();
    }
    
    public ArrayList<Utilisateur> getAll(){
        try {
            String sql="""
                       SELECT
                           `utilisateur`.`id`,
                           `utilisateur`.`nom`,
                           `utilisateur`.`prenom`,
                           `utilisateur`.`sex`,
                           `utilisateur`.`cin`,
                           `utilisateur`.`dateNais`,
                           `compte`.`UtilisateurID`,
                           `compte`.`RoleID`,
                           `role`.`id`
                       FROM
                           `utilisateur`
                       LEFT JOIN `compte` ON `compte`.`UtilisateurID` = `utilisateur`.`id`
                       LEFT JOIN `role` ON `compte`.`RoleID` = `role`.`id`
                       WHERE
                           RoleID = 1 OR RoleID IS NULL;""";
            tab= new ArrayList();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet sat = st.executeQuery();
            while(sat.next()){
                //java.util.Date utilDate = new java.util.Date(sat.getDate(6).getTime());

                tab.add(new Utilisateur( sat.getInt(1) , sat.getString(2),sat.getString(3),sat.getString(4),sat.getString(5),sat.getDate(6)));
            }
            return tab;
        } catch (SQLException e) {
            return null;
        }
      }
}
