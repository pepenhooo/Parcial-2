package edu.unal.felipearias.clinica.model;
import java.io.Serializable;
import java.time.LocalDate;

public class Consulta implements Serializable{
    private LocalDate fecha;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private Medico medico;
    private Paciente paciente;
    
    public Consulta(LocalDate fecha, String sintomas, String diagnostico, String tratamiento, Medico medico, Paciente paciente){
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medico = medico;
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    
    @Override
    public String toString(){
        return "Consulta [" + fecha + "]\n" + 
                "Paciente: " + paciente.getNombre() + "\n" + 
                "Medico: " + medico.getNombre() + "(" + medico.getEspecialidad() + ")\n" +
                "Sintomas: " + sintomas + "\n" + 
                "Diagnostico: " + diagnostico + "\n" +
                "Tratamiento: " + tratamiento;                
    }
}
