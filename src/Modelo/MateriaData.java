package Modelo;

import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.JOptionPane;


public class MateriaData {
    private Connection con=null;

    public MateriaData(Conexion con) {
        
        this.con = con.getConnection();
        
    }
    
    //Cargar una Materia a la base de datos.
    
    public void cargarMateria(Materia materia){
        
        String query = "INSERT INTO materia(nombre) VALUES (?)";
        
        try {
  
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            
            ps.executeUpdate();
            
            ResultSet rs=ps.getGeneratedKeys();
            
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
            }
            else{
                JOptionPane.showMessageDialog(null, "No pudo obtener ID de la materia");
            }
            
            ps.close();

            } 
            catch (SQLException e){
            
            JOptionPane.showMessageDialog(null, "No pudo cargar la materia a la base de datos");
            
        }
    }
    //Buscar una materia por ID.
    
    public Materia buscarMateria(int id){
        
        Materia matter= null; 
        
        String query = "SELECT * FROM materia WHERE id_materia = ?";
        
        try {
            
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                matter = new Materia();
                matter.setIdMateria(rs.getInt(1));
                matter.setNombre(rs.getString(2));
                
            }
            
            ps.close();
            
        } catch (SQLException e){
            
            JOptionPane.showMessageDialog(null, "No pudo encontrar la materia");
        
        }
        
        return matter;
    }
    
    //Borrar un materia por su ID.
    
    public void borrarMateria(int id){
        
        String query = "DELETE FROM materia WHERE id_materia = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            
        } catch (SQLException e){
            
            JOptionPane.showMessageDialog(null, "No se borró la materia");
        
        }
    }
    
    //Mostrar todas las Materias.
    
    public List<Materia> mostrarMaterias(){
        
        Materia matter = null;
        
        List<Materia> materias = new ArrayList <>();
        
        String query ="SELECT * FROM materia";
        
        try {
            
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            
             while(rs.next()){ 
                matter = new Materia();
                matter.setIdMateria(rs.getInt(1));
                matter.setNombre(rs.getString(2));
                materias.add(matter);
            }
 
            ps.close();
            
        } 
        catch (SQLException e){
            
            JOptionPane.showMessageDialog(null, "No pudo encontrar materias");
            
        }
        
        return materias;
    } 
    
    //Actualizar una materia.
    
    public void actualizarMateria(Materia materia){
        
        String query="UPDATE materia SET nombre=? WHERE id_materia = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getIdMateria());
            ps.executeUpdate();
            
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                
                JOptionPane.showMessageDialog(null, "No pudo modificar");
                
            }
            ps.close();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.toString());
            
        }
    }
}