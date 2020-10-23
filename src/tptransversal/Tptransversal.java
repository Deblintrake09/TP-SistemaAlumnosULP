
package tptransversal;

import Modelo.*;
import Entidades.*;
import java.time.LocalDate;
import Modelo.AlumnoData;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tptransversal 
{    
    public static void main(String[] args) 
    {
        
     //Creamos un Alumo y lo guardamos
      Alumno alum = new Alumno(23066666, "Correa Claudio", LocalDate.of(1980, Month.JANUARY,25), true);
      alum.toString();
      AlumnoData adata = new AlumnoData(new Conexion());   
      adata.guardarAlumno(alum);
      
      //borramos el alumno que acabamos de crear usando su ID (que es guardado en el alumno cuando se lo carga
      adata.borrarAlumno(alum.getIdAlumno());
      
      //Creamos un alumno y lo guardamos, luego modificamos datos y lo actualizamos en el sistema
      alum = new Alumno(32456674, "Juan Lorenzo Paez", LocalDate.of(1982, Month.FEBRUARY,8), false);
      adata.guardarAlumno(alum);
      alum.setActivo(true);
      alum.setNombreAlumno("Juan Manuel Lorenzo Paez");
      adata.actualizarAlumno(alum);
      
      
      
      //Cargamos varios Alumnos. Luego la Lista de Alumnos Guardados y Se impriime por consola
      alum = new Alumno(30145758, "Gabriela Gutierrez", LocalDate.of(1983, Month.JANUARY,15), true);
      adata.guardarAlumno(alum);
      alum = new Alumno(31578968, "Jorge Fernandez", LocalDate.of(1983, Month.JULY,20), true);
      adata.guardarAlumno(alum);
      alum = new Alumno(33456787, "José Martinez", LocalDate.of(1983, Month.OCTOBER,7), true);
      adata.guardarAlumno(alum);
      alum = new Alumno(36759874, "Gabriela Gutierrez", LocalDate.of(1990, Month.JUNE,6), false);
      adata.guardarAlumno(alum);
      
      ArrayList<Alumno> alumnos= adata.obtenerAlumnos();
        Iterator iter = alumnos.iterator();
        while(iter.hasNext())
        {
            iter.next().toString();
        }
            
// MATERIAS        
       Materia mat = new Materia("Matemática 1");
       MateriaData mdata = new MateriaData(new Conexion());  
       mdata.cargarMateria(mat);
       System.out.println("Id de materia agregada = " + mat.getIdMateria());
       
       
       mdata.borrarMateria(mat.getIdMateria());
       
       
       mat = new Materia("Matemática 1");
       mdata.cargarMateria(mat);
       mat = new Materia("Laboratorio de Algoritmos 1");
       mdata.cargarMateria(mat);
       mat = new Materia("Estructura de Datos");
       mdata.cargarMateria(mat);
       mat = new Materia("Programación Web");
       mdata.cargarMateria(mat);
       
       Materia mat2 = mdata.buscarMateria(3);
       System.out.println(mat2.toString());
        
        List<Materia> materias= mdata.mostrarMaterias();
        iter = materias.iterator();
        while(iter.hasNext())
        {
            iter.next().toString();
        }
            
//INSCRIPCIONES

        mat= mdata.buscarMateria(16);
        alum= adata.buscarAlumno(53);
        Inscripcion insc = new Inscripcion(alum,mat, -1);
        InscripcionData idata = new InscripcionData(new Conexion());
        idata.guardarInscripcion(insc);
        
        
        insc= idata.buscarInscripcion(1);
        idata.borrarInscripcion(insc.getIdInscripcion());
       
        insc = new Inscripcion(alum,mat, -1);
        idata.guardarInscripcion(insc);
        insc = idata.buscarInscripcion(2);
        idata.actualizarNota(insc, 7.5f);
        
        
        ArrayList<Inscripcion> inscripciones= idata.obtenerInscripciones();
        iter = inscripciones.iterator();
        while(iter.hasNext())
        {
            iter.next().toString();
        }              
    }
    
}
