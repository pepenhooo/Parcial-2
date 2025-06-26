package edu.unal.felipearias.clinica.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import edu.unal.felipearias.clinica.model.Consulta;
import edu.unal.felipearias.clinica.model.Paciente;
import edu.unal.felipearias.clinica.model.Medico;
import edu.unal.felipearias.clinica.viewmodel.ClinicaViewModel;

public class PanelHistorial extends JPanel{
    private final JComboBox<String> tipoBusquedaCombo;
    private final JComboBox<String> cedulaCombo;
    private final JButton buscarBtn;
    private final JTextArea resultadoArea;
    private final ClinicaViewModel viewModel;

    public PanelHistorial(ClinicaViewModel viewModel) {
        this.viewModel = new ClinicaViewModel();
        setLayout(new BorderLayout());

        // Panel superior de entrada
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new FlowLayout());

        tipoBusquedaCombo = new JComboBox<>(new String[]{"Por Paciente", "Por Médico"});
        cedulaCombo = new JComboBox<>();
        cedulaCombo.setEditable(true);
        cedulaCombo.setPrototypeDisplayValue("123456789012345");
        
        buscarBtn = new JButton("Buscar");

        panelEntrada.add(new JLabel("Buscar:"));
        panelEntrada.add(tipoBusquedaCombo);
        panelEntrada.add(new JLabel("Cédula:"));
        panelEntrada.add(cedulaCombo);
        panelEntrada.add(buscarBtn);

        add(panelEntrada, BorderLayout.NORTH);

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        buscarBtn.addActionListener(e -> buscarHistorial());
        
        if ("Medico".equals(viewModel.getTipoUsuarioLogueado())){
            tipoBusquedaCombo.setEnabled(false);
            cedulaCombo.setEnabled(false);
            buscarHistorial();
        }
        else {
            cargarCedulas();
        }
    }
    private void cargarCedulas(){
        cedulaCombo.removeAllItems();
        
        if (tipoBusquedaCombo.getSelectedItem().equals("Por Paciente")){
            for (Paciente p : viewModel.getPacientes()){
                cedulaCombo.addItem(p.getCedula());
            }
        }
        else {
            for (Medico m : viewModel.getMedicos()){
                cedulaCombo.addItem(m.getCedula());
            }
        }
    }
    private void buscarHistorial(){
        resultadoArea.setText("");
        
        if ("Medico".equals(viewModel.getTipoUsuarioLogueado())) {
            Medico m = viewModel.buscarMedico(viewModel.getCedulaUsuarioLogueado());
            if (m == null) {
                resultadoArea.setText("Médico no encontrado.");
                return;
            }
            
            List<Consulta> consultas = viewModel.getConsultasPorMedico(m);
            if (consultas.isEmpty()) {
                resultadoArea.setText("No tiene consultas registradas.");
            }
            else {
                for (Consulta c : consultas) {
                    resultadoArea.append(c.toString() + "\n\n");
                }
            }
            return;
        }
        
        String cedula = ((JTextField) cedulaCombo.getEditor().getEditorComponent()).getText().trim();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una cédula válida.");
            return;
        }
        
        boolean porPaciente = tipoBusquedaCombo.getSelectedItem().equals("Por Paciente");
        if (porPaciente) {
            Paciente p = viewModel.buscarPaciente(cedula);
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
                return;
            }
            List<Consulta> historial = p.getHistorial();
            if (historial.isEmpty()) {
                resultadoArea.setText("El paciente no tiene consultas registradas.");
            }
            else {
                for (Consulta c : historial) {
                    resultadoArea.append(c.toString() + "\n\n");
                }
            }
        }
        else {
            Medico m = viewModel.buscarMedico(cedula);
            if (m == null) {
                JOptionPane.showMessageDialog(this, "Médico no encontrado.");
                return;
            }
            
            List<Consulta> consultas = viewModel.getConsultasPorMedico(m);
            if (consultas.isEmpty()) {
                resultadoArea.setText("El médico no tiene consultas registradas.");
        }
            else {
                for (Consulta c : consultas) {
                    resultadoArea.append(c.toString() + "\n\n");
                }
            }
        }
    }
}
