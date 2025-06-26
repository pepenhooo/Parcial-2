package edu.unal.felipearias.clinica.model;
import java.io.Serializable;

public class Medico extends Persona implements Serializable{
    private final String especialidad;
    
    public Medico(String nombre, String cedula, int edad, String especialidad, String clave){
        super(nombre, cedula, edad, clave);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    
    @Override
    public String getClave(){
        return clave;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", Especialidad: " + especialidad;
    }
}
