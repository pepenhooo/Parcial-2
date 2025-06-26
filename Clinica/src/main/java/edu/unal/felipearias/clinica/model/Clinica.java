package edu.unal.felipearias.clinica.model;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Clinica implements Serializable{
    private final List<Paciente> pacientes;
    private final List<Medico> medicos;
    private final List<Consulta> consultas;
    
    public Clinica(){
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.consultas = new ArrayList<>();
    }
    
    public void agregarPaciente(Paciente p){
        pacientes.add(p);
    }
    
    public void agregarMedico(Medico m){
        medicos.add(m);
    }
    
    public void registrarConsulta(Consulta c){
        consultas.add(c);
        c.getPaciente().agregarConsulta(c);
    }
    
    public List<Paciente> getPacientes() {
        return pacientes;
    }
    
    public List<Medico> getMedicos() {
        return medicos;
    }
    
    public List<Consulta> getConsultas() {
        return consultas;
    }
    
    public Paciente buscarPacientePorCedula(String cedula){
        for (Paciente p : pacientes){
            if (p.getCedula().equals(cedula)){
                return p;
            }
        }
        return null;
    }
    
    public Medico buscarMedicoPorCedula(String cedula){
        for (Medico m : medicos){
            if (m.getCedula().equals(cedula)){
                return m;
            }
        }
        return null;
    }
}
