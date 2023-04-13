
package Controleur;

import dao.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Livre;

/**
 *
 * @author khali
 */
public class LivreControleur {
    private ArrayList<Livre> tab= new ArrayList();
    	private Connection con;

    public LivreControleur() {
        con=db.getCon();
    }
    
        public ArrayList<Livre> getAll(){
        try {
            tab= new ArrayList();
            Statement st = con.createStatement();
            ResultSet sat = st.executeQuery("select * from livre");
            while(sat.next()){
                tab.add(new Livre( sat.getInt(1) , sat.getString(2),sat.getString(3),sat.getString(4),sat.getString(5)));
            }
            return tab;
        } catch (SQLException e) {
            return null;
        }
      }
    
    public void add(String isbn,String titre,String A,String d){
    String sql="insert into livre values(null,?,?,?,?)";
        try {
            PreparedStatement st=con.prepareStatement(sql);
            
            st.setString(1, isbn);
            st.setString(2, titre);
            st.setString(3, A);
            st.setString(4, d);

            st.executeUpdate();

           // ResultSet rs=st.executeQuery(sql);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex.getMessage());
        }
      
}  
     public void Supprimer(int id){
         String sql="delete from  livre where id = ?";
        try {
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
           // ResultSet rs=st.executeQuery(sql);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex.getMessage());
        }
      
}  
    public void update(int id,String isbn,String titre,String A,String d){
            String sql="update livre set isbn=?, titre=? , auteur=?, description=? where id=?";
        try {
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, isbn);
            st.setString(2, titre);
            st.setString(3, A);
            st.setString(4, d);
            st.setInt(5, id);
            st.executeUpdate();

           // ResultSet rs=st.executeQuery(sql);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex.getMessage());
        }
      
} 
    
    public ArrayList<Livre> search(String val){
        try {
            Statement st =  con.createStatement();
            ArrayList<Livre> tab = new ArrayList();
            ResultSet rs= st.executeQuery("select * from livre where titre like '"+ val+"%' ");
            while(rs.next()){
                tab.add(new Livre( rs.getInt(1) , rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
              return tab;
        } catch (SQLException e) {
            return null;
        }
    }
      public Livre getById(int id){
        String sql="""
                   SELECT
                       `livre`.`id`,
                       `livre`.`isbn`,
                       `livre`.`titre`,
                       `livre`.`auteur`,
                       `livre`.`description`
                       
                   FROM
                       `livre`
                    where `livre`.`id` =?;""";
        try {
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs=st.executeQuery();
            Livre l = null;
             while(rs.next()){
                 l =new Livre( rs.getInt(1) , rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
             return l;
        } catch (SQLException ex) {
            return null;
        }
      }
    
    public ArrayList<Livre> getAllByUser(int id){
        String sql="""
                   SELECT
                       `livre`.`id`,
                       `livre`.`isbn`,
                       `livre`.`titre`,
                       `livre`.`auteur`,
                       `livre`.`description`,
                       `reservationbibliotheque`.`reservataire`,
                       `reservationbibliotheque`.`livre`
                   FROM
                       `livre`
                   INNER JOIN `reservationbibliotheque` ON `reservationbibliotheque`.`livre` = `livre`.`id` where reservataire =?;""";
        try {
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs=st.executeQuery();
             while(rs.next()){
                tab.add(new Livre( rs.getInt(1) , rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
             return tab;
        } catch (SQLException ex) {
            return null;
        }
      }
}
