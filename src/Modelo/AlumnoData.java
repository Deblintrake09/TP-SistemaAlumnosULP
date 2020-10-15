package Modelo;

import Entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



public class AlumnoData {
    
    private Connection con;

    public AlumnoData(Conexion con) {
        this.con = con.getConnection();
    }
    
    public void guardarAlumno(Alumno alumno)
    {
    //String query= "INSERT INTO alumno ('legajo','nombre','fechaNac','activo') "+"VALUES (?,?,?,?)";
    String query= "INSERT INTO alumno ( legajo, nombre, fechaNac, activo) VALUES (?,?,?,?)";
    try
    {
    PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    ps.setInt(1, alumno.getLegajo());
    ps.setString(2, alumno.getNombreAlumno());
    ps.setDate(3, Date.valueOf(alumno.getFechaNac()));
    ps.setBoolean(4, alumno.isActivo());

    ps.executeUpdate();
    
    
    ResultSet rs=ps.getGeneratedKeys();
    if(rs.next())
    {
        alumno.setIdAlumno(rs.getInt(1));
    }else
    {
        JOptionPane.showMessageDialog(null, "No pudo Obtener ID");
    }
            con.close();
    }
    catch(SQLException e)
    {
        
       JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo guardar Alumno");
        
    }
    try {
        con.close();
    }
    catch(SQLException e)
    {
        JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo cerrar la conexión");
        
    }
       
    }
     
    
}
