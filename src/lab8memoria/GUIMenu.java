/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8memoria;

/**
 *
 * @author denam
 */

    
import java.awt.*;
import javax.swing.*;

public class GUIMenu extends JPanel {

    private JButton btnAdministrarEquipo;
    private JButton btnCerrarSesion;
    private JButton btnIniciarBatalla;
    private JLabel lblNombre;

    public GUIMenu(GUIPantalla padre) {
        inicializarComponentes(padre);
    }

    private void inicializarComponentes(GUIPantalla padre) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 45));

        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraSuperior.setBackground(new Color(20, 20, 35));

        btnAdministrarEquipo = new JButton("Administrar equipo");
        btnAdministrarEquipo.addActionListener(ev->{
        new GUIAdministrador(padre);
        
        });
        
       
        btnCerrarSesion = new JButton("Cerrar sesión");

        lblNombre = new JLabel("nombre");
        lblNombre.setForeground(Color.BLUE);

        btnAdministrarEquipo.setFocusable(false);
       
        btnCerrarSesion.setFocusable(false);

        btnCerrarSesion.addActionListener(ev -> {
            padre.mostrarCard("inicio");
        });

        barraSuperior.add(lblNombre);
        barraSuperior.add(Box.createHorizontalStrut(1100));
        barraSuperior.add(btnAdministrarEquipo);
    
        barraSuperior.add(btnCerrarSesion);

        add(barraSuperior, BorderLayout.NORTH);

   
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

    
        JLabel lblTitulo = new JLabel("Batalla Pokemon");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 38));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 25, 0);
        panelCentral.add(lblTitulo, gbc);

        btnIniciarBatalla = new JButton("Iniciar batalla");
        btnIniciarBatalla.setFont(new Font("Arial", Font.BOLD, 22));
        btnIniciarBatalla.setPreferredSize(new Dimension(250, 70));
        btnIniciarBatalla.setFocusable(false);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panelCentral.add(btnIniciarBatalla, gbc);

        add(panelCentral, BorderLayout.CENTER);
    }
}