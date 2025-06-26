package edu.unal.felipearias.clinica.model;
import java.io.Serializable;

public abstract class Persona implements Serializable{
    protected String nombre;
    protected String cedula; // No se usa int aunque sea un número ya que no se hacen operaciones con el número de cédula
    protected int edad;
    protected String clave;

    public Persona(String nombre, String cedula, int edad, String clave){
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.clave = clave;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public int getEdad() {
        return edad;
    }
    
    public String getClave(){
        return clave;
    }
    
    public void setClave(String clave){
        this.clave = clave;
    }
    
    @Override
    public String toString(){
        return "Nombre: " + nombre + " (C.C. " + cedula + ")";
    }
    
}
