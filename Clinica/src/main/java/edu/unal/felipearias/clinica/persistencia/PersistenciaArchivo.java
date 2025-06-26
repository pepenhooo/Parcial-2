package edu.unal.felipearias.clinica.persistencia;
import java.io.*;

import edu.unal.felipearias.clinica.model.Clinica;
import edu.unal.felipearias.clinica.model.IPersistencia;

public class PersistenciaArchivo implements IPersistencia{
    private static final String ARCHIVO = "clinica.dat";

    @Override
    public void guardar(Clinica clinica) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("clinica.dat"))) {
            oos.writeObject(clinica);
        }
    }

    @Override
    public Clinica cargar() throws IOException, ClassNotFoundException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new Clinica(); // Si no existe, se devuelve una nueva
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (Clinica) ois.readObject();
        }
    }
}
