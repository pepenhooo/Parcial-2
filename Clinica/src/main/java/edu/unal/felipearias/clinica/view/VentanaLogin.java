package edu.unal.felipearias.clinica.view;

import javax.swing.*;
import java.awt.*;
import edu.unal.felipearias.clinica.model.Medico;
import edu.unal.felipearias.clinica.viewmodel.ClinicaViewModel;
import edu.unal.felipearias.clinica.model.Paciente;

public class VentanaLogin extends JFrame {
    private final JTextField cedulaField;
    private final JPasswordField claveField;
    private final JComboBox<String> tipoUsuarioCombo;
    private final JButton loginButton;
    private final ClinicaViewModel viewModel;

    public VentanaLogin(ClinicaViewModel viewModel) {
        super("Ingreso al Sistema - Clínica");
        this.viewModel = viewModel;

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        panel.add(new JLabel("Cédula:"));
        cedulaField = new JTextField();
        panel.add(cedulaField);

        panel.add(new JLabel("Clave:"));
        claveField = new JPasswordField();
        panel.add(claveField);

        panel.add(new JLabel("Tipo de usuario:"));
        tipoUsuarioCombo = new JComboBox<>(new String[]{"Médico", "Paciente", "Administrativo"});

        // Eliminar opciones si no hay datos guardados
        if (viewModel.getMedicos().isEmpty()) {
            tipoUsuarioCombo.removeItem("Médico");
        }
        if (viewModel.getPacientes().isEmpty()) {
            tipoUsuarioCombo.removeItem("Paciente");
        }

        panel.add(tipoUsuarioCombo);

        loginButton = new JButton("Ingresar");
        panel.add(new JLabel()); // Espacio vacío
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);

        loginButton.addActionListener(e -> realizarLogin());
    }

    private void realizarLogin() {
        String cedula = cedulaField.getText().trim();
        String tipo = (String) tipoUsuarioCombo.getSelectedItem();
        String claveIngresada = new String(claveField.getPassword()).trim();

        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula.");
            return;
        }
        
        if ("Médico".equals(tipo) && viewModel.getMedicos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay médicos registrados. Solo puede ingresar como administrativo.");
            return;
        }

        if ("Paciente".equals(tipo) && viewModel.getPacientes().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay pacientes registrados. Solo puede ingresar como administrativo.");
            return;
        }

        // Validación de credenciales
        switch (tipo) {
            case "Médico" -> {
                Medico medico = viewModel.buscarMedico(cedula);
                if (medico == null || !medico.getClave().equals(claveIngresada)) {
                    JOptionPane.showMessageDialog(this, "Cédula o clave incorrecta para médico.");
                    return;
                }
            }
            case "Paciente" -> {
                Paciente paciente = viewModel.buscarPaciente(cedula);
                if (paciente == null || !paciente.getClave().equals(claveIngresada)) {
                    JOptionPane.showMessageDialog(this, "Cédula o clave incorrecta para paciente.");
                    return;
                }
            }
            case "Administrativo" -> {
                if (!cedula.equals("admin") || !claveIngresada.equals("admin123")) {
                    JOptionPane.showMessageDialog(this, "Credenciales administrativas incorrectas.");
                    return;
                }
            }
        }

        viewModel.setUsuarioLogueado(tipo, cedula);
        new VentanaPrincipal(viewModel);
        dispose();
    }
}