
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author khali
 */
public class db {
    private static Connection con =null;
    private static db ct=null;

    private  db() {
        try {
           
                con=DriverManager.getConnection("jdbc:mysql://localhost/agl","root","");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()); 
        }
    }
    public static Connection getCon(){
        if(ct==null)
            ct = new db();
        return con;
    }
    
    public static void main(String[] args){
       db.getCon();
    }
    
}
    

