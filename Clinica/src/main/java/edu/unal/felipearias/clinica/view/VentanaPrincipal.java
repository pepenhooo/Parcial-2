package edu.unal.felipearias.clinica.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import edu.unal.felipearias.clinica.viewmodel.ClinicaViewModel;
import edu.unal.felipearias.clinica.view.PanelRegistro;
import edu.unal.felipearias.clinica.view.PanelConsulta;
import edu.unal.felipearias.clinica.view.PanelHistorial;
import edu.unal.felipearias.clinica.view.VentanaLogin;

public class VentanaPrincipal extends JFrame{
    private JMenuBar barraMenu;
    private JMenu menuArchivo, menuAcciones; 
    private JMenuItem itemRegistro, itemConsulta, itemHistorial, itemSalir;
    private JPanel panelActual;
    private final ClinicaViewModel viewModel;
    
    public VentanaPrincipal(ClinicaViewModel viewModel){
        this.viewModel = viewModel;
        setTitle("Sistema de Gestión de Clínica");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        inicializarMenu();
        
        String tipo = viewModel.getTipoUsuarioLogueado();
        if ("Medico".equals(tipo)){
            itemRegistro.setEnabled(false);
            itemConsulta.setEnabled(false);
        }
        
        mostrarPanel(new PanelHistorial(viewModel));
        
        setVisible(true);
    }
    
    private void inicializarMenu(){
        barraMenu = new JMenuBar();
        
        menuAcciones = new JMenu("Acciones");
        
        itemRegistro = new JMenuItem("Registrar Paciente/Médico");
        itemConsulta = new JMenuItem("Asignar Consulta");
        itemHistorial = new JMenuItem("Ver Historial");
        itemSalir = new JMenuItem("Salir");
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesion");
        
        itemRegistro.addActionListener(e -> mostrarPanel(new PanelRegistro(viewModel)));
        itemConsulta.addActionListener(e -> mostrarPanel(new PanelConsulta(viewModel)));
        itemHistorial.addActionListener(e -> mostrarPanel(new PanelHistorial(viewModel)));
        itemCerrarSesion.addActionListener(e  -> {
            viewModel.cerrarSesion();
            dispose();
            SwingUtilities.invokeLater(() -> new VentanaLogin(viewModel));
            JOptionPane.showMessageDialog(null, "Sesion cerrada exitosamente.");
        });
        itemSalir.addActionListener(e -> System.exit(0));
        
        menuAcciones.add(itemRegistro);
        menuAcciones.add(itemConsulta);
        menuAcciones.add(itemHistorial);
        menuAcciones.add(itemCerrarSesion);
        menuAcciones.add(itemSalir);
        
        barraMenu.add(menuAcciones);
        
        setJMenuBar(barraMenu);
    }
    
    private void mostrarPanel(JPanel nuevoPanel){
        if (panelActual != null){
            remove(panelActual);
        }
        panelActual = nuevoPanel;
        add(panelActual, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}