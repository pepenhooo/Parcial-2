package edu.unal.felipearias.clinica.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import edu.unal.felipearias.clinica.model.Consulta;
import edu.unal.felipearias.clinica.model.Paciente;
import edu.unal.felipearias.clinica.model.Medico;
import edu.unal.felipearias.clinica.viewmodel.ClinicaViewModel;

public class PanelConsulta extends JPanel{
   private JComboBox<String> pacienteCombo, medicoCombo;
    private JTextField sintomasField, diagnosticoField, tratamientoField;
    private JButton registrarBtn;
    private ClinicaViewModel viewModel;

    public PanelConsulta(ClinicaViewModel viewModel) {
        this.viewModel = new ClinicaViewModel();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Paciente:"), gbc);
        gbc.gridx = 1;
        pacienteCombo = new JComboBox<>(viewModel.getCedulasPacientes());
        add(pacienteCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Médico:"), gbc);
        gbc.gridx = 1;
        medicoCombo = new JComboBox<>(viewModel.getCedulasMedicos());
        add(medicoCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Síntomas:"), gbc);
        gbc.gridx = 1;
        sintomasField = new JTextField(15);
        add(sintomasField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Diagnóstico:"), gbc);
        gbc.gridx = 1;
        diagnosticoField = new JTextField(15);
        add(diagnosticoField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Tratamiento:"), gbc);
        gbc.gridx = 1;
        tratamientoField = new JTextField(15);
        add(tratamientoField, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        registrarBtn = new JButton("Registrar Consulta");
        add(registrarBtn, gbc);

        registrarBtn.addActionListener(e -> registrarConsulta());
    }

    private void registrarConsulta() {
        try {
            String cedPaciente = (String) pacienteCombo.getSelectedItem();
            String cedMedico = (String) medicoCombo.getSelectedItem();

            Paciente p = viewModel.buscarPaciente(cedPaciente);
            Medico m = viewModel.buscarMedico(cedMedico);

            if (p == null || m == null) {
                throw new IllegalArgumentException("Paciente o médico no encontrados.");
            }

            String sintomas = sintomasField.getText().trim();
            String diagnostico = diagnosticoField.getText().trim();
            String tratamiento = tratamientoField.getText().trim();

            if (sintomas.isEmpty() || diagnostico.isEmpty() || tratamiento.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben completarse.");
            }

            Consulta c = new Consulta(LocalDate.now(), sintomas, diagnostico, tratamiento, m, p);
            viewModel.registrarConsulta(c);

            JOptionPane.showMessageDialog(this, "Consulta registrada correctamente.");
            limpiarCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        sintomasField.setText("");
        diagnosticoField.setText("");
        tratamientoField.setText("");
    }
} 