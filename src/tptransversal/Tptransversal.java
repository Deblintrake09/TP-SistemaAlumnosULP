/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tptransversal;

import Modelo.Conexion;
import Entidades.Alumno;
import java.time.LocalDate;
import Modelo.AlumnoData;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author jackd
 */
public class Tptransversal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Conexion c = new Conexion();
       
        /* 
        Alumno alum = new Alumno(32156561, "Juan Perez", LocalDate.of(1986, Month.JULY, 4), true);
        AlumnoData adata = new AlumnoData(c);   
        adata.guardarAlumno(alum);
        */
        

        //AlumnoData adata = new AlumnoData(c);   
        //adata.borrarAlumno(6);
       
       
       /*Alumno alum2 = adata.buscarAlumno(7);
       alum2.setNombreAlumno("Paco Perez");
       alum2.setLegajo(42353456);
       AlumnoData adata2 = new AlumnoData(new Conexion());
        adata2.actualizarAlumno(alum2);
        */
       
       AlumnoData adata2 = new AlumnoData(new Conexion());
        ArrayList<Alumno> alumnos= adata2.obtenerAlumnos();
        Iterator iter = alumnos.iterator();
        while(iter.hasNext())
        {
            iter.next().toString();
        }
        
    }
    
}
