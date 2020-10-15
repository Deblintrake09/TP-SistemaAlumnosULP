package Modelo;

import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;;
import java.util.List;
import javax.swing.JOptionPane;


public class MateriaData {
    private Connection con;

    public MateriaData(Conexion con) {
        this.con = con.getConnection();
    }
    //cargar una Materia
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
                JOptionPane.showMessageDialog(null, "No se pudo obtener ID");
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede cargar la materia");
        }
        try {
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión. No se pudo cerrar la conexión");
        }
    }
    //Buscar una materia
    public Materia buscarMateria(int id){
        Materia m= null;        
        String query = "SELECT * FROM materia WHERE id_materia = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
             if(rs.next()){
                m= new Materia(); 
                m.setIdMateria(rs.getInt(1));
                m.setNombre(rs.getString(2));
            }
            
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontró la materia"  + e);
        
    }
    return m;
 }
    //Borrar Materia.
    public void borrarMateria(int id){
        String query = "DELETE FROM materia WHERE id_materia = ?";
    try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se borró la materia");
        
        }
    }
    //Mostrar todas las Materias.
    public List<Materia> mostrarMaterias(){
        Materia m = new Materia();
        List<Materia> materias = new ArrayList <>();
        String query ="SELECT * FROM materia";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
             while(rs.next()){ 
                m.setIdMateria(rs.getInt(1));
                m.setNombre(rs.getString(2));
                System.out.println(m.getNombre());
                materias.add(m);
            }
 
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron materias" + e);
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
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
    }
}