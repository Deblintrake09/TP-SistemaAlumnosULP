package Modelo;

import java.sql.Connection;
import Entidades.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData 
{
    private Connection con;

    public InscripcionData(Conexion con) 
    {
        this.con = con.getConnection();
    }
    
    public void guardarInscripcion(Inscripcion inscripcion)
    {
        
        String query= "INSERT INTO inscripcion (id_inscripcion,id_alumno,id_materia,nota) VALUES (?,?,?,?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getIdInscripcion());
            ps.setInt(2, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(3, inscripcion.getMateria().getIdMateria());
            ps.setFloat(4, inscripcion.getNota());

            ps.executeUpdate();


            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
            {
                inscripcion.setIdInscripcion(rs.getInt(1));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No pudo Obtener ID");
            }
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo guardar Inscripción");
        }
    }
    
    
    public Inscripcion buscarInscripcion(int id)
    {
        Inscripcion insc = new Inscripcion();
        Alumno alum = new Alumno();
        Materia mate = new Materia();
                
        String query= "SELECT * FROM inscripcion WHERE id_inscripcion = (?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);    
            ResultSet rs=ps.executeQuery();
        
            if(rs.next())
            {
                insc.setIdInscripcion(rs.getInt(1));
                alum.setIdAlumno(rs.getInt(2));
                mate.setIdMateria(rs.getInt(3));
                insc.setNota(rs.getInt(4));
            }    
            else
            {
                JOptionPane.showMessageDialog(null, "No se encontró la inscripción con ID ingresado");
            }
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo Buscar " + e);
        }

        System.out.println("Número de incripción: " + insc.getIdInscripcion()
                + " - id alumno: " + alum.getIdAlumno() + " - id materia: " + mate.getIdMateria() + " - con nota: "+(int)insc.getNota());
        return insc;
    }
    
    
    public void borrarInscripcion(int id)
    {

        String query= "DELETE FROM inscripcion WHERE id_inscripcion = (?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
                
            int deleted = ps.executeUpdate();        
         
            if(deleted==0)
            {
                JOptionPane.showMessageDialog(null, "No se pudo borrar la inscripción con ID ingresado");
            }
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo Borrar" + e);
        }
    }
    
    
    public void actualizarNota(Inscripcion insc, float nota)
    {
        String query= "UPDATE inscripcion SET nota=? WHERE id_inscripcion=? ";
        //UPDATE inscripcion SET nota=3 WHERE id_inscripcion = 25
        try
        {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
       
            ps.setFloat(1,nota);
            ps.setInt(2, insc.getIdInscripcion());
                  
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar la nota");
            }
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public ArrayList<Inscripcion> obtenerInscripciones()
    {
        Inscripcion insc = new Inscripcion();
        Alumno alum = new Alumno();
        Materia mate = new Materia();
        
        ArrayList<Inscripcion> inscr = new ArrayList<>();
        
        String query= "SELECT inscripcion.id_inscripcion , alumno.nombre , materia.nombre, inscripcion.nota\n" +
                      "FROM inscripcion,alumno,materia\n" +
                      "WHERE inscripcion.id_alumno = alumno.id_alumno and inscripcion.id_materia = materia.id_materia";
        try
        {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                
            
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                insc.setIdInscripcion(rs.getInt(1));
                alum.setNombreAlumno(rs.getString(2));
                mate.setNombre(rs.getString(3));
                insc.setNota(rs.getFloat(4));
                System.out.print("ID Inscripción: ");
                System.out.print(insc.getIdInscripcion());
                System.out.println();
                System.out.print("Nombre del alumno: ");
                System.out.print(alum.getNombreAlumno());
                System.out.println();
                System.out.print("Nombre de la materia: ");
                System.out.print(mate.getNombre());
                System.out.println();
                System.out.print("Nota: ");
                System.out.print(insc.getNota());
                System.out.println();
                System.out.println("------------------------------------------");
                inscr.add(insc);
            
            }
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return inscr;
        
    }
    
    
    public List <Inscripcion> buscarInscripcionPorAlumno(int id){
        ArrayList<Inscripcion> inscpAl = new ArrayList<>();
        Inscripcion insc = null;
                
        String query= "SELECT * FROM inscripcion WHERE inscripcion.id_alumno = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, id);
            
            ResultSet rs=ps.executeQuery();
        
            while(rs.next())
            {
                insc=new Inscripcion();
                insc.setIdInscripcion(rs.getInt(1));
                Alumno a= buscarAlumno(rs.getInt(2));
                Materia m = buscarMateria(rs.getInt(3));
                insc.setAlumno(a);
                insc.setMateria(m);
                insc.setNota(rs.getFloat(4));    
                inscpAl.add(insc);
            }    
            
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo Buscar " + e);
        }
        
        return inscpAl;
        
    }
        public List <Inscripcion> buscarInscripcionPorMateria(int id){
        ArrayList<Inscripcion> inscpAl = new ArrayList<>();
        Inscripcion insc = null;
                
        String query= "SELECT * FROM inscripcion WHERE inscripcion.id_materia = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            
            
            ps.setInt(1, id);
            
                
            ResultSet rs=ps.executeQuery();
        
            while(rs.next())
            {
                insc=new Inscripcion();
                insc.setIdInscripcion(rs.getInt(1));
                Alumno a= buscarAlumno(rs.getInt(2));
                Materia m = buscarMateria(rs.getInt(3));
                insc.setAlumno(a);
                insc.setMateria(m);
                insc.setNota(rs.getFloat(4));    
                inscpAl.add(insc);
            }    
            
            ps.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error de Conexión - No se pudo Buscar " + e);
        }
        
        return inscpAl;
        
    }
        
     public Alumno buscarAlumno(int id){
        Conexion c = new Conexion();
        AlumnoData  ad = new AlumnoData(c);
        return ad.buscarAlumno(id);
    
    }
    
    public Materia buscarMateria(int id){
        Conexion c = new Conexion();
        MateriaData  ad = new MateriaData(c);
        return ad.buscarMateria(id);
    }
    
}
