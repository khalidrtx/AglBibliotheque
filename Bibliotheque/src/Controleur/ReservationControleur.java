
package Controleur;

import dao.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Livre;
import model.ReservationBibliotheque;
import model.Utilisateur;

/**
 *
 * @author khali
 */
public class ReservationControleur {
    private Connection con;
    private ArrayList<Livre> tab;


    public ReservationControleur() {
          con=db.getCon();
          tab= new ArrayList();
      
    }

    public ArrayList<Livre> getTab() {
        return tab;
    }
    public void addLivre(Livre l){
        tab.add(l);
    }
     public ArrayList<Livre> getAll(int userId){
            String sql="""
                     SELECT
                         `livre`.`id`,
                         `livre`.`isbn`,
                         `livre`.`titre`,
                         `livre`.`auteur`,
                         `livre`.`description`,
                         `reservationbibliotheque`.`livre`,
                         `reservationbibliotheque`.`reservataire`
                       FROM
                         `livre`
                         LEFT JOIN `reservationbibliotheque`
                           ON `reservationbibliotheque`.`livre` = `livre`.`id`
                           AND `reservationbibliotheque`.`reservataire` = ?
                       WHERE
                         (`reservationbibliotheque`.`reservataire` IS NULL) OR
                         (`reservationbibliotheque`.`reservataire` <> ?)""";
         
           try {
                tab= new ArrayList();
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, userId);

            ResultSet rs=st.executeQuery();
                 while(rs.next()){
                tab.add(new Livre( rs.getInt(1) , rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                 }
            return tab;
        } catch (SQLException ex) {
            return null;
        }
    
     }
    public void reserver(int userId,int LivreId){
             try {
            String query="insert into reservationbibliotheque(reservataire,livre) values(?,?) ";
		PreparedStatement statement =con.prepareStatement(query);
		statement.setInt(1, userId);
		statement.setInt(2, LivreId);
		statement.executeUpdate();
              
        } catch (SQLException e) {
        }
    }
      public ArrayList<Livre> search(int userId,String val){
        try {
            String sql="""
                       SELECT
                                                  `livre`.`id` ,
                                                  `livre`.`isbn`,
                                                  `livre`.`titre`,
                                                  `livre`.`auteur`,
                                                  `livre`.`description`,
                                                  `reservationbibliotheque`.`livre`,
                                                  `reservationbibliotheque`.`reservataire`
                                              FROM
                                                  `livre`
                                              LEFT JOIN
                                                  `reservationbibliotheque`
                                              ON
                                                  `reservationbibliotheque`.`livre` = `livre`.`id`
                                                  where (`reservationbibliotheque`.`reservataire` <> ? or `reservationbibliotheque`.`reservataire` is null) and titre like ? """;
            PreparedStatement st=con.prepareStatement(sql);
            ArrayList<Livre> tab = new ArrayList();
             st.setInt(1, userId);
             st.setString(12, val+"%");
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                tab.add(new Livre( rs.getInt(1) , rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
              return tab;
        } catch (SQLException e) {
            return null;
        }
    }
      
        public ArrayList<ReservationBibliotheque> getAllReservedLivres(){
            String sql="""
                       SELECT
                           `utilisateur`.`id` as 'utilisateurid',
                           `utilisateur`.`nom` as 'utilisateurnom',
                           `utilisateur`.`prenom` as 'utilisateurprenom',
                           `utilisateur`.`sex` as 'utilisateursex',
                           `utilisateur`.`cin` as 'utilisateurCin',
                           `utilisateur`.`dateNais` as 'utilisateurDateNais',
                           `reservationbibliotheque`.`id` as 'reservationbibliothequeId',
                           `reservationbibliotheque`.`dateR` as 'reservationbibliothequeDateR',
                           `reservationbibliotheque`.`etat` as 'reservationbibliothequeEtat',
                           `reservationbibliotheque`.`reservataire` as 'reservationbibliothequeReservataire',
                           `reservationbibliotheque`.`livre` as 'reservationbibliothequeLiver',
                            `livre`.`id` as 'livreid',
                            `livre`.`isbn` as 'livreisbn',
                            `livre`.`titre` as 'livretitre',
                            `livre`.`auteur` as 'livreauteur',
                            `livre`.`description` as 'livredescription'
                       FROM
                           `utilisateur`
                       INNER JOIN `reservationbibliotheque` ON `reservationbibliotheque`.`reservataire` = `utilisateur`.`id`
                       INNER JOIN `livre` ON `reservationbibliotheque`.`livre` = `livre`.`id` and `reservationbibliotheque`.`etat` = 'suspendu';""";
         
           try {
               ArrayList<ReservationBibliotheque>  tab= new ArrayList();
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            Livre l;
            Utilisateur u;
                 while(rs.next()){
                    u=new Utilisateur();
                    u.setId(rs.getInt("utilisateurid"));
                    u.setCin(rs.getString("utilisateurid"));
                    u.setDateNais(rs.getDate("utilisateurDateNais"));
                    u.setNom(rs.getString("utilisateurnom"));
                    u.setPrenom(rs.getString("utilisateurprenom"));
                    u.setSexe(rs.getString("utilisateursex"));
                    l=new Livre();
                    l.setId(rs.getInt("livreid"));
                    l.setAuteur(rs.getString("livreauteur"));
                    l.setIsbn(rs.getString("livreisbn"));
                    l.setTitre(rs.getString("livretitre"));
                    l.setDescription(rs.getString("livredescription"));
                ReservationBibliotheque r=new ReservationBibliotheque();    
                r.setId(rs.getInt("reservationbibliothequeId"));
                r.setLivre(l);
                r.setReservataire(u);
                r.setEtat(rs.getString("reservationbibliothequeEtat"));
                r.setDate(rs.getDate("reservationbibliothequeDateR"));
                tab.add(r);
                 }
            return tab;
        } catch (SQLException ex) {
            return null;
        }
    
     }
         public ArrayList<ReservationBibliotheque> Search(String texte){
            String sql="""
                       SELECT
                           `utilisateur`.`id` as 'utilisateurid',
                           `utilisateur`.`nom` as 'utilisateurnom',
                           `utilisateur`.`prenom` as 'utilisateurprenom',
                           `utilisateur`.`sex` as 'utilisateursex',
                           `utilisateur`.`cin` as 'utilisateurCin',
                           `utilisateur`.`dateNais` as 'utilisateurDateNais',
                           `reservationbibliotheque`.`id` as 'reservationbibliothequeId',
                           `reservationbibliotheque`.`dateR` as 'reservationbibliothequeDateR',
                           `reservationbibliotheque`.`etat` as 'reservationbibliothequeEtat',
                           `reservationbibliotheque`.`reservataire` as 'reservationbibliothequeReservataire',
                           `reservationbibliotheque`.`livre` as 'reservationbibliothequeLiver',
                            `livre`.`id` as 'livreid',
                            `livre`.`isbn` as 'livreisbn',
                            `livre`.`titre` as 'livretitre',
                            `livre`.`auteur` as 'livreauteur',
                            `livre`.`description` as 'livredescription'
                       FROM
                           `utilisateur`
                       INNER JOIN `reservationbibliotheque` ON `reservationbibliotheque`.`reservataire` = `utilisateur`.`id`
                       INNER JOIN `livre` ON `reservationbibliotheque`.`livre` = `livre`.`id` and `reservationbibliotheque`.`etat` = 'suspendu'
                       and (`utilisateur`.`nom` like ? or `utilisateur`.`prenom` like ?) ;""";
         
           try {
               ArrayList<ReservationBibliotheque>  tab= new ArrayList();
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,texte+"%");
            st.setString(2,texte+"%");
            ResultSet rs=st.executeQuery();
            Livre l;
            Utilisateur u;
                 while(rs.next()){
                    u=new Utilisateur();
                    u.setId(rs.getInt("utilisateurid"));
                    u.setCin(rs.getString("utilisateurid"));
                    u.setDateNais(rs.getDate("utilisateurDateNais"));
                    u.setNom(rs.getString("utilisateurnom"));
                    u.setPrenom(rs.getString("utilisateurprenom"));
                    u.setSexe(rs.getString("utilisateursex"));
                    l=new Livre();
                    l.setId(rs.getInt("livreid"));
                    l.setAuteur(rs.getString("livreauteur"));
                    l.setIsbn(rs.getString("livreisbn"));
                    l.setTitre(rs.getString("livretitre"));
                    l.setDescription(rs.getString("livredescription"));
                ReservationBibliotheque r=new ReservationBibliotheque();    
                r.setId(rs.getInt("reservationbibliothequeId"));
                r.setLivre(l);
                r.setReservataire(u);
                r.setEtat(rs.getString("reservationbibliothequeEtat"));
                r.setDate(rs.getDate("reservationbibliothequeDateR"));
                tab.add(r);
                 }
            return tab;
        } catch (SQLException ex) {
            return null;
        }
    
     }
      
          public ReservationBibliotheque getAllReservedLivresById(int id){
            String sql="""
                       SELECT
                           `utilisateur`.`id` as 'utilisateurid',
                           `utilisateur`.`nom` as 'utilisateurnom',
                           `utilisateur`.`prenom` as 'utilisateurprenom',
                           `utilisateur`.`sex` as 'utilisateursex',
                           `utilisateur`.`cin` as 'utilisateurCin',
                           `utilisateur`.`dateNais` as 'utilisateurDateNais',
                           `reservationbibliotheque`.`id` as 'reservationbibliothequeId',
                           `reservationbibliotheque`.`dateR` as 'reservationbibliothequeDateR',
                           `reservationbibliotheque`.`etat` as 'reservationbibliothequeEtat',
                           `reservationbibliotheque`.`reservataire` as 'reservationbibliothequeReservataire',
                           `reservationbibliotheque`.`livre` as 'reservationbibliothequeLiver',
                            `livre`.`id` as 'livreid',
                            `livre`.`isbn` as 'livreisbn',
                            `livre`.`titre` as 'livretitre',
                            `livre`.`auteur` as 'livreauteur',
                            `livre`.`description` as 'livredescription'
                       FROM
                           `utilisateur`
                       INNER JOIN `reservationbibliotheque` ON `reservationbibliotheque`.`reservataire` = `utilisateur`.`id`
                       INNER JOIN `livre` ON `reservationbibliotheque`.`livre` = `livre`.`id` and `reservationbibliotheque`.`etat` = 'suspendu'
                       and `reservationbibliotheque`.`id` =? ;""";
         
           try {
               ArrayList<ReservationBibliotheque>  tab= new ArrayList();
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs=st.executeQuery();
            Livre l;
            Utilisateur u;
            ReservationBibliotheque r = null;
                 while(rs.next()){
                    u=new Utilisateur();
                    u.setId(rs.getInt("utilisateurid"));
                    u.setCin(rs.getString("utilisateurid"));
                    u.setDateNais(rs.getDate("utilisateurDateNais"));
                    u.setNom(rs.getString("utilisateurnom"));
                    u.setPrenom(rs.getString("utilisateurprenom"));
                    u.setSexe(rs.getString("utilisateursex"));
                    l=new Livre();
                    l.setId(rs.getInt("livreid"));
                    l.setAuteur(rs.getString("livreauteur"));
                    l.setIsbn(rs.getString("livreisbn"));
                    l.setTitre(rs.getString("livretitre"));
                    l.setDescription(rs.getString("livredescription"));
                    r=new ReservationBibliotheque();    
                   r.setId(rs.getInt("reservationbibliothequeId"));
                   r.setLivre(l);
                   r.setReservataire(u);
                   r.setEtat(rs.getString("reservationbibliothequeEtat"));
               
                 }
            return r;
        } catch (SQLException ex) {
            return null;
        }
    
     }
      
       public void valider(int reservationID){
             try {
            String query="UPDATE `reservationbibliotheque` SET `etat` = 'valider' WHERE `reservationbibliotheque`.`id` = ?; ";
		PreparedStatement statement =con.prepareStatement(query);
		statement.setInt(1, reservationID);
		statement.executeUpdate();
        } catch (SQLException e) {
        }
    }
         public void Supprimer(int reservationID){
             try {
            String query="DELETE FROM `reservationbibliotheque` WHERE `reservationbibliotheque`.`id` = ?";
		PreparedStatement statement =con.prepareStatement(query);
		statement.setInt(1, reservationID);
		statement.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
