package javaapplication1;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;

public class DatabaseConnector {
     Statement st = null ;
     Connection con = null ;
     ResultSet rs = null ;
     
 
    
    public DatabaseConnector()
    {
        try
        {
             con = DriverManager.getConnection("jdbc:mysql://localhost/university_management_system","root","");
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog(null,e.getMessage() );
        }
    }
    public void Insert(String posion,String name,String username,int age,String password)
    {
        String query = String.format("INSERT INTO %s (NAME,USER_NAME,PASSWORD,AGE) VALUES('%s','%s','%s',%d)",posion,name,username,password,age);

        try
        {
            st = con.createStatement();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getSQLState() );
        }
        try {
            int update = st.executeUpdate(query);

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getSQLState() );
        }

      try {
          st.close();
          con.close();
      }
      catch (SQLException e)
      {
        JOptionPane.showMessageDialog(null,e.getSQLState() );
      }
        
    }
    public void InsertCourse( String position,String username,String courseName )
    {
        String query = String.format("INSERT INTO COURSES (STATE,USER_NAME,COURSE_NAME) VALUES('%s','%s','%s')",position,username,courseName);
        
         try
        {
            st = con.createStatement();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getSQLState() );
        }
        try {
            int update = st.executeUpdate(query);

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getSQLState() );
        }

      try {
          st.close();
          con.close();
      }
      catch (SQLException e)
      {
        JOptionPane.showMessageDialog(null,e.getSQLState() );
      }
        
    }
    public String getdetail( String position,String username, String password)
    {
        String printdata = null;
       
         try
        {
            st = con.createStatement();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro" );
         
        }
         
        
        try
        {
             String q = String.format("SELECT * FROM COURSES AS C,%s AS E WHERE E.USER_NAME = C.USER_NAME AND E.USER_NAME = '%s' AND E.PASSWORD = '%s'", position,username,password);
             rs = st.executeQuery(q);
             
        }
          catch (SQLException e1)
        {
            JOptionPane.showMessageDialog(null, "Erro");
           
        }
        
                while (true)
        {
            try {
                if (!rs.next()) break;
            }
            catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Erro");
            }
            try {
                if (printdata == null )
                {
                    printdata = rs.getString(4);
                }
                else
                    printdata = printdata + "," + rs.getString(4);
               
            } 
            catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,"Erro");
               
            }
            
        }

        try {
            st.close();
            con.close();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Erro");
        }
        try {
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro");
        }
         return printdata;
                
        
    }
    public String getprofile( String position,String username, String password)
    {
       
        String printdata = null;
        int count = 0;
         try
        {
            st = con.createStatement();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro1" );
         
        }
         
      
        try
        {
            String q = String.format("SELECT * FROM %s WHERE USER_NAME = '%s' AND PASSWORD = '%s'", position,username,password);
             rs = st.executeQuery(q);
             
        }
          catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null, "Erro2");
           
        }
        
                while (true)
        {
            try {
                if (!rs.next()) break;
            }
            catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Erro3");
            }
            try {
                if( count == 0 )
                {
                     printdata = rs.getString(3) + "," + rs.getString(2);
                     count++;
                }
               
               
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro4");
               
            }
            
        }

        try {
            st.close();
            con.close();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Erro5");
        }
        try {
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro6");
        }
         return printdata;
                
        
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
