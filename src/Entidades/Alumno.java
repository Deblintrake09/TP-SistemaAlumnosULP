/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.time.LocalDate;

public class Alumno {
    
    private int id_alumno;
    private int legajo;
    private String nombre;
    private LocalDate fechaNac;
    private boolean activo;


public Alumno()
{
    
}

public Alumno(int id)
{
    this.id_alumno =id;
}

public Alumno(int id, int leg, String nombre, LocalDate fn, boolean activo)
{
    this.id_alumno =id;
    this.legajo=leg;
    this.nombre=nombre;
    this.fechaNac=fn;
    this.activo=activo;
}

public Alumno(int leg, String nombre, LocalDate fn, boolean activo)
{
    this.legajo=leg;
    this.nombre=nombre;
    this.fechaNac=fn;
    this.activo=activo;
}

    
    public int getIdAlumno() {
        return id_alumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.id_alumno = idAlumno;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombreAlumno() {
        return nombre;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombre = nombreAlumno;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
