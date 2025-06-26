package edu.unal.felipearias.clinica.model;

public interface IPersistencia {
    void guardar(Clinica clinica) throws Exception;
    Clinica cargar() throws Exception;
}
