
package Controleur;
import dao.db;
import java.sql.*;
public class authetification {
    	private Connection con;

    public authetification() {
        con=db.getCon();
    }

    public int seConnecter(String login,String pass){
        try {
            String query="select UtilisateurID from compte where login=? and pass=?";
		PreparedStatement statement =con.prepareStatement(query);
		statement.setString(1, login);
		statement.setString(2, pass);
		ResultSet rs = statement.executeQuery();  
                while (rs.next())
               return  rs.getInt(1);
           return 0;      
        } catch (SQLException e) {
            return 0;
        }
    }   
    
      public boolean isAdmin(String login,String pass){
        try {
            String query="select * from compte where login=? and pass=? and RoleID=2";
		PreparedStatement statement =con.prepareStatement(query);
		statement.setString(1, login);
		statement.setString(2, pass);
		ResultSet rs = statement.executeQuery();  
                return rs.next();
        } catch (SQLException e) {
            return false;
        }
    } 
}
