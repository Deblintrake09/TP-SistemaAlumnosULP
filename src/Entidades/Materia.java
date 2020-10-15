/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author jackd
 */
public class Materia {
    
    private int id_materia;
    private String nombre;

    public Materia(int idMateria, String nombre) {
        this.id_materia = idMateria;
        this.nombre = nombre;
    }

    public Materia(int idMateria) {
        this.id_materia = idMateria;
    }

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public Materia() {
    } 
    
    public int getIdMateria() {
        return id_materia;
    }

    public void setIdMateria(int idMateria) {
        this.id_materia = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}
