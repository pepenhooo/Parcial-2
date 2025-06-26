package edu.unal.felipearias.clinica;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import edu.unal.felipearias.clinica.view.VentanaLogin;
import edu.unal.felipearias.clinica.viewmodel.ClinicaViewModel;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame root = new JFrame();
            root.setUndecorated(true);
            root.setSize(0, 0);
            root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            root.setLocationRelativeTo(null);
            root.setVisible(true);
            
            ClinicaViewModel vm = new ClinicaViewModel();
            new VentanaLogin(vm);
        });
    }
}
