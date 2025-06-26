package edu.unal.felipearias.clinica.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import edu.unal.felipearias.clinica.model.Paciente;
import edu.unal.felipearias.clinica.model.Medico;
import edu.unal.felipearias.clinica.viewmodel.ClinicaViewModel;

public class PanelRegistro extends JPanel{
    private JComboBox<String> tipoPersonaCombo, especialidadComboBox;
    private JTextField nombreField, cedulaField, edadField;
    private JButton registrarBtn, limpiarBtn;
    private ClinicaViewModel viewModel;
    private JPasswordField claveField;
    private JLabel claveLabel, especialidadLabel;

    public PanelRegistro(ClinicaViewModel viewModel) {
        this.viewModel = viewModel;
        if (!"Administrativo".equals(viewModel.getTipoUsuarioLogueado())){
            JOptionPane.showMessageDialog(this, "Acceso denegado: solo administrativos pueden registrar usuarios.");
            setVisible(false);
            return;
        }
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tipo de persona
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Tipo de persona:"), gbc);
        gbc.gridx = 1;
        tipoPersonaCombo = new JComboBox<>(new String[]{"Paciente", "Médico"});
        add(tipoPersonaCombo, gbc);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        nombreField = new JTextField(15);
        add(nombreField, gbc);

        // Cédula
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Cédula:"), gbc);
        gbc.gridx = 1;
        cedulaField = new JTextField(15);
        add(cedulaField, gbc);

        // Edad
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        edadField = new JTextField(15);
        add(edadField, gbc);

        // Especialidad (solo para médicos)
        especialidadLabel = new JLabel("Especialidad:");
        gbc.gridx = 0; gbc.gridy = 4;
        add(especialidadLabel, gbc);
        especialidadComboBox = new JComboBox<>(new String[]{
            "Cardiología", "Cirugía", "Dermatología", "Ginecología", "Medicina General", 
            "Neurología", "Ortopedia", "Pediatría", "Psicología", "Psiquiatría", "Urología"
        });
        gbc.gridx = 1;
        add(especialidadComboBox, gbc);
        
        // Clave (solo para médicos)
        claveLabel = new JLabel("Clave:");        
        gbc.gridx = 0; gbc.gridy = 5;
        add(claveLabel, gbc);
        claveField = new JPasswordField(15);
        gbc.gridx = 1;
        add(claveField, gbc);

        // Botones
        gbc.gridx = 0; gbc.gridy = 6;
        registrarBtn = new JButton("Registrar");
        add(registrarBtn, gbc);
        gbc.gridx = 1;
        limpiarBtn = new JButton("Limpiar");
        add(limpiarBtn, gbc);

        registrarBtn.addActionListener(e -> registrarPersona());
        limpiarBtn.addActionListener(e -> limpiarCampos());

        tipoPersonaCombo.addActionListener(e -> toggleCamposMedico());
        
        toggleCamposMedico();    
    }
    

    private void toggleCamposMedico() {
        boolean esMedico = tipoPersonaCombo.getSelectedItem().equals("Médico");
        
        especialidadComboBox.setVisible(esMedico);
        especialidadLabel.setVisible(esMedico);
        claveField.setVisible(true);
        claveLabel.setVisible(true);
        
        revalidate();
        repaint();
    }

    private void registrarPersona() {
        try {
            String nombre = nombreField.getText().trim();
            String cedula = cedulaField.getText().trim();
            String edadText = edadField.getText().trim();
            String especialidad = (String) especialidadComboBox.getSelectedItem();

            if (nombre.isEmpty() || cedula.isEmpty() || edadText.isEmpty()) {
                throw new IllegalArgumentException("Campos obligatorios vacíos.");
            }

            int edad = Integer.parseInt(edadText);

            if (tipoPersonaCombo.getSelectedItem().equals("Paciente")) {
                String clave = new String(claveField.getPassword()).trim();
                if (clave.isEmpty()){
                    throw new IllegalArgumentException("La clave es obligatoria para pacientes.");
                }
                Paciente p = new Paciente(nombre, cedula, edad, clave);
                viewModel.registrarPaciente(p);
                JOptionPane.showMessageDialog(this, "Paciente registrado.");
            } 
            else {
                String clave = new String(claveField.getPassword()).trim();
                
                if (especialidad.isEmpty()) {
                    throw new IllegalArgumentException("La especialidad es obligatoria.");
                }
                if (clave.isEmpty()){
                    throw new IllegalArgumentException("La clave es obligatoria.");
                }
                
                Medico m = new Medico(nombre, cedula, edad, especialidad, clave);
                viewModel.registrarMedico(m);
                JOptionPane.showMessageDialog(this, "Médico registrado.");
            }

            limpiarCampos();

        } 
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Edad inválida.");
        } 
        catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void limpiarCampos() {
        nombreField.setText("");
        cedulaField.setText("");
        edadField.setText("");
        especialidadComboBox.setSelectedIndex(0);
    }
}
