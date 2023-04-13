
package Controleur;
import dao.db;
import java.sql.*;
import java.util.ArrayList;
import model.EmpruntBibliotheque;
import model.Livre;
import model.Utilisateur;

public class EmpruntControleur {
     private Connection con;
    private ArrayList<Livre> tab;


    public EmpruntControleur() {
          con=db.getCon();
          tab=new ArrayList();
    }
    
    public void Emprunt(int userId, Date d,int LivreId){
             try {
            String query="INSERT INTO `empruntbibliotheque` (`id`, `emprunteur`,  `dateRetour`, `livre`) VALUES (NULL, ?, ?, ?);";
		PreparedStatement statement =con.prepareStatement(query);
		statement.setInt(1, userId);
                statement.setDate(2, d);
		statement.setInt(3, LivreId);
		statement.executeUpdate();
        } catch (SQLException e) {
        }
    }
    public void Rendre(int userId){
         try {
            String query="DELETE FROM `empruntbibliotheque` WHERE `empruntbibliotheque`.`id` = ?";
		PreparedStatement statement =con.prepareStatement(query);
		statement.setInt(1, userId);
		statement.executeUpdate();
        } catch (SQLException e) {
        }
    }
       public ArrayList<EmpruntBibliotheque> getAllById(int userId){
            String sql="""
                    SELECT
                         `empruntbibliotheque`.`id`,
                         `empruntbibliotheque`.`emprunteur`,
                         `empruntbibliotheque`.`dateEmprunt`,
                         `empruntbibliotheque`.`dateRetour`,
                         `livre`.`id`,
                         `livre`.`isbn`,
                         `livre`.`titre`,
                         `livre`.`auteur`,
                         `livre`.`description`
                     FROM
                         `empruntbibliotheque`
                     INNER JOIN `livre` ON `empruntbibliotheque`.`livre` = `livre`.`id`
                     INNER JOIN `utilisateur` ON `empruntbibliotheque`.`emprunteur` = `utilisateur`.`id` WHERE  `empruntbibliotheque`.`emprunteur` = ?;""";
         
           try {
            ArrayList<EmpruntBibliotheque>   t= new ArrayList();
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs=st.executeQuery();
            Livre l;
            EmpruntBibliotheque e;
            Utilisateur u;
                 while(rs.next()){
                     u=new Utilisateur();
                     u.setId(rs.getInt(2));
                     l=new Livre(rs.getInt(5) , rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                     e=new EmpruntBibliotheque(rs.getInt(1),u,rs.getDate(3),rs.getDate(4),l);
                     t.add(e);
                 }
            return t;
        } catch (SQLException ex) {
            return null;
        }
    
     }
       
        public ArrayList<EmpruntBibliotheque> getAll(){
            String sql="""
                    SELECT
                         `empruntbibliotheque`.`id`,
                         `empruntbibliotheque`.`emprunteur`,
                         `empruntbibliotheque`.`dateEmprunt`,
                         `empruntbibliotheque`.`dateRetour`,
                         `livre`.`id`,
                         `livre`.`isbn`,
                         `livre`.`titre`,
                         `livre`.`auteur`,
                         `livre`.`description`,
                        `utilisateur`.`id` as 'utilisateurid',
                         `utilisateur`.`nom` as 'utilisateurnom',
                         `utilisateur`.`prenom` as 'utilisateurprenom',
                         `utilisateur`.`sex` as 'utilisateursex',
                        `utilisateur`.`cin` as 'utilisateurCin'
                     FROM
                         `empruntbibliotheque`
                     INNER JOIN `livre` ON `empruntbibliotheque`.`livre` = `livre`.`id`
                     INNER JOIN `utilisateur` ON `empruntbibliotheque`.`emprunteur` = `utilisateur`.`id` """;
         
           try {
            ArrayList<EmpruntBibliotheque>   t= new ArrayList();
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            Livre l;
            EmpruntBibliotheque e;
            Utilisateur u;
                 while(rs.next()){
                     u=new Utilisateur();
                     u.setId(rs.getInt(2));
                     u.setNom(rs.getString("utilisateurnom"));
                     u.setPrenom(rs.getString("utilisateurprenom"));
                     l=new Livre(rs.getInt(5) , rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                     e=new EmpruntBibliotheque(rs.getInt(1),u,rs.getDate(3),rs.getDate(4),l);
                     t.add(e);
                 }
            return t;
        } catch (SQLException ex) {
            return null;
        }
    
     }
}
