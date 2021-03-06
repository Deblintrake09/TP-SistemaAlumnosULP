
package Modelo;

import Entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class AlumnoData {
    
    private Connection con;

    public AlumnoData(Conexion con) {
        this.con = con.getConnection();
    }
    
    public void guardarAlumno(Alumno alumno)
    {
        
        try
        {  
        String sql="SELECT * FROM alumno WHERE legajo = ?";
            PreparedStatement psi = con.prepareStatement(sql);
            psi.setInt(1, alumno.getLegajo());
            psi.executeQuery();
            ResultSet rsl = psi.getResultSet();
            //si no hay ninguna, la
            if(!rsl.next())
            {
                String query= "INSERT INTO alumno (legajo,nombre,fechaNac,activo) VALUES (?,?,?,?)";
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
                ps.close();
            }else 
            {
                JOptionPane.showMessageDialog(null, "El alumno ya se encuentra cargado con ID N° " + rsl.getString(1));
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo guardar Alumno");
        }
    }
    
    
    //Busca Un alumno acorde al ID
    public Alumno buscarAlumno(int id)
    {
        Alumno alum = new Alumno();
        
        String query= "SELECT * FROM alumno WHERE id_alumno= (?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
                
            ResultSet rs=ps.executeQuery();
        
            if(rs.next())
            {
                alum.setIdAlumno(rs.getInt(1));
                alum.setLegajo(rs.getInt(2));
                alum.setNombreAlumno(rs.getString(3));
                alum.setFechaNac(rs.getDate(4).toLocalDate());
                alum.setActivo(rs.getBoolean(5));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No se encontró el alumno con ID ingresado");
            }
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo Buscar");
        }
        return alum;
    }
    
    
    //BORRA POR ID. SIN GUARDAR LA DATA POR SI SE QUISIERA, SE PODRIA MEJORAR
    public void borrarAlumno(int id)
    {

        String query= "DELETE FROM alumno WHERE id_alumno= (?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
                
            int deleted = ps.executeUpdate();        
         
            if(deleted==0)
            {
                JOptionPane.showMessageDialog(null, "No se encontró el alumno con ID ingresado");
            }
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo Borrar");
        }
    }
    
    public void actualizarAlumno(Alumno alumno)
    {
        
        String query= "UPDATE alumno SET legajo=?, nombre=?, fechaNac=?, activo=? WHERE id_alumno=?";
        try
        {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, alumno.getLegajo());
            ps.setString(2, alumno.getNombreAlumno());
            ps.setDate(3, Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(4, alumno.isActivo());
            ps.setInt(5, alumno.getIdAlumno());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null, "No se pudo modificar - alumno no encontrado");
            }
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    
    public ArrayList<Alumno> obtenerAlumnos()
    {
        Alumno al;
        ArrayList<Alumno> alumnos = new ArrayList<>();
        
        String query= "SELECT * FROM alumno";
        try
        {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                
            
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                al=new Alumno();
                al.setIdAlumno(rs.getInt(1));
                al.setLegajo(rs.getInt(2));
                al.setNombreAlumno(rs.getString(3));
                al.setFechaNac(rs.getDate(4).toLocalDate());
                al.setActivo(rs.getBoolean(5));
                alumnos.add(al);
            }
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return alumnos;
    }
    
}
