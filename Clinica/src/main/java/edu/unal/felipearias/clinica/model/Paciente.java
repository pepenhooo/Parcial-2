package edu.unal.felipearias.clinica.model;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Paciente extends Persona implements Serializable{
    private final List<Consulta> historial;
    
    public Paciente(String nombre, String cedula, int edad, String clave){
        super(nombre, cedula, edad, clave);
        this.historial = new ArrayList<>();
    }
    
    public void agregarConsulta(Consulta consulta){
        historial.add(consulta);
    }
    
    public List<Consulta> getHistorial(){
        return historial;
    }
}
