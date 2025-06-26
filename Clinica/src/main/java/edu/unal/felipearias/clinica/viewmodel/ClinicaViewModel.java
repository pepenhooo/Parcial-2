package edu.unal.felipearias.clinica.viewmodel;
import edu.unal.felipearias.clinica.model.IPersistencia;
import edu.unal.felipearias.clinica.model.Medico;
import edu.unal.felipearias.clinica.model.Clinica;
import edu.unal.felipearias.clinica.model.Paciente;
import java.util.ArrayList;
import java.util.List;

import edu.unal.felipearias.clinica.model.Consulta;
import edu.unal.felipearias.clinica.persistencia.PersistenciaArchivo;

public class ClinicaViewModel {
    private Clinica clinica;
    private IPersistencia persistencia;
    private String tipoUsuarioLogueado;
    private String cedulaUsuarioLogueado;

    public ClinicaViewModel() {
        this.persistencia = new PersistenciaArchivo();
        try {
            this.clinica = persistencia.cargar();
        } catch (Exception e) {
            this.clinica = new Clinica();
        }
    }

    public void registrarPaciente(Paciente p) {
        clinica.agregarPaciente(p);
        guardarDatos();
    }

    public void registrarMedico(Medico m) {
        clinica.agregarMedico(m);
        guardarDatos();
    }

    public void registrarConsulta(Consulta c) {
        clinica.registrarConsulta(c);
        guardarDatos();
    }
    
    public void setUsuarioLogueado(String tipo, String cedula){
        this.tipoUsuarioLogueado = tipo;
        this.cedulaUsuarioLogueado = cedula;
    }

    public Paciente buscarPaciente(String cedula) {
        return clinica.buscarPacientePorCedula(cedula);
    }

    public Medico buscarMedico(String cedula) {
        return clinica.buscarMedicoPorCedula(cedula);
    }

    public String[] getCedulasPacientes() {
        List<Paciente> lista = clinica.getPacientes();
        String[] cedulas = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            cedulas[i] = lista.get(i).getCedula();
        }
        return cedulas;
    }
    
    public String getTipoUsuarioLogueado(){
        return tipoUsuarioLogueado;
    }
    
    public String getCedulaUsuarioLogueado(){
        return cedulaUsuarioLogueado;
    }

    public String[] getCedulasMedicos() {
        List<Medico> lista = clinica.getMedicos();
        String[] cedulas = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            cedulas[i] = lista.get(i).getCedula();
        }
        return cedulas;
    }

    public List<Consulta> getConsultasPorMedico(Medico medico) {
        List<Consulta> resultado = new ArrayList<>();
        for (Consulta c : clinica.getConsultas()) {
            if (c.getMedico().getCedula().equals(medico.getCedula())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    private void guardarDatos() {
        try {
            persistencia.guardar(clinica);
        } catch (Exception e) {
            System.out.println("Error guardando datos: " + e.getMessage());
        }
    }
    
    public void cerrarSesion(){
        this.cedulaUsuarioLogueado = null;
        this.tipoUsuarioLogueado = null;
    }
    
    public List<Paciente> getPacientes() {
        return clinica.getPacientes();
    }
    
    public List<Medico> getMedicos() {
        return clinica.getMedicos();
    }
}
